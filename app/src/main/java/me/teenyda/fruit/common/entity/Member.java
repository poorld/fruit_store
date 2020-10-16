package me.teenyda.fruit.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * author: teenyda
 * date: 2020/9/18
 * description:
 */

@Data
@AllArgsConstructor
public class Member {

    private String memberName;

    /**
     * 1 vip1 普通会员
     * 2 vip2 黄金会员
     * 3 vip3 钻石会员
     */
    private int level;

    private int memberImgRes;

    private int backgroundRes;

    private String price;
}
