package com.sp.netty.integrations.encoder;

import com.sp.netty.integrations.model.ResponseSample;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description:
 * @author: luchao
 * @date: Created in 5/15/22 6:40 PM
 */
public class ResponseSampleEncoder extends MessageToByteEncoder<ResponseSample> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseSample msg, ByteBuf out) throws Exception {
        if(msg != null){
            out.writeBytes(msg.getCode().getBytes());
            out.writeBytes(msg.getData().getBytes());
            out.writeLong(msg.getTimestamp());
        }
    }
}
