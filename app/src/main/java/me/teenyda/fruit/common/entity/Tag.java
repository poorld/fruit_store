package me.teenyda.fruit.common.entity;

import java.io.Serializable;

/**
 * (Tag)实体类
 * 产品标签
 * @author makejava
 * @since 2020-10-14 12:39:31
 */
public class Tag implements Serializable {
    private static final long serialVersionUID = -68040896446473512L;

    private Integer tagId;

    private String name;


    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}