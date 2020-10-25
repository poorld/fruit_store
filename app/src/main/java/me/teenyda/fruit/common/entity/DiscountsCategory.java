package me.teenyda.fruit.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * (DiscountsCategory)实体类
 *
 * @author makejava
 * @since 2020-10-12 18:44:25
 */
@Data
public class DiscountsCategory implements Serializable {
    private static final long serialVersionUID = 520668961860415181L;

    private Integer discountsCategoryId;
    /**
     * 充值 满减 折扣
     */
    private String discountsType;

    private Integer discountsFlag;

    private String discountsDescription;



}