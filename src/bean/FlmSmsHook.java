package bean;

public class FlmSmsHook {
    public static final String EN_BANK_ABC = "95599";
    public static final String EN_BANK_BCM = "95559";
    public static final String EN_BANK_BOC = "95566";
    public static final String EN_BANK_CCB = "95533";
    public static final String EN_BANK_CIB = "95561";
    public static final String EN_BANK_CITICB = "95558";
    public static final String EN_BANK_CMB = "95555";
    public static final String EN_BANK_CMBC = "95568";
    public static final String EN_BANK_ICBC = "95588";
    public static final String EN_BANK_PSBC = "95580";

    /* JADX WARNING: Removed duplicated region for block: B:66:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01a4  */
    public void onReceived(java.lang.String r10, java.lang.String r11, java.util.Date r12) {
        /*
        r8 = this;
        if (r9 == 0) goto L_0x001e;
    L_0x0002:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "FlmSmsHook from ==> ";
        r0.append(r1);
        r0.append(r10);
        r1 = ", content ==> ";
        r0.append(r1);
        r0.append(r11);
        r0 = r0.toString();
        com.zhiyi.xppay.utils.LogUtil.xLog(r0);
    L_0x001e:
        r0 = 0;
        r1 = "支出";
        r1 = r11.contains(r1);
        if (r1 != 0) goto L_0x01e6;
    L_0x0027:
        r1 = "转出";
        r1 = r11.contains(r1);
        if (r1 == 0) goto L_0x0031;
    L_0x002f:
        goto L_0x01e6;
    L_0x0031:
        r1 = "95533";
        r1 = r10.equals(r1);
        r2 = 1;
        if (r1 == 0) goto L_0x0051;
    L_0x003a:
        r1 = "人民币([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])";
        r1 = java.util.regex.Pattern.compile(r1);
        r1 = r1.matcher(r11);
        r3 = r1.find();
        if (r3 == 0) goto L_0x01a1;
    L_0x004a:
        r0 = r1.group(r2);
        r5 = r0;
        goto L_0x01a2;
    L_0x0051:
        r1 = "95599";
        r1 = r10.equals(r1);
        if (r1 == 0) goto L_0x0070;
    L_0x0059:
        r1 = "人民币([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])";
        r1 = java.util.regex.Pattern.compile(r1);
        r1 = r1.matcher(r11);
        r3 = r1.find();
        if (r3 == 0) goto L_0x01a1;
    L_0x0069:
        r0 = r1.group(r2);
        r5 = r0;
        goto L_0x01a2;
    L_0x0070:
        r1 = "95599";
        r1 = r10.equals(r1);
        if (r1 == 0) goto L_0x008f;
    L_0x0078:
        r1 = "人民币([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])";
        r1 = java.util.regex.Pattern.compile(r1);
        r1 = r1.matcher(r11);
        r3 = r1.find();
        if (r3 == 0) goto L_0x01a1;
    L_0x0088:
        r0 = r1.group(r2);
        r5 = r0;
        goto L_0x01a2;
    L_0x008f:
        r1 = "95555";
        r1 = r10.equals(r1);
        if (r1 == 0) goto L_0x00ae;
    L_0x0097:
        r1 = "人民币([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])";
        r1 = java.util.regex.Pattern.compile(r1);
        r1 = r1.matcher(r11);
        r3 = r1.find();
        if (r3 == 0) goto L_0x01a1;
    L_0x00a7:
        r0 = r1.group(r2);
        r5 = r0;
        goto L_0x01a2;
    L_0x00ae:
        r1 = "95561";
        r1 = r10.equals(r1);
        if (r1 == 0) goto L_0x00cd;
    L_0x00b6:
        r1 = "收入([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])";
        r1 = java.util.regex.Pattern.compile(r1);
        r1 = r1.matcher(r11);
        r3 = r1.find();
        if (r3 == 0) goto L_0x01a1;
    L_0x00c6:
        r0 = r1.group(r2);
        r5 = r0;
        goto L_0x01a2;
    L_0x00cd:
        r1 = "95559";
        r1 = r10.equals(r1);
        if (r1 == 0) goto L_0x00ec;
    L_0x00d5:
        r1 = "转入([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])";
        r1 = java.util.regex.Pattern.compile(r1);
        r1 = r1.matcher(r11);
        r3 = r1.find();
        if (r3 == 0) goto L_0x01a1;
    L_0x00e5:
        r0 = r1.group(r2);
        r5 = r0;
        goto L_0x01a2;
    L_0x00ec:
        r1 = "95580";
        r1 = r10.equals(r1);
        if (r1 == 0) goto L_0x010b;
    L_0x00f4:
        r1 = "金额([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])";
        r1 = java.util.regex.Pattern.compile(r1);
        r1 = r1.matcher(r11);
        r3 = r1.find();
        if (r3 == 0) goto L_0x01a1;
    L_0x0104:
        r0 = r1.group(r2);
        r5 = r0;
        goto L_0x01a2;
    L_0x010b:
        r1 = "95568";
        r1 = r10.equals(r1);
        if (r1 == 0) goto L_0x012a;
    L_0x0113:
        r1 = "存入￥([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])";
        r1 = java.util.regex.Pattern.compile(r1);
        r1 = r1.matcher(r11);
        r3 = r1.find();
        if (r3 == 0) goto L_0x01a1;
    L_0x0123:
        r0 = r1.group(r2);
        r5 = r0;
        goto L_0x01a2;
    L_0x012a:
        r1 = "95588";
        r1 = r10.equals(r1);
        if (r1 == 0) goto L_0x0165;
    L_0x0132:
        r1 = "收入(银联入账)([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])";
        r1 = java.util.regex.Pattern.compile(r1);
        r1 = r1.matcher(r11);
        r3 = r1.find();
        if (r3 == 0) goto L_0x0146;
    L_0x0142:
        r0 = r1.group(r2);
    L_0x0146:
        if (r0 != 0) goto L_0x0163;
    L_0x0148:
        r0 = "(银联入账)";
        r0 = r11.split(r0);
        r0 = r0[r2];
        r1 = "元";
        r0 = r0.split(r1);
        r1 = 0;
        r0 = r0[r1];
        r1 = r0.length();
        r0 = r0.substring(r2, r1);
        r5 = r0;
        goto L_0x01a2;
    L_0x0163:
        r5 = r0;
        goto L_0x01a2;
    L_0x0165:
        r1 = "95566";
        r1 = r10.equals(r1);
        if (r1 == 0) goto L_0x0183;
    L_0x016d:
        r1 = "人民币([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])";
        r1 = java.util.regex.Pattern.compile(r1);
        r1 = r1.matcher(r11);
        r3 = r1.find();
        if (r3 == 0) goto L_0x01a1;
    L_0x017d:
        r0 = r1.group(r2);
        r5 = r0;
        goto L_0x01a2;
    L_0x0183:
        r1 = "95558";
        r1 = r10.startsWith(r1);
        if (r1 == 0) goto L_0x01a1;
    L_0x018b:
        r1 = "存入人民币([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])";
        r1 = java.util.regex.Pattern.compile(r1);
        r1 = r1.matcher(r11);
        r3 = r1.find();
        if (r3 == 0) goto L_0x01a1;
    L_0x019b:
        r0 = r1.group(r2);
        r5 = r0;
        goto L_0x01a2;
    L_0x01a1:
        r5 = r0;
    L_0x01a2:
        if (r5 == 0) goto L_0x01e5;
    L_0x01a4:
        r0 = com.zhiyi.xppay.hook.CustomApplcation.getContext();
        r1 = "manual_uid";
        r0 = com.zhiyi.xppay.utils.AbSharedUtil.getString(r0, r1);
        r1 = new java.text.SimpleDateFormat;
        r2 = "yyyyMMddHHmmss";
        r1.<init>(r2);
        r1 = r1.format(r12);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r10);
        r10 = "-";
        r2.append(r10);
        r2.append(r0);
        r10 = "-";
        r2.append(r10);
        r2.append(r5);
        r10 = "-";
        r2.append(r10);
        r2.append(r1);
        r4 = r2.toString();
        r3 = 0;
        r1 = r8;
        r2 = r9;
        r6 = r11;
        r7 = r12;
        r1.notify(r2, r3, r4, r5, r6, r7);
    L_0x01e5:
        return;
    L_0x01e6:
        r9 = "FlmSmsHook err ==> 短信包含支出，转出文字，不符合规则";
        com.zhiyi.xppay.utils.LogUtil.xLog(r9);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zhiyi.xppay.hook.FlmSmsHook.onReceived(android.content.Context, java.lang.String, java.lang.String, java.util.Date):void");
    }

    public String getBillType() {
        return PayType.FULINMEN.toString();
    }
}
