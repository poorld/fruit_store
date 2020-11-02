package me.teenyda.fruit.common.constant;

public enum PaymentTypeEnum {

    Balance(0, "余额"),
    Alipay(1, "支付宝"),
    WeChatPay(2, "微信支付");

    int paymentType;

    String desc;

    PaymentTypeEnum(int paymentType, String desc) {
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
