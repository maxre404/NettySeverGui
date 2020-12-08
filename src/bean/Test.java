package bean;

import javax.naming.Context;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    static  int count=0;
    static final Pattern b = Pattern.compile(new String(new byte[]{(byte) 1}));

    public static void main(String[] args) {

        String org="他是中国人123.924  哈哈 好像是哦1222";

//        Pattern compile = Pattern.compile("\\d+\\.*\\d{0,2}");
        Pattern compile = Pattern.compile("\\D+");
        Matcher matcher = compile.matcher(org);
        if (matcher.find()){
            System.out.println(matcher.group());
            System.out.println(matcher.replaceAll(" "));
        }


//        onReceived( BankType.ABC.getAddress(), "【中国农业银行】冉显跃于09月14日15:22向您尾号7770账户完成银联入账交易人民币299.99，余额2299.97。", null);
//        onReceived( BankType.ABC.getAddress(), "【中国农业银行】何良峰于10月01日22:59向您尾号3578账户完成银联入账交易人民币49.98，余额206.86。", null);
//        onReceived( BankType.CMB.getAddress(), "您账户0339于10月17日16:18二维码收款（王杰），人民币5.00元[招商银行]", null);
        onReceived( BankType.CMB.getAddress(), "您账户0339于10月16日14:35二维码收款（魏彩城/wWnWMgUr），人民币299.99元[招商银行]", null);
//        onReceived( BankType.CIB.getAddress(), "16日12:22账户*0681*跨行代付收入999.97元，余额7002.83元[兴业银行]", null);
//        onReceived( BankType.BOC.getAddress(), "您的借记卡账户8793，于10月16日POS收入人民币99.85元，交易后余额2663.30【中国银行】", null);
//        onReceived( "95566", "您的借记卡账户穆绍贞，于10月17日POS收入人民币5.00元，交易后余额5.00【中国银行】", null);
        onReceived(BankType.CCB.getAddress(),"卜茹雪10月19日10时28分向您尾号3802的储蓄卡账户银联入账收入人民币149.99元,活期余额57834.88元。[建设银行]",null);
        Socket client = null;
//        try {
//            client = new Socket("18.162.216.235", 11199);
////            client = new Socket("192.168.3.19", 11199);
//            client.setSoTimeout(10000);
//            System.out.println("连接成功");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("++++超时++++");
//        }
    }
    public static void teststr(String...str){
        System.out.println(str[0]);
    }
    public static void onReceived( String str, String str2, Date date) {
        String str3 = str;
        String str4 = str2;
        String str5 = "";
        String str6 = "";
        String str7 = "";
        String str8 = "";
        String str9 = "";
        String str10 = "";
        Pattern pattern = getPattern(str3, str4);
        if (pattern != null) {
            Matcher matcher = pattern.matcher(str4);
            if (matcher.find()) {
                String group;
                String group2;
                String group3;
                String group4;
                String no;
                if (str3.equals(BankType.CCB.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(1);
                    group3 = matcher.group(4);
                    group4 = matcher.group(2);
                    no = getNo(BankType.CCB.toString(), group2, group, group3, group4);
                    str7 = no;
//                    notify(context, BankType.CCB.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.equals(BankType.CMBC.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(1);
                    group3 = matcher.group(4);
                    group4 = matcher.group(2);
                    no = getNo(BankType.CMBC.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.CMBC.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.equals(BankType.ABC.getAddress())) {
                    if (str4.contains("支付宝（中国）网络技术有限公司")) {
                        str3 = matcher.group(3);
                        str5 = matcher.group(2);
                        group2 = str3;
                        group = str5;
                        group3 = matcher.group(4);
                        group4 = matcher.group(1);
                    } else {
                        str3 = matcher.group(3);
                        str5 = matcher.group(1);
                        group2 = str3;
                        group = str5;
                        group3 = matcher.group(4);
                        group4 = matcher.group(2);
                    }
                    no = getNo(BankType.ABC.toString(), group, group2, group3, group4);
                    str7 = no;
                    notify(null, BankType.ABC.toString(), str7, group2, str2, date);
                    str6 = str4;
                    str8 = group;
                    str5 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.equals(BankType.ICBC.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(1);
                    group3 = matcher.group(4);
                    group4 = matcher.group(2);
                    no = getNo(BankType.ICBC.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.ICBC.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.equals(BankType.PSBC.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(2);
                    group3 = matcher.group(4);
                    group4 = matcher.group(1);
                    no = getNo(BankType.PSBC.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.PSBC.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.equals(BankType.SPDB.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(1);
                    group3 = matcher.group(4);
                    group4 = matcher.group(2);
                    no = getNo(BankType.SPDB.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.SPDB.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.equals(BankType.CMB.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(1);
                    group3 = "BALANCE";
                    group4 = matcher.group(2);
                    no = getNo(BankType.CMB.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.CMB.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.equals(BankType.CEB.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(1);
                    group3 = matcher.group(4);
                    group4 = matcher.group(2);
                    no = getNo(BankType.CEB.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.CEB.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.equals(BankType.BOC.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(1);
                    group3 = matcher.group(4);
                    group4 = matcher.group(2);
                    no = getNo(BankType.BOC.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.BOC.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.equals(BankType.BOB.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(1);
                    group3 = matcher.group(4);
                    group4 = matcher.group(2);
                    no = getNo(BankType.BOB.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.BOB.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.equals(BankType.CIB.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(2);
                    group3 = matcher.group(4);
                    group4 = matcher.group(1);
                    no = getNo(BankType.CIB.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.CIB.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.equals(BankType.CZB.getAddress())) {
                    group = matcher.group(1);
                    group2 = matcher.group(3);
                    group3 = matcher.group(2);
                    group4 = matcher.group(4);
                    no = getNo(BankType.CZB.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.CZB.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.equals(BankType.HXB.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(1);
                    group3 = matcher.group(4);
                    group4 = matcher.group(2);
                    no = getNo(BankType.HXB.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.HXB.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.endsWith(BankType.HNRCU.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(1);
                    group3 = matcher.group(4);
                    group4 = matcher.group(2);
                    no = getNo(BankType.HNRCU.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.HNRCU.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                } else if (str3.endsWith(BankType.ZZBANK.getAddress())) {
                    group = matcher.group(3);
                    group2 = matcher.group(2);
                    group3 = matcher.group(4);
                    group4 = matcher.group(1);
                    no = getNo(BankType.ZZBANK.toString(), group2, group, group3, group4);
                    str7 = no;
                    notify(null, BankType.ZZBANK.toString(), str7, group, str2, date);
                    str6 = str4;
                    str5 = group;
                    str8 = group2;
                    str9 = group3;
                    str10 = group4;
                }
            }
        }
            PrintStream printStream = System.out;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("money=> ");
            stringBuilder2.append(str5);
            stringBuilder2.append(", bankcard => ");
            stringBuilder2.append(str8);
            stringBuilder2.append(", balance => ");
            stringBuilder2.append(str9);
            stringBuilder2.append(", time=>");
            stringBuilder2.append(str10);
            stringBuilder2.append(", no => ");
            stringBuilder2.append(str7);
            stringBuilder2.append(", mark => ");
            stringBuilder2.append(str6);
        System.out.println(stringBuilder2.toString());


    }
    public static void notify(Context context, String str, String str2, String str3, String str4, Date date) {
        if (context != null && date != null) {
//            if (AppUtil.isExceedTime(date.getTime(), 600)) {
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("notify ==> no = ");
//                stringBuilder.append(str2);
//                stringBuilder.append(" 短信超过10小时，直接放弃！");
//                LogUtil.xLog(stringBuilder.toString());
//                return;
//            }
            System.out.println("str:"+str+" str2:"+str2+" str3:"+str3+" str4:"+str4);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("notify ==> no = ");
            stringBuilder2.append(str2);
            stringBuilder2.append(", money = ");
            stringBuilder2.append(str3);
//            LogUtil.xLog(stringBuilder2.toString());
//            Intent intent = new Intent();
//            intent.putExtra("bill_no", str2);
//            intent.putExtra("order_no", str2);
//            intent.putExtra("bill_money", str3);
//            intent.putExtra("bill_mark", str4);
//            intent.putExtra("bill_type", getBillType());
//            intent.putExtra("bill_extra", str);
//            intent.putExtra("dt", date.getTime());
//            intent.setAction(AppConst.SMS_BILLSUCCESS_ACTION);
//            context.sendBroadcast(intent);
        }
    }


    public static String getNo(String str, String str2, String str3, String str4, String str5) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("-");
        stringBuilder.append(str2);
        stringBuilder.append("-");
        stringBuilder.append(str3);
        stringBuilder.append("-");
        stringBuilder.append(str4);
        stringBuilder.append("-");
        stringBuilder.append(str5);
        return stringBuilder.toString();
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


    public static Pattern getPattern(String str, String str2) {
        if (str.equals(BankType.CCB.getAddress())) {
            return Pattern.compile("(.+)(\\d+月\\d+日\\d+时\\d+分).+人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元.+余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元。\\[建设银行\\]");
//            return Pattern.compile(str2.contains("银联入账") ? "您尾号(\\d+)的储蓄卡账户(\\d+月\\d+日\\d+时\\d+分)银联入账收入人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元.+活期余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元。\\[建设银行\\]" : "您尾号(\\d+).+(\\d+月\\d+日\\d+时\\d+分)支付机构提现收入人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元.+余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元。\\[建设银行\\]");
        } else if (str.equals(BankType.CMBC.getAddress())) {
            return Pattern.compile("账户\\*(\\d+)于(.+)存入￥([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元.+余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元.+民生银行");
        } else {
            if (str.equals(BankType.ABC.getAddress())) {
//                str = str2.contains("支付宝（中国）网络技术有限公司") ? str2.contains("银联入账") ? "【中国农业银行】支付宝（中国）网络技术有限公司于(.+)向您尾号(\\d+)账户完成银联入账交易人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)" : "【中国农业银行】支付宝（中国）网络技术有限公司于(.+)向您尾号(\\d+)账户完成代付交易人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)" : str2.contains("银联入账") ? "【中国农业银行】您尾号(\\d+)账户(.+)完成银联入账交易人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)" : "【中国农业银行】您尾号(\\d+)账户(.+)完成代付交易人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)";
                str = str2.contains("支付宝（中国）网络技术有限公司") ? str2.contains("银联入账") ? "【中国农业银行】支付宝（中国）网络技术有限公司于(.+)向您尾号(\\d+)账户完成银联入账交易人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)" : "【中国农业银行】支付宝（中国）网络技术有限公司于(.+)向您尾号(\\d+)账户完成代付交易人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)" : str2.contains("银联入账") ? "【中国农业银行】.+于(.+)向您尾号(\\d+)账户完成银联入账交易人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)" : "【中国农业银行】您尾号(\\d+)账户(.+)完成代付交易人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)";
                return Pattern.compile(str);
            } else if (str.equals(BankType.ICBC.getAddress())) {
                return Pattern.compile(str2.contains("快捷支付收入") ? "您尾号(\\d+).+(\\d+月\\d+日\\d+:\\d+)快捷支付收入\\(.+支付宝转账支付宝\\)([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元.+工商银行" : "您尾号(\\d+).+(\\d+月\\d+日\\d+:\\d+)网上银行收入\\(.+\\)([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元.+工商银行");
            } else if (str.equals(BankType.PSBC.getAddress())) {
                return Pattern.compile("邮储银行.+(\\d+年\\d+月\\d+日\\d+:\\d+)您尾号(\\d+)账户提现金额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元.+");
            } else {
                if (str.equals(BankType.SPDB.getAddress())) {
                    return Pattern.compile("您尾号(\\d+)卡人民币活期(\\d+:\\d+)存入([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+).+可用余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)。【浦发银行】");
                }
                if (str.equals(BankType.CMB.getAddress())) {
                    if (str2.contains("二维码收款")){
                                                //您账户0339于10月17日16:18二维码收款（王杰），人民币5.00元[招商银行]
                                                //您账户0339于10月17日16:18二维码收款，人民币5.00元[招商银行]
//                        return  Pattern.compile("您账户(\\d+)于(\\d+月\\d+日\\d+:\\d+)二维码收款（([\\u4e00-\\u9fa5])）,人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元[招商银行]");
                        return  Pattern.compile("您账户(\\d+)于(\\d+月\\d+日\\d+:\\d+).+人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元\\[招商银行\\]");
                    }
                    return Pattern.compile(str2.contains("银联入账") ? "您账户(\\d+)于(\\d+月\\d+日\\d+:\\d+)银联入账人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元.+[招商银行]" : "您账户(\\d+)于(\\d+月\\d+日\\d+:\\d+)收款人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)，.+[招商银行]");
                } else if (str.equals(BankType.CEB.getAddress())) {
                    return Pattern.compile("尾号(\\d+)账户(\\d+:\\d+)存入([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元，摘要:.+[光大银行]");
                } else {
                    if (str.equals(BankType.BOC.getAddress())) {
                        return Pattern.compile("您的借记卡账户(.+)，于(\\d+月\\d+日).+收入人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元，交易后余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)【中国银行】");
                    }
                    if (str.equals(BankType.BOB.getAddress())) {
                        return Pattern.compile(str2.startsWith("您尾号为") ? "您尾号为(\\d+)的京卡于(\\d+年\\d+月\\d+日\\d+:\\d+)收入([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元。活期余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元.+" : "【北京银行】您尾号为(\\d+)的京卡于(\\d+年\\d+月\\d+日\\d+:\\d+)收入([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元。活期余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元。");
                    } else if (str.equals(BankType.CIB.getAddress())) {

                        return Pattern.compile("(\\d+日.+)账户(\\*\\d+\\*).+收入([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元\\[兴业银行\\]");
                    } else {
                        if (str.equals(BankType.CZB.getAddress())) {
                            return Pattern.compile("【浙商银行】您的商卡增加人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元，可用余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元。账号末四位(\\d+)，交易时间(\\d+月\\d+日\\d+时\\d+分)，.+。如有疑问请联系95527");
                        }
                        if (str.equals(BankType.HXB.getAddress())) {
                            return Pattern.compile("您的账户(\\d+)于(\\d+月\\d+日\\d+:\\d+)收入人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元。收到网联付款。【华夏银行】");
                        }
                        if (str.endsWith(BankType.HNRCU.getAddress())) {
                            return Pattern.compile("【河南农信】您尾号(\\d+)于(\\d+年\\d+月\\d+日\\d+时\\d+分)通过本行业务系统账号 转入([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元，余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元。");
                        }
                        if (!str.endsWith(BankType.ZZBANK.getAddress())) {
                            return null;
                        }
                        return Pattern.compile(str2.startsWith("【郑州银行】") ? "【郑州银行】(\\d+月\\d+日\\d+:\\d+)，您尾号为(\\d+)的卡,支付宝,收款人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元,余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元" : "(\\d+月\\d+日\\d+:\\d+)，您尾号为(\\d+)的卡,支付宝,收款人民币([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元,余额([1-9][\\d,]*.\\d*|0.\\d*[1-9]\\d*|\\d+)元。【郑州银行】");
                    }
                }
            }
        }
    }



}
