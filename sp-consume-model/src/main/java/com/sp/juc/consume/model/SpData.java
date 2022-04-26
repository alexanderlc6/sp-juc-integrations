package com.sp.juc.consume.model;

import lombok.Data;

/**
 * @description:
 * @author: luchao
 * @date: Created in 11/20/21 5:57 PM
 */
@Data
public class SpData {
    private Integer value;

    public SpData(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
