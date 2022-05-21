package com.sp.netty.integrations.model;

import lombok.Data;

/**
 * @description: 响应消息Model
 * @author: luchao
 * @date: Created in 5/15/22 6:42 PM
 */
@Data
public class ResponseSample {

    public ResponseSample(String code, String data, Long timestamp) {
        this.code = code;
        this.data = data;
        this.timestamp = timestamp;
    }

    private String code;

    private String data;

    private Long timestamp;
}
