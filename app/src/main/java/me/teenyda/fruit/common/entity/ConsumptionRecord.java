package me.teenyda.fruit.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (ConsumptionRecord)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:10:38
 */
public class ConsumptionRecord implements Serializable {
    private static final long serialVersionUID = 750530957634852798L;

    private Integer consumptionId;

    private Integer userId;
    /**
     * 消费金额
     */
    private Double consumption;
    /**
     * 消费来源:购物/充值/vip
     */
    private String source;

    private Date createTime;


    public Integer getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(Integer consumptionId) {
        this.consumptionId = consumptionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}