package bean.GUI;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.DefaultFileRegion;
import io.netty.handler.codec.http.*;

import java.io.*;

class HttpServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {

        if (msg instanceof HttpRequest) {

            // 请求，解码器将请求转换成HttpRequest对象
            HttpRequest request = (HttpRequest) msg;

            // 获取请求参数
            QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
            String name = "netty";
            if(queryStringDecoder.parameters().get("name") != null) {
                    name = queryStringDecoder.parameters().get("name").get(0);
            }

            // 响应HTML
            String responseHtml = "<html><body>Hello, " + name + "</body></html>";
            byte[] responseBytes = responseHtml.getBytes("UTF-8");
            int contentLength = responseBytes.length;

            // 构造FullHttpResponse对象，FullHttpResponse包含message body

            File html = new File("index.html");
            RandomAccessFile file = new RandomAccessFile(html, "r");
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(responseBytes));
//            FullHttpResponse response = new DefaultFullHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK);
//            response.headers().set("Content-Type", "text/html; charset=utf-8");
//            response.headers().set("Content-Length", Integer.toString(contentLength));
            ctx.write(response);
            ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            ctx.writeAndFlush(response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}