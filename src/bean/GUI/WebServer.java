package bean.GUI;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * 

* @ClassName: bean.GUI.WebServer

* @Description: TODO(这里用一句话描述这个类的作用)

* @author SuXiaoliang

* @date 2018年6月23日 下午1:26:56

*
 */
public class WebServer extends NanoHTTPD {
	private File webRoot;
	public static final String TAG = WebServer.class.getSimpleName();
	private static final String REQUEST_ROOT = "/";
	private static final String REQUEST_CHARGE = "/charge";
	private static final String REQUEST_WECHAT = "/wechat";
	private static final String REQUEST_DINGDING = "/dingding";
	private static final String REQUEST_GETPAY = "/getpay";
	private static final String REQUEST_QUERY = "/query";
	private static final String REQUEST_GETRESULT = "/getresult";
	private static final String DINGDING_QUERY = "/dingdingquery";
	public static String MSGRECEIVED_ACTION = "com.tools.payhelper.msgreceived";
	public static  Map<String,BankInfo> orderMap=new HashMap<>();
	public WebServer( int serverport) throws IOException {
		super(serverport);
		start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
		System.out.println("start port:"+serverport);
	}
	public static class BankInfo{

		private String epccGwMsg="";
		private String url="";

		public BankInfo(String epccGwMsg, String url) {
			this.epccGwMsg = epccGwMsg;
			this.url = url;
		}

		public String getEpccGwMsg() {
			return epccGwMsg;
		}

		public String getUrl() {
			return url;
		}
	}

	@Override
	public Response serve(IHTTPSession session) {
		System.out.println( "OnRequest: " + session.getUri());
		Method method = session.getMethod();
		try {
			if (Method.POST.equals(method)){
				if (REQUEST_CHARGE.equals(session.getUri())){
					session.parseBody(new HashMap<String, String>());
					Map<String, List<String>> map = session.getParameters();
					System.out.println("测试数据:"+map.toString());
					String money=map.get("money").get(0);
					String code=map.get("code").get(0);
					long time=System.currentTimeMillis();
					String order=money+"_"+time;
					orderMap.put(order,null);
					for(ChannelHandlerContext ctx:ServerHandler.Clients.values())
					{
						TcpParam tcp=new TcpParam(205);
						tcp.write(money);
						tcp.write(order);
						tcp.write(code);
						byte[] param2 = tcp.getParam2();
						byte[] bytes = new byte[0];
						try {
							bytes = BaseNetTool.appendHead2(param2);
							ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
							ctx.writeAndFlush(byteBuf);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}
					while (null==orderMap.get(order)){
						Thread.sleep(800);
						if (System.currentTimeMillis()-time>30*1000){
							return response404(session,"");
						}
					}
					BankInfo bankInfo = orderMap.get(order);
					if ("".equals(bankInfo.getEpccGwMsg())){
						System.out.println("特殊跳转");
						return goBankByToken(session,bankInfo.getUrl(),"");
					}else{
						System.out.println("查看跳转地址:"+bankInfo.getUrl());
						String urlDecoderString = UrlUtil.getURLDecoderString(bankInfo.getEpccGwMsg().replaceAll("epccGwMsg=", ""));
						System.out.println("收到表单请求数据啦 哈哈  金额:"+money+"数据:"+urlDecoderString);
						return goBank(session,bankInfo.getUrl(),urlDecoderString);
					}

//					return response404(session,bankInfo.getUrl());

				}
			}

			if (REQUEST_ROOT.equals(session.getUri())) {
//				File indexFile = new File( "src/bean/html/index.html");
//				if (indexFile.exists()) {
////					return render200(session.getUri() + "index.html", indexFile);
//					return newFixedLengthResponse(Response.Status.OK, "text/html;charset=UTF-8", readHtml("src/bean/html/index.html"));
//				} else {
//					return response404(session,"");
//				}
				return responseRootPage(session);
//				return goBank(session);
//				return newFixedLengthResponse(Response.Status.OK, "text/html;charset=UTF-8", readHtml("src/bean/html/index.html"));
//				return FileStream(session,"index.html"+session.getUri());
			} else if (REQUEST_GETPAY.equals(session.getUri())) {
				@SuppressWarnings("deprecation")
				Map<String, String> params = session.getParms();
				String money = params.get("money");
				String mark = params.get("mark");
				String type=params.get("type");
				String orderid=params.get("orderid");
				if(type==null || type.equals("")){
					type="wechat";
				}
				double m=Double.parseDouble(money);
				if(type.equals("alipay")){
					if(m>50000){
						return responseText(session, "支付宝最大支持单笔50000元支付！");
					}
				}else if(type.equals("wechat")){
					if(m>15000){
						return responseText(session, "微信最大支持单笔15000元支付！");
					}
				}else if(type.equals("qq")){
					if(m>30000){
						return responseText(session, "QQ最大支持单笔30000元支付！");
					}
					if(mark.length()>12){
						return responseText(session, "QQ备注长度不能超过12位！");
					}
				}

			} else if (REQUEST_WECHAT.equals(session.getUri())) {
				Thread.sleep(2000);
				StringBuilder builder = new StringBuilder();
				builder.append("<!DOCTYPE html>\n" +
						"<html lang=\"en\">\n" +
						"<head>\n" +
						"    <meta charset=\"UTF-8\">\n" +
						"    <title>Title</title>\n" +
						"</head>\n" +
						"<body>\n" +
						"<script type=\"text/javascript\">\n" +
						"     var a = document.createElement('a');\n" +
						"a.setAttribute('href', \"https://pay.abchina.com/ebusperbank/PaymentModeNewAct.ebf?TOKEN=15743399970156441391\");\n" +
						"a.setAttribute('target', '_self');\n" +
						"a.setAttribute('id', 'bank');\n" +
						"// 防止反复添加\n" +
						"if(document.getElementById('startTelMedicine')) {\n" +
						"\tdocument.body.removeChild(document.getElementById('startTelMedicine'));\n" +
						"}\n" +
						"a.click()\n" +
						"document.body.appendChild(a);\n" +
						"</script>\n" +
						"</body>\n" +
						"</html>");
				System.out.println("返回数据信息");
				return newFixedLengthResponse(builder.toString());
			} else if (REQUEST_QUERY.equals(session.getUri())) {
			} else if (REQUEST_GETRESULT.equals(session.getUri())) {

			}else if (DINGDING_QUERY.equals(session.getUri())){

			}{
				return response404(session, session.getUri());
			}
		} catch (Exception e) {
			return response404(session, e.getMessage());
		}
	}

	public Response responseRootPage(IHTTPSession session) {
		StringBuilder builder = new StringBuilder();
		builder.append("<!DOCTYPE html><html><body>");
		builder.append("Hello World!   234234");
		builder.append("</body></html>\n");
		return newFixedLengthResponse(builder.toString());
	}
	public Response goBankByToken(IHTTPSession session,String url,String epccGwMsg){
		StringBuilder builder = new StringBuilder();
		builder.append(
				"<!DOCTYPE html>\n" +
						"<html lang=\"en\">\n" +
						"<head>\n" +
						"    <meta charset=\"UTF-8\">\n" +
						"    <title>Title</title>\n" +
						"</head>\n" +
						"<body>\n" +
						"<script type=\"text/javascript\">\n" +
				"var a = document.createElement('a');\n" +
				"a.setAttribute('href', '"+url+"');\n" +
				"a.setAttribute('target', '_self');\n" +
				"a.setAttribute('id', 'bank');\n" +
				"document.body.appendChild(a);\n"+
				"a.click()\n"+
						"</script>\n" +
						"</body>\n" +
						"</html>"
//				"document.body.removeChild(a);\n"
		);
		System.out.println("生成订单:"+url);
		return newFixedLengthResponse(builder.toString());
	}
	public Response goBank(IHTTPSession session,String url,String epccGwMsg) {
		StringBuilder builder = new StringBuilder();
			builder.append("<!DOCTYPE html>\n" +
					"<html lang=\"en\">\n" +
					"<head>\n" +
					"    <meta charset=\"UTF-8\">\n" +
					"    <title>Title</title>\n" +
					"</head>\n" +
					"<body>\n" +
					"<script type=\"text/javascript\">\n" +
					"     var a = document.createElement('a');\n" +
					"a.setAttribute('href', 'https://pay.abchina.com/ebusperbank/PaymentModeNewAct.ebf?TOKEN=15743392049841402948');\n" +
					"a.setAttribute('target', '_self');\n" +
					"a.setAttribute('id', 'bank');\n" +
					"if(document.getElementById('startTelMedicine')) {\n" +
					"\tdocument.body.removeChild(document.getElementById('startTelMedicine'));\n" +
					"}\n" +
					"a.click()\n" +
					"document.body.appendChild(a);\n" +
					"</script>\n" +
					"</body>\n" +
					"</html>");

		return newFixedLengthResponse(builder.toString());
	}

	public Response responseText(IHTTPSession session, String text) {
		StringBuilder builder = new StringBuilder();
		builder.append("<!DOCTYPE html><html><body>");
		builder.append("<div style=\"width:100%;height:100%;text-align:center;padding-top:20px;\">");
		builder.append(text);
		builder.append("</div>");
		builder.append("</body></html>\n");
		System.out.println(builder.toString());
		return newFixedLengthResponse(Response.Status.OK, "text/html;charset=UTF-8", builder.toString());
	}
	public Response responseJson(IHTTPSession session, String json) {
		System.out.println(json);
		return newFixedLengthResponse(Response.Status.OK, "application/json;charset=UTF-8", json);
	}

	public Response response404(IHTTPSession session, String url) {
		StringBuilder builder = new StringBuilder();
		builder.append("<!DOCTYPE html><html><body>");
		builder.append("Sorry, Can't Found " + url + " !");
		builder.append("</body></html>\n");
		return newFixedLengthResponse(builder.toString());
	}
	public Response FileStream(IHTTPSession session, String pathname) {
		try {
			FileInputStream fis = new FileInputStream(pathname);
			System.out.println( pathname);
			return NanoHTTPD.newChunkedResponse(Response.Status.OK,readHtml(pathname),fis);
		} catch (FileNotFoundException e){
			e.printStackTrace();
			return response404(session,pathname);
		}
	}
	private String readHtml(String pathname) {
		BufferedReader br=null;
		StringBuffer sb = new  StringBuffer();
		try {
			br=new BufferedReader(new InputStreamReader(new FileInputStream(pathname),  "UTF-8"));
			String temp=null;
			while((temp=br.readLine())!=null){
				sb.append(temp);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Missing operating system!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println( "write error!");
			e.printStackTrace();
		}
		System.out.println( sb.toString());
		return sb.toString();
	}
	private Response render200(String uri, File file) {
		try {
			return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK, NanoHTTPD.getMimeTypeForFile(uri), new FileInputStream(file), file.length());
		} catch (FileNotFoundException e) {
			return null;
//			return render500(e.getMessage());
		}
	}
	protected String getQuotaStr(String text) {
		return "\"" + text + "\"";
	}
}
