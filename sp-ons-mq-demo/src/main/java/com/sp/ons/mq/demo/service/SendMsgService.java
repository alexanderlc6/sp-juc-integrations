package com.sp.ons.mq.demo.service;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import com.sp.ons.mq.demo.config.MqConfig;
import com.sp.ons.mq.demo.domain.SendMsgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @description:
 * @author: luchao
 * @date: Created in 4/24/22 11:39 PM
 */
public interface SendMsgService {
    String testSend(SendMsgVO sendMsg);

    void testConsumer(SendMsgVO sendMsg);
}
