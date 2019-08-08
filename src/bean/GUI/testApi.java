package bean.GUI;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class testApi implements SMessage {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton Button;
    private JButton btn_loging;
    private JTextField textField3;
    public  JTextArea logArea;
    private JButton btn_clear;
    private JLabel text_ip;
    public static String holdName="";
    private static String bankNumber="";
    public static String money="";

    public testApi() {
        App.getApp().addMessageListener("test",testApi.this);
        textField3.setDocument(new NumOnlyDocument());
        text_ip.setBorder(new EmptyBorder(0, 0, 30, 0));
        text_ip.setText("当前Ip地址:"+getIp());
        btn_loging.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new WebServer(8080);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        try {
//                            new HttpServer().start();
//                        } catch (InterruptedException e1) {
//                            e1.printStackTrace();
//                        }
                        new NettyServer().start();
                    }
                }).start();
                btn_loging.setText("服务器开启成功");
                btn_loging.setEnabled(false);

            }
        });
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ServerHandler.Clients.size()==0){
                    appendMessage("无可用矿机");
                    return;
                }
                for(ChannelHandlerContext ctx:ServerHandler.Clients.values())
                {
                    holdName = textField1.getText().toString().trim();
                    bankNumber = textField2.getText().toString().trim();
                    money = textField3.getText().toString().trim();
                    if ("".equals(holdName)||"".equals(bankNumber)||"".equals(money)){
                        JOptionPane.showConfirmDialog(null, "输入框不能为空", "温馨提示",
                                JOptionPane.YES_NO_OPTION);
                        return;
                    }
                    Loging loging=new Loging();
                    loging.setLoginStatus(1);
                    loging.setPayType("alipay");
                    TcpParam tcp=new TcpParam(205);
                    tcp.write(holdName);
                    tcp.write(bankNumber);
                    tcp.write(money);
                    byte[] param2 = tcp.getParam2();
                    byte[] bytes = new byte[0];
                    try {
                        bytes = BaseNetTool.appendHead2(param2);
                        ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
                        ctx.writeAndFlush(byteBuf);
                        appendMessage("开始查询余额");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btn_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("testApi");
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setContentPane(new testApi().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public String getIp(){
        try {
            InetAddress   ip4 = Inet4Address.getLocalHost();
            return ip4.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void messageReceived(int cmd, byte[] data) {
        switch (cmd){
            case 206:
                TcpRespond tcp=new TcpRespond(data,4);
                String remoney = tcp.getUTF_8().replaceAll(",","");
                appendMessage("获取金额:"+remoney);
               if ( Double.valueOf(remoney)<Double.valueOf(money)){
                    appendMessage("矿机金额不够");
                    return;
               }
                TcpParam param=new TcpParam(100);
                param.write(holdName);
                param.write(bankNumber);
                param.write(money);
                try {
                    byte[] bytes = BaseNetTool.appendHead2(param.getParam2());
                    ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
                    for(ChannelHandlerContext ctx:ServerHandler.Clients.values())
                    {
                        ctx.writeAndFlush(byteBuf);
                    }
                    appendMessage("请求转账 金额:"+money+" 姓名:"+holdName+"银行卡号:"+bankNumber);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case 208:
                TcpRespond tcpResult=new TcpRespond(data,4);
                int statu = tcpResult.getInt();
                String message = tcpResult.getUTF_8();
                if (1==statu){
                    appendMessage("转账成功");
                }else{
                    appendMessage("转账失败,原因:"+message);
                }
                break;
            case 0:
                TcpRespond ttt=new TcpRespond(data,4);
                appendMessage(" 矿机下线:"+ttt.getUTF_8());
                break;
            case 1:
                TcpRespond gggg=new TcpRespond(data,4);
                appendMessage(" 矿机上线:"+gggg.getUTF_8());
                break;
        }
    }

    @Override
    public void MoneyChange(double money) {

    }
    public void appendMessage(String message){
        logArea.append(message+"\n");
        int length = logArea.getText().length();
        logArea.setCaretPosition(length);
    }

}
