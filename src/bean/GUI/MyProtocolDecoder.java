package bean.GUI;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.Iterator;
import java.util.List;

public class MyProtocolDecoder extends ByteToMessageDecoder {

    private static final int HEADER_SIZE = 5;



    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf bufferIn, List<Object> out) throws Exception {
        // 可读长度必须大于基本长度
        if (bufferIn.readableBytes() < HEADER_SIZE) {
            return;
        }

        int beginIndex = bufferIn.readerIndex();
        byte head = bufferIn.readByte();
//        short i = bufferIn.readShort();

        int length = bufferIn.readInt();
        System.out.println("kkkkkk:"+length);
        byte[] bhead={head};
        if (!new String(bhead).equals("|")){
            bufferIn.readerIndex(beginIndex);
            return;
        }
        if (bufferIn.readableBytes() < length) {
            bufferIn.readerIndex(beginIndex);
            return;
        }

        bufferIn.readerIndex(beginIndex + 5 + length);

        ByteBuf otherByteBufRef = bufferIn.slice(beginIndex, 5 + length);

        otherByteBufRef.retain();

        out.add(otherByteBufRef);
    }
}