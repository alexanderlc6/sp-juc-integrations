package com.sp.netty.integrations.model;

import lombok.Data;

/**
 * [Add Description Here]
 *
 * @author luchao Created in 6/11/22 10:44 AM
 */
@Data
public class TradeOrder {

    private Integer tradeId;

    private String status;

    public TradeOrder(Integer tradeId, String status) {
        this.tradeId = tradeId;
        this.status = status;
    }
}
