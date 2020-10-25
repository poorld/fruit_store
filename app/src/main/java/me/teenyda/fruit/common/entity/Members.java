package me.teenyda.fruit.common.entity;

import java.io.Serializable;

/**
 * (Members)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:10:45
 */
public class Members implements Serializable {
    private static final long serialVersionUID = 862662292328287934L;

    private Integer membersId;

    private String name;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 购物折扣
     */
    private Integer discount;
    /**
     * 配送优惠
     */
    private Integer distribution;
    /**
     * 充值优惠
     */
    private Integer topup;
    /**
     * 价格/月
     */
    private Integer price;


    public Integer getMembersId() {
        return membersId;
    }

    public void setMembersId(Integer membersId) {
        this.membersId = membersId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getDistribution() {
        return distribution;
    }

    public void setDistribution(Integer distribution) {
        this.distribution = distribution;
    }

    public Integer getTopup() {
        return topup;
    }

    public void setTopup(Integer topup) {
        this.topup = topup;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}