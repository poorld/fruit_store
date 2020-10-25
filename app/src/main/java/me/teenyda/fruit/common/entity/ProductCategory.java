package me.teenyda.fruit.common.entity;

import lombok.Data;

/**
 * author: teenyda
 * date: 2020/10/24
 * description:
 */
@Data
public class ProductCategory {
    /**
     * 主键
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
    private String description;

    /**
     *
     * isNullAble:1
     */
    private String image;
}
