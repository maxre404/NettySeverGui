package bean;

public enum PayType {
    ALIPAY_ANTBANK("支付宝转卡短信监控", "请填写手机号码", "支付宝", 0),
    ALIPAY_COLLECT("支付宝收款", "自动读取支付宝PID", "支付宝", 1),
    ALIPAY_REDENVELOP("支付宝红包监控", "自动读取支付宝PID", "支付宝", 2),
    ALIPAY_TRANSFER("支付宝转账", "自动读取支付宝PID", "支付宝", 3),
    ALIPAY_TRANSFER_UID("支付宝二维码收款", "自动读取支付宝PID", "支付宝", 4),
    WECHAT_TRANSFER("微信转账", "自动读取微信PID", "微信", 5),
    FULINMEN("付临门短信监控", "请填写手机号码", "付临门", 6);
    
    private String appName;
    private String hint;
    private int index;
    private String name;

    private PayType(String str, String str2, String str3, int i) {
        this.name = str;
        this.appName = str3;
        this.index = i;
    }

    public static String getName(int i) {
        for (PayType payType : values()) {
            if (payType.getIndex() == i) {
                return payType.name;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public String getHint() {
        return this.hint;
    }

    public void setHint(String str) {
        this.hint = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }
}