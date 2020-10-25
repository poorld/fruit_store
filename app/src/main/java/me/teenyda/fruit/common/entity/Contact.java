package me.teenyda.fruit.common.entity;

import java.io.Serializable;

/**
 * (Contact)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:10:40
 */
public class Contact implements Serializable {
    private static final long serialVersionUID = -16328750537562427L;

    private Integer contactId;

    private Integer userId;

    private String name;

    private String mobile;

    private String address;
    /**
     * 1 默认联系人
     */
    private Integer contactFlag;


    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getContactFlag() {
        return contactFlag;
    }

    public void setContactFlag(Integer contactFlag) {
        this.contactFlag = contactFlag;
    }

}