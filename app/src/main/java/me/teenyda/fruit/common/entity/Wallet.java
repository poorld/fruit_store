package me.teenyda.fruit.common.entity;

import java.io.Serializable;

/**
 * (Wallet)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:11:05
 */
public class Wallet implements Serializable {
    private static final long serialVersionUID = 522224344623511348L;

    private Integer walletId;

    private Integer userId;

    private Double balance;

    private String password;


    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}