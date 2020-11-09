package me.teenyda.fruit.common.entity;

import java.util.Date;

import lombok.Data;

/**
 * author: teenyda
 * date: 2020/11/9
 * description:
 */
@Data
public class OrderProductDto {
    /**
     * 主键
     *
     * isNullAble:0
     */
    private Integer productId;

    /**
     *
     * isNullAble:0
     */
    private Integer productCategoryId;

    /**
     *
     * isNullAble:1
     */
    private String name;

    /**
     *
     * isNullAble:1
     */
    private String explain;

    /**
     *
     * isNullAble:1
     */
    private java.math.BigDecimal shopPrice;

    /**
     *
     * isNullAble:1
     */
    private java.math.BigDecimal price;

    /**
     *
     * isNullAble:1
     */
    private Integer hot;

    /**
     *
     * isNullAble:0,defaultVal:0
     */
    private Integer productStatus;

    /**
     * 是否加推荐标签
     */
    private Boolean recommended;

    /**
     *
     * isNullAble:1
     */
    private String defaultImg;

    /**
     *
     * isNullAble:1
     */
    private Date updateTime;


    /**
     *
     * isNullAble:1
     */
    private Date createTime;

    private Spec spec;
}
