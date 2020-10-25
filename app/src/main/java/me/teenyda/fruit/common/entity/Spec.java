package me.teenyda.fruit.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Spec)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:10:58
 */
@Data
public class Spec implements Serializable {
    private static final long serialVersionUID = 176125375001612113L;

    private Integer specId;

    private Integer productId;

    private String specName;

    private Double price;

    private Integer quantity;

    private Sku sku;

}