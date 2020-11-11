package me.teenyda.fruit.common.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:11:00
 */
@Data
public class User {
    private Integer userId;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private String image;

    private String qq;

    private String mobile;

    private Date lastLoginTime;

    private Date registerTime;

    private Members members;

}