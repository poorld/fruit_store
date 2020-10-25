package me.teenyda.fruit.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Comments)实体类
 * 评论
 * @author makejava
 * @since 2020-10-09 17:10:37
 */

@Data
public class Comments implements Serializable {
    private static final long serialVersionUID = -25444406065751623L;

    private Integer commentsId;

    // private Integer userId;
    private User user;

    private Integer productId;

    private String content;

    private String img1;

    private String img2;

    private String img3;
    /**
     * 0未审核 1审核通过
     */
    private Integer audit;
    /**
     * 商家回复
     */
    private String reply;

    private Date createTime;



}