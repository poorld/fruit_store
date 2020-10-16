package me.teenyda.fruit.common.entity;

import lombok.Data;

/**
 * author: teenyda
 * date: 2020/9/17
 * description: 购物车
 */

@Data
public class Cart {

    private String name;
    private float price;
    private String specification;
    private int count;
    private boolean selected;
}
