package bean;

public enum BankType {
    CCB(FlmSmsHook.EN_BANK_CCB, "建设银行", AddressMatchType.EQUAL),
    CMBC(FlmSmsHook.EN_BANK_CMBC, "民生银行", AddressMatchType.EQUAL),
    ABC(FlmSmsHook.EN_BANK_ABC, "农业银行", AddressMatchType.EQUAL),
    ICBC(FlmSmsHook.EN_BANK_ICBC, "工商银行", AddressMatchType.EQUAL),
    PSBC(FlmSmsHook.EN_BANK_PSBC, "邮储银行", AddressMatchType.EQUAL),
    SPDB("95528", "浦发银行", AddressMatchType.EQUAL),
    CMB(FlmSmsHook.EN_BANK_CMB, "招商银行", AddressMatchType.EQUAL),
    CEB("95595", "光大银行", AddressMatchType.EQUAL),
    BOC(FlmSmsHook.EN_BANK_BOC, "中国银行", AddressMatchType.EQUAL),
    BOB("95526", "北京银行", AddressMatchType.EQUAL),
    CIB(FlmSmsHook.EN_BANK_CIB, "兴业银行", AddressMatchType.EQUAL),
    CZB("95527", "浙商银行", AddressMatchType.EQUAL),
    HXB("95577", "华夏银行", AddressMatchType.EQUAL),
    HNRCU("96288", "河南农信", AddressMatchType.ENDWITH),
    ZZBANK("67585", "郑州银行", AddressMatchType.ENDWITH);
    
    private String address;
    private AddressMatchType addressMatchType;
    private String name;

    private BankType(String str, String str2, AddressMatchType addressMatchType) {
        this.address = str;
        this.name = str2;
        this.addressMatchType = addressMatchType;
    }

    public String getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }

    public AddressMatchType getAddressMatchType() {
        return this.addressMatchType;
    }

    public BankType getBankType(int i) {
        return values()[i];
    }

    public static String[] getBankNames() {
        BankType[] values = values();
        String[] strArr = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            strArr[i] = values[i].getName();
        }
        return strArr;
    }
}