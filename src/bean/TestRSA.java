package bean;

public class TestRSA {
    public static void main(String[] args) {

        //测试字符串
        String encryptStr = "merchantId=10000&merchantOrderId=Test_20181020065232&merchantUid=1000&money=0.01¬ifyUrl=http://localhost:11188/test_notify.html&payType=alipay×tamp=1540018352";
        try {
            //加密
            String result = RSAUtil.encrypt(RSAUtil.DEFAULT_PRIVATE_KEY, encryptStr);

            String sign = RSAUtil.rsaSign(RSAUtil.DEFAULT_PRIVATE_KEY, encryptStr);
            System.out.println(sign);


            encryptStr=encryptStr;
            boolean ret=RSAUtil.rsaVerify(RSAUtil.DEFAULT_PUBLIC_KEY,sign,encryptStr);

            System.out.println(ret);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}


