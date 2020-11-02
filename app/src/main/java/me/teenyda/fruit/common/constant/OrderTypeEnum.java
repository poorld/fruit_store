package me.teenyda.fruit.common.constant;

public enum OrderTypeEnum {

    Order(0, "订单"),
    Cart(1, "购物车");

    int paymentType;

    String desc;

    OrderTypeEnum(int paymentType, String desc) {
        this.paymentType = paymentType;
        this.desc = desc;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
