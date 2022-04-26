package com.sp.rmq.interaction.controller;

import com.sp.framework.common.utils.ResponseUtil;
import com.sp.framework.common.vo.ResponseVO;
import com.sp.rmq.interaction.domain.SendMsgVO;
import com.sp.rmq.interaction.service.SendMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: MQ消息测试
 * @author: luchao
 * @date: Created in 4/23/22 5:32 PM
 */
@RestController
@RequestMapping("/rmq")
public class SendMsgController {
    @Autowired
    SendMsgService sendMsgService;

    /**
     * 发送MQ消息
     * @param sendMsg
     * @return
     * @throws Exception
     */
    @PostMapping("/send")
    public ResponseVO sendMsg(@RequestBody SendMsgVO sendMsg) throws Exception {
        return ResponseUtil.getFromData(sendMsgService.sendMsg(sendMsg));
    }

    /**
     * 消费MQ消息
     * @param sendMsg
     * @return
     * @throws Exception
     */
    @GetMapping("/consume")
    public ResponseVO consumeMsg(@RequestBody SendMsgVO sendMsg) throws Exception {
        return ResponseUtil.getFromData(sendMsgService.consumeMsg());
    }
}
