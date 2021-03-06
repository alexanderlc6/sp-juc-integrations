package com.sp.ons.mq.demo.service;

import com.aliyun.openservices.ons.api.*;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import com.sp.ons.mq.demo.config.MqConfig;
import com.sp.ons.mq.demo.domain.SendMsgVO;
import com.sp.ons.mq.demo.normal.DemoMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @description:
 * @author: luchao
 * @date: Created in 4/24/22 11:39 PM
 */
@Service
public class SendMsgServiceImpl implements SendMsgService {
    //普通消息的Producer 已经注册到了spring容器中，后面需要使用时可以直接注入到其它类中
    @Autowired
    private ProducerBean producer;

//    @Autowired
//    private ConsumerBean consumerBean;

    @Autowired
    private MqConfig mqConfig;

    public String testSend(SendMsgVO sendMsg) {
        StringBuilder stbErrors = new StringBuilder();
        //循环发送消息
//        for (int i = 0; i < 100; i++) {
            Message msg = new Message( //
                    // Message所属的Topic
                    mqConfig.getTopic(),
                    // Message Tag 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在MQ服务器过滤
                    mqConfig.getTag(),
                    // Message Body 可以是任何二进制形式的数据， MQ不做任何干预
                    // 需要Producer与Consumer协商好一致的序列化和反序列化方式
                    "ZA Infra test-msg".getBytes());
            // 设置代表消息的业务关键属性，请尽可能全局唯一
            // 以方便您在无法正常收到消息情况下，可通过MQ 控制台查询消息并补发
            // 注意：不设置也不会影响消息正常收发
            msg.setKey("ZA-ONS-TEST-001");
//            msg.putUserProperties("baggage", "INJECTED_OTEL_CONTEXT=cd");
            // 发送消息，只要不抛异常就是成功
            try {
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
                assert sendResult != null;
            } catch (ONSClientException e) {
//                System.out.println("发送失败:" + e.getMessage());
                //出现异常意味着发送失败，为了避免消息丢失，建议缓存该消息然后进行重试。
                stbErrors.append("发送失败:" +e.getMessage());
            }
//        }

        return stbErrors.toString();
    }

    public void testConsumer(SendMsgVO sendMsg){
//        new ImportSelector(){
//            @Override
//            public String[] selectImports(AnnotationMetadata annotationMetadata) {
//                return new String[]{"com.aliyun.openservices.springboot.example.normal.ConsumerClient"};
//            }
//        };

//        DefaultMQPushConsumer mqPushConsumer = new DefaultMQPushConsumer("test-cons-grp1");
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(PropertyKeyConst.GROUP_ID, mqConfig.getGroupId());
        consumerProperties.setProperty(PropertyKeyConst.AccessKey, mqConfig.getAccessKey());
        consumerProperties.setProperty(PropertyKeyConst.SecretKey, mqConfig.getSecretKey());
        consumerProperties.setProperty(PropertyKeyConst.NAMESRV_ADDR, mqConfig.getNameSrvAddr());
        Consumer consumer = ONSFactory.createConsumer(consumerProperties);
        consumer.subscribe(mqConfig.getTopic(), mqConfig.getTag(), new DemoMessageListener());
        consumer.start();
        System.out.println("Consumer start success.");

        //等待固定时间防止进程退出
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
