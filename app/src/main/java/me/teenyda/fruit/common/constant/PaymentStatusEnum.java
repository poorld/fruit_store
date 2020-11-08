package me.teenyda.fruit.common.constant;

/**
 * author: teenyda
 * date: 2020/11/8
 * description:
 */
public enum PaymentStatusEnum {
    UN_PAYMENT(0, "未付款"),
    PAYMENT(1, "已付款");

    int paymentStatus;

    String desc;

    PaymentStatusEnum(int paymentStatus, String desc) {
        this.paymentStatus = paymentStatus;
        this.desc = desc;
    }

    public int getPaymentType() {
        return paymentStatus;
    }

    public void setPaymentType(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
