package test;

public class test {
    public static void main(String[] args) {
        String str="通过支付宝向你的银行卡（尾号0515）转账0.02元已到账";
        System.out.println(getTextCenter(str,"转账","元"));

    }
    public static String getTextCenter(String str, String str2, String str3) {
        try {
            int indexOf = str.indexOf(str2) + str2.length();
            return str.substring(indexOf, str.indexOf(str3, indexOf));
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
