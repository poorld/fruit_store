package me.teenyda.fruit_store.common.entity;

import java.io.Serializable;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class UserToken implements Serializable {

    private String userName;

    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
