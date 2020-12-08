package bean.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 处理消息的handler
 * TextWebSocketFrame：在netty中，是用于websocket专门处理文本的对象，frame是消息的载体。
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //获取到所有的客户端channel。
    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        //1.获取客户端发来的消息
        String content = msg.text();

        Channel currentChannel = ctx.channel();

        //2.判断消息类型，根据不同的类型来处理不同的业务


    }


    /**
     * 当客户端连接服务端之后（打开连接）
     * 获取客户端的channel，并且放到ChannelGroup中去进行管理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发handler销毁时，这个会自动的移除的。
        users.remove(ctx.channel());

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生异常之后关闭连接，随后从ChannelGroup中移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

        //1.获取客户端发来的消息
        String content = textWebSocketFrame.text();
        System.out.println("收到消息内容:"+content);

    }
}