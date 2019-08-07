
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * 

* @ClassName: WebServer

* @Description: TODO(这里用一句话描述这个类的作用)

* @author SuXiaoliang

* @date 2018年6月23日 下午1:26:56

*
 */
public class WebServer extends NanoHTTPD {

	public static final String TAG = WebServer.class.getSimpleName();
	private static final String REQUEST_ROOT = "/";
	private static final String REQUEST_WECHAT = "/wechat";
	private static final String REQUEST_DINGDING = "/dingding";
	private static final String REQUEST_GETPAY = "/getpay";
	private static final String REQUEST_QUERY = "/query";
	private static final String REQUEST_GETRESULT = "/getresult";
	private static final String DINGDING_QUERY = "/dingdingquery";
	public static String MSGRECEIVED_ACTION = "com.tools.payhelper.msgreceived";

	public WebServer( int serverport) {
		super(serverport);
	}

	@Override
	public Response serve(IHTTPSession session) {
		System.out.println( "OnRequest: " + session.getUri());
		try {
			if (REQUEST_ROOT.equals(session.getUri())) {
				return responseRootPage(session);
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

			} else if (REQUEST_QUERY.equals(session.getUri())) {
			} else if (REQUEST_GETRESULT.equals(session.getUri())) {

			}if (DINGDING_QUERY.equals(session.getUri())){

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
		builder.append("Hello World!");
		builder.append("</body></html>\n");
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

	protected String getQuotaStr(String text) {
		return "\"" + text + "\"";
	}
}
