package bean.GUI;
import java.util.HashMap;
import java.util.Map;

public class App {
    private static App instance;
    private boolean isSocketLoging=false;//socket连接是否已登录
    private boolean isDisConnect;//是否主动断开
    private double lefMoney=-1;//余额
    private App() {}
    private Map<String, SMessage> map_message=new HashMap<>();
    private String userName="";
    private String passWord="";
    private String isSelfMerchant="";
    public static App getApp() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }
    public boolean isSocketLoging() {
        return isSocketLoging;
    }

    public void setSocketLoging(boolean socketLoging) {
        isSocketLoging = socketLoging;
    }
    public boolean isDisConnect() {
        return isDisConnect;
    }

    public void setDisConnect(boolean disConnect) {
        isDisConnect = disConnect;
    }

    public double getLefMoney() {
        return lefMoney;
    }

    public void setLefMoney(double lefMoney) {
        this.lefMoney = lefMoney;
    }

    public void addMessageListener(String page,SMessage message){
        if (!map_message.containsKey(page)){
            map_message.put(page,message);
        }
    }
    public void removeMessageListener(String page){
        map_message.remove(page);
    }

    public Map<String, SMessage> getMap_message() {
        return map_message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getIsSelfMerchant() {
        return isSelfMerchant;
    }

    public void setIsSelfMerchant(String isSelfMerchant) {
        this.isSelfMerchant = isSelfMerchant;
    }
}
