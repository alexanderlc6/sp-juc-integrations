package com.sp.ons.mq.demo.order;

import com.aliyun.openservices.ons.api.bean.OrderProducerBean;
import com.sp.ons.mq.demo.config.MqConfig;
import com.zhongan.zaenc.ZaencException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderProducerClient {

    @Autowired
    private MqConfig mqConfig;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public OrderProducerBean buildOrderProducer() {
        OrderProducerBean orderProducerBean = new OrderProducerBean();
        try {
            orderProducerBean.setProperties(mqConfig.getMqPropertie());
        } catch (ZaencException e) {
            e.printStackTrace();
        }
        return orderProducerBean;
    }

}
