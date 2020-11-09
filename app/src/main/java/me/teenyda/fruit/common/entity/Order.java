package me.teenyda.fruit.common.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * author: teenyda
 * date: 2020/11/9
 * description:
 */
@Data
public class Order {

    private String orderNum;

    private Integer paymentFlag;

    private Integer userId;

    private String contactName;

    private String contactMobile;

    private String contactAddress;

    private String message;

    private Integer status;

    private Integer type;

    private Integer paymentType;

    private Date createTime;

    private List<OrderItemDto> orderItems;
}
