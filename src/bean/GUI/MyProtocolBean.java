package bean.GUI;

import java.util.Arrays;

public class MyProtocolBean {
    //包头
    private byte head;

    //内容长度
    private int length;

    //内容
    private byte[] content;

    public MyProtocolBean(byte head, int length, byte[] content) {
        this.head = head;
        this.length = length;
        this.content = content;
    }

    public byte getHead() {
        return head;
    }

    public int getLength() {
        return length;
    }

    public byte[] getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "MyProtocolBean{" +
                "head=" + head +
                ", length=" + length +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}