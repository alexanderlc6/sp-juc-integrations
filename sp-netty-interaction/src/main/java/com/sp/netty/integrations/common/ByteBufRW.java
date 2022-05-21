package com.sp.netty.integrations.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * [Add Description Here]
 *
 * @author luchao Created in 5/21/22 1:20 PM
 */
public class ByteBufRW {

    private static void printByteBufInfo(String step, ByteBuf buffer){
        System.out.println("-----" + step + "-----");
        System.out.println("readerIndex():" + buffer.readerIndex());
        System.out.println("writerIndex():" + buffer.writerIndex());
        System.out.println("isReadable():" + buffer.isReadable());
        System.out.println("isWritable():" + buffer.isWritable());
        System.out.println("readableBytes():" + buffer.readableBytes());
        System.out.println("writableBytes():" + buffer.writableBytes());
        System.out.println("maxWritableBytes():" + buffer.maxWritableBytes());
        System.out.println("capacity():" + buffer.capacity());
        System.out.println("maxCapacity:" + buffer.maxCapacity());
    }

    public static class ByteBufTest {
        public static void main(String[] args) {
            ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(6, 10);
            printByteBufInfo("ByteBufAllocator.buffer(5,10)", buf);

            buf.writeBytes(new byte[]{1,2});
            printByteBufInfo("write 2 Bytes:", buf);

            buf.writeInt(100);
            printByteBufInfo("write int 100", buf);

            buf.writeBytes(new byte[]{3,4,5});
            printByteBufInfo("write 3 Bytes:", buf);

            byte[] read = new byte[buf.readableBytes()];
            buf.readBytes(read);
            printByteBufInfo("readBytes(" + buf.readableBytes() + ")", buf);

            printByteBufInfo("BeforeGetAndSet", buf);
            System.out.println("getInt(2):" + buf.getInt(2));
            buf.setByte(1,0);
            System.out.println("getByte(1):" + buf.getByte(1));
            printByteBufInfo("AfterGetAndSet", buf);
        }
    }
}
