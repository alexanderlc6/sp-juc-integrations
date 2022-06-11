package com.sp.netty.integrations.common;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * [Add Description Here]
 *
 * @author luchao Created in 6/5/22 5:01 PM
 */
public class CommonExceptionHandler extends ChannelDuplexHandler {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(cause instanceof RuntimeException){
            System.out.println("Handle biz exception success.");
        }
    }
}
