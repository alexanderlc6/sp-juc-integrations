package com.sp.rmq.interaction.service;

import com.sp.rmq.interaction.domain.SendMsgVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @description: MQ消息处理Service实现类
 * @author: luchao
 * @date: Created in 4/23/22 5:39 PM
 */
@Service
@Slf4j
public class SendMsgServiceImpl implements SendMsgService {
    /**
     * 发送MQ消息
     * @param sendMsg
     * @return
     */
    @Override
    public Boolean sendMsg(SendMsgVO sendMsg) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("test-grp1");
        producer.setInstanceName("TestInst-" + RandomUtils.nextInt());
        producer.setNamesrvAddr("localhost:9876");
        producer.setVipChannelEnabled(false);

        try {
            producer.start();
        }catch (MQClientException e){
            log.error("Send MQ message failed!", e);
            return false;
        }


        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest2", "tag-a",
                    ("test-str-" + RandomUtils.nextInt()).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }

        producer.shutdown();
        return true;
    }

    /**
     * 消费MQ消息
     * @return
     */
    @Override
    public Boolean consumeMsg() throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-grp1");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setInstanceName("consumeInst-" + RandomUtils.nextInt());
        consumer.subscribe("TopicTest2", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Received new messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("Consumer completed.%n");
        return true;
    }
}
