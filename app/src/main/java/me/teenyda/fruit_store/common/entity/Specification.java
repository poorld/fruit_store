package me.teenyda.fruit_store.common.entity;

import lombok.Data;

/**
 * author: teenyda
 * date: 2020/9/16
 * description: 规格
 */

@Data
public class Specification {
    // 规格
    private String spec;

    // 价格
    private float price;

    public Specification(String spec, float price) {
        this.spec = spec;
        this.price = price;
    }


}
