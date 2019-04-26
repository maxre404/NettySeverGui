package bean;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class Test {
    static  int count=0;
    static final Pattern b = Pattern.compile(new String(new byte[]{(byte) 1}));
    public static String str="{\"actionLink\":\"alipays://platformapi/startapp?appId=60000081\",\"actionName\":\"收款记录\",\"actionTitle\":\"商家服务\"," +
            "\"assistMsg1\":\"今日第17笔收款，共计￥0.17\",\"assistMsg2\":\"用花呗收钱，可支持顾客使用花呗红包\",\"bizMonitor\":\"{\"expireLink\":\"\",\"gmtCreate\":1544284650819," +
            "\"gmtValid\":1546876650809,\"hiddenSum\":\"0\",\"homePageTitle\":\"商家服务: ￥0.01 收款到账通知\"," +
            "\"icon\":\"https://gw.alipayobjects.com/zos/mwalletmng/XYNZOTVpZxkryjGaSsoi.png\",\"id\":\"82a47d764b37ad9df6a68b5ecf57082a5282pep_MDailyBill_new_Push2088802005017821\",\"link\":\"https://render.alipay.com/p/z/merchant-mgnt/simple-order.html?source=mdb_new_card\"," +
            "\"linkName\":\"\",\"msgId\":\"82a47d764b37ad9df6a68b5ecf57082a5282\",\"msgType\":\"NOTICE\",\"operate\":\"UPDATE\",\"status\":\"\"," +
            "\"templateCode\":\"pep_MDailyBill_new_Push\",\"templateId\":\"WALLET-BILL@mbill-pay-fwc-dynamic-v2\",\"templateName\":\"商家服务收款到账\"," +
            "\"templateType\":\"S\",\"title\":\"商家服务·收款到账\",\"userId\":\"2088802005017821\"}\"," +
            "\"cardLink\":\"https://render.alipay.com/p/z/merchant-mgnt/simple-order.html?source=mdb_new_card\",\"content\":\"收款金额￥0.01\"," +
            "\"contentList\":[{\"content\":\"今日第17笔收款，共计￥0.17\",\"isHighlight\":false,\"label\":\"汇总\"},{\"content\":\"用花呗收钱，可支持顾客使用花呗红包\"," +
            "\"isHighlight\":true,\"label\":\"备注\"}],\"expireLink\":\"https://render.alipay.com/p/f/fd-jblxfp45/pages/home/index.html\"," +
            "\"gmtValid\":1546876650809,\"link\":\"https://render.alipay.com/p/z/merchant-mgnt/simple-order.html?source=mdb_new_card\",\"mainAmount\":\"0.01\",\"mainTitle\":\"收款金额\"," +
            "\"templateId\":\"WALLET-BILL@mbill-pay-fwc-dynamic-v2\"}";
    public static  String test=" icon='https://gw.alipayobjects.com/zos/rmsportal/EMWIWDsKUkuXYdvKDdaZ.png', link='alipays://platformapi/startapp?appId=20000003&actionType=toBillDetails&tradeNO=20181209200040011100770030438115&bizType=D_TRANSFER?tagid=MB_SEND_PH', " +
            "linkName='', templateCode='00059_00094_zfzs001', gmtCreate=1544335902743, gmtValid=1546927902731, homePageTitle='支付助手: ￥0.01 二维码收款到账通知', statusFlag='null',";

    public static void main(String[] args) {
        Person.Students origen= Person.stu;
        System.out.println(origen.toString());
        Person mp=new Person();
        Person.Students students = new Person.Students();
        students.setAge(12);
        students.setName("jack");
        mp.setStu(students);
        Person.Students stu=new Person.Students();
        stu.setName("hellowrod");
        stu.setAge(19);
        new Person().setStu(stu);
        Person.Students test= Person.stu;
        System.out.println(test.toString());

//        String textCenter = getTextCenter(str, "\"mainAmount\":\"", "\"");
//        String time = getTextCenter(str, "\"gmtCreate\":", ",");
//        System.out.println("截取的金额:"+textCenter+"获取的时间:"+time);


    }
    static class MyThread extends Thread{

        @Override
        public void run() {
            super.run();
            while (true){
                try {
                    Thread.sleep(1000);
                    System.out.println("helloword"+count);
                    count++;
                if (count==10){
                    interrupt();
                }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }
    public static String good(){

        try {
            return "ko";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("借宿");
        }
        return "jkljlkj1234";
    }
    public static String GetDealData(double data){
        DecimalFormat formater = new DecimalFormat("");
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.UP);
        String format = formater.format(data);
        BigDecimal bd = new BigDecimal(format);
        String str = bd.toPlainString();
        return str;
    }
    public static void test(int i){
        try {
            if (i%2==0)Thread.sleep(5000);
            else Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String getTextCenter(String text,String begin,String end){
        try {
            int b=text.indexOf(begin)+begin.length();
            int e=text.indexOf(end,b);
            return text.substring(b,e);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }
    }
}
