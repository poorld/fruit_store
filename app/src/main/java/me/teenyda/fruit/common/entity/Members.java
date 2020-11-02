package me.teenyda.fruit.common.entity;


import lombok.Data;

/**
 * (Members)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:10:45
 */
@Data
public class Members {
    private Integer membersId;

    private String name;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 价格/月
     */
    private Integer price;

}