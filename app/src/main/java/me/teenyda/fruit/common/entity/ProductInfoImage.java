package me.teenyda.fruit.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (ProductInfoImage)实体类
 *
 * @author makejava
 * @since 2020-10-14 12:39:30
 */
@Data
public class ProductInfoImage implements Serializable {
    private static final long serialVersionUID = 485888426861903348L;

    private Integer piiId;

    private Integer productId;
    /**
     * 排序
     */
    private Integer sort;

    private String url;


}