package com.sp.ons.mq.demo.transaction;

import com.aliyun.openservices.ons.api.bean.TransactionProducerBean;
import com.sp.ons.mq.demo.config.MqConfig;
import com.zhongan.zaenc.ZaencException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionProducerClient {

    @Autowired
    private MqConfig mqConfig;

    @Autowired
    private DemoLocalTransactionChecker localTransactionChecker;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public TransactionProducerBean buildTransactionProducer() {
        TransactionProducerBean producer = new TransactionProducerBean();
        try {
            producer.setProperties(mqConfig.getMqPropertie());
        } catch (ZaencException e) {
            e.printStackTrace();
        }
        producer.setLocalTransactionChecker(localTransactionChecker);
        return producer;
    }

}
