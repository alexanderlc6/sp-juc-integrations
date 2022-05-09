package com.sp.ons.mq.demo.controller;

import com.sp.framework.common.utils.ResponseUtil;
import com.sp.framework.common.vo.ResponseVO;

import com.sp.ons.mq.demo.domain.SendMsgVO;
import com.sp.ons.mq.demo.service.SendMsgServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: MQ消息测试
 * @author: luchao
 * @date: Created in 4/23/22 5:32 PM
 */
@RestController
@RequestMapping("/ons")
public class SendMsgController {
    @Autowired
    SendMsgServiceImpl sendMsgService;

    /**
     * 发送MQ消息
     * @param sendMsg
     * @return
     * @throws Exception
     */
    @PostMapping("/send")
    public ResponseVO sendMsg(@RequestBody SendMsgVO sendMsg) throws Exception {
        String result =sendMsgService.testSend(sendMsg);
        return StringUtils.isNotBlank(result) ? ResponseUtil.getFailure(result) : ResponseUtil.getSuccess();
    }

    /**
     * 消费MQ消息
     * @param sendMsg
     * @return
     * @throws Exception
     */
    @PostMapping("/consume")
    public ResponseVO consumeMsg(@RequestBody SendMsgVO sendMsg) throws Exception {
        sendMsgService.testConsumer(sendMsg);
        return ResponseUtil.getSuccess();
    }
}
