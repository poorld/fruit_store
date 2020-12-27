package me.teenyda.fruit.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: personal_information
 * @author: Teenyda
 * @create: 2020-12-27 14:54
 * @description: 用户评论
 **/
@Data
public class CommentsDto {

    private Integer commentsId;

    private Integer userId;

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
