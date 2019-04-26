package bean.GUI;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class testApi {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton Button;
    private JButton btn_loging;

    public testApi() {
        btn_loging.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
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
                for(ChannelHandlerContext ctx:ServerHandler.Clients.values())
                {
                    Loging loging=new Loging();
                    loging.setLoginStatus(1);
                    loging.setPayType("alipay");
                    TcpParam tcp=new TcpParam(100);
                    String moeny = textField2.getText().toString().trim();
                    tcp.write(moeny);
                    byte[] param2 = tcp.getParam2();
                    byte[] bytes = new byte[0];
                    try {
                        bytes = BaseNetTool.appendHead2(param2);
                        ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
                        ctx.writeAndFlush(byteBuf);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("testApi");
        frame.setContentPane(new testApi().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
