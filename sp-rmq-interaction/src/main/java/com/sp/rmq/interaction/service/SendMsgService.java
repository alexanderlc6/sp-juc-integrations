package com.sp.rmq.interaction.service;

import com.sp.rmq.interaction.domain.SendMsgVO;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * @description: MQ消息处理Service
 * @author: luchao
 * @date: Created in 4/23/22 5:38 PM
 */
public interface SendMsgService {
    /**
     * 发送MQ消息
     * @param sendMsg
     * @return
     */
    Boolean sendMsg(SendMsgVO sendMsg) throws Exception;

    /**
     * 消费MQ消息
     * @return
     */
    Boolean consumeMsg() throws Exception;
}
