package com.sp.ons.mq.demo.normal;

import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.sp.ons.mq.demo.config.MqConfig;
import com.zhongan.zaenc.ZaencException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerClient {

    @Autowired
    private MqConfig mqConfig;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean buildProducer() {
        ProducerBean producer = new ProducerBean();
        try {
            producer.setProperties(mqConfig.getMqPropertie());
        } catch (ZaencException e) {
            e.printStackTrace();
        }
        return producer;
    }

}
