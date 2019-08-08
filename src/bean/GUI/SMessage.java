package bean.GUI;

public interface SMessage {
    void  messageReceived(int cmd, byte[] data);
    void  MoneyChange(double money);
}
