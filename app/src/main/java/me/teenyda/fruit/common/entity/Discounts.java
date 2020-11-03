package me.teenyda.fruit.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Discounts)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:10:41
 */

@Data
public class Discounts implements Serializable {
    private static final long serialVersionUID = -78622205559742887L;

    private Integer discountsId;

    /**
     * 说明
     */
    private String discountsExplain;
    /**
     * 满足条件的价格
     */
    private Integer conditions;
    /**
     * 满足条件说明
     */
    private String conditionsExplain;
    /**
     * 优惠
     */
    private Double discounts;
    /**
     * 会员限制
     */
    private Boolean members;

    /**
     * 优惠类型
     */
    private DiscountsCategory discountsCategory;



}