package com.sp.rmq.interaction.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description: MQ消息VO
 * @author: luchao
 * @date: Created in 4/23/22 5:36 PM
 */
@Data
public class SendMsgVO {
    /**
     * MQ消息发送方式(1-同步,2-异步,3-单向)
     */
    private Integer sendType;

    /**
     * 消息内容
     */
    @NotBlank(message = "消息内容不能为空!")
    private String message;
}
