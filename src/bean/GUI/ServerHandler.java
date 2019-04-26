package bean.GUI;

import bean.heartJump;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ServerHandler extends ChannelHandlerAdapter {
	public static Map<String,ChannelHandlerContext> Clients=new HashMap<>();
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		String uuid = ctx.channel().id().asLongText();
		Clients.put(uuid,ctx);
	}
	Gson gson;
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (null==gson){
			gson=new Gson();
		}
		//do something msg
		ByteBuf buf = (ByteBuf) msg;
		byte[] data = new byte[buf.readableBytes()];
		buf.readBytes(data);
		int tt = 0;
		byte[] utf_8 = BaseNetTool.getUTF_8(data, tt, 1);
		if ("|".equals(new String(utf_8))) {
			tt += 1;
			int length = BaseNetTool.Getint(data, tt);
			tt += 4;
			byte[] reciveData = BaseNetTool.getbyte(data, tt, length);
			tt += length;
			int k = 0;
			System.out.println(Arrays.toString(reciveData));
			for (k = 0; k < reciveData.length; k++) {
				reciveData[k] ^= 255;
			}
			int position = 0;
			int cmd = BaseNetTool.Getint(reciveData, position);
			System.out.println("收到cmd................." + cmd+".....................length:"+length);
			position += 4;
			switch (cmd) {
				case 100:
					double aDouble = BaseNetTool.getDouble(reciveData, position);
					position+=8;
					float aFloat = BaseNetTool.getFloat(reciveData, position);
					position+=4;
					short aShort = BaseNetTool.getShort(reciveData, position);
					position+=2;
					int len = BaseNetTool.Getint(reciveData, position);
					position+=4;
					byte[] utf_81 = BaseNetTool.getUTF_8(reciveData, position, len);
					position+=len;
					int leng2 = BaseNetTool.Getint(reciveData, position);
					position+=4;
					byte[] utf_82 = BaseNetTool.getUTF_8(reciveData, position, leng2);
					System.out.println("double:"+aDouble+"flat:"+aFloat+"short:"+aShort+"str1:"+new String(utf_81)+"str2:"+new String(utf_82));
					Thread.sleep(3000);
					Loging loging=new Loging();
					loging.setLoginStatus(1);
					loging.setPayType("alipay");
					TcpParam tcp=new TcpParam(100);
					tcp.write(gson.toJson(loging));
					byte[] param2 = tcp.getParam2();
					byte[] bytes = BaseNetTool.appendHead2(param2);
					System.out.println("发出去的数据"+Arrays.toString(bytes)+"长度："+bytes.length);
					ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
					ctx.writeAndFlush(byteBuf);
					break;
				case 101://心跳包返回
					heartJump jup=new heartJump();
					jup.setMessage("systemadfsdsfdsafdafasdfsadfdsfddsfadsfdsfdsafdsafsafasfsdafsfasdfdsafadfasfasdfsdafsadfsdafsdafsdafsdafsdafasdfsdafsadfasf");

					break;
				case 104://广播

					break;
				case 102:

					break;
				case 202:

					break;
			}
//                Intent arg0 = new Intent(String.valueOf(cmd));
//                arg0.putExtra("packge", com.tencent.charge.BuildConfig.FLAVOR);
//                arg0.putExtra("data", reciveData);
//                mContext.sendBroadcast(arg0);


		} else {
			System.out.println("解析错误");
		}


	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}