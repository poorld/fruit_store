package me.teenyda.fruit.common.constant;

public enum PaymentFlagEnum {

    Not_Paying(0, "未付款"),

    Already_Payment(1, "已付款");

    int paymentFlag;

    String desc;

    PaymentFlagEnum(int paymentFlag, String desc) {
        this.paymentFlag = paymentFlag;
        this.desc = desc;
    }

    public int getPaymentFlag() {
        return paymentFlag;
    }

    public void setPaymentFlag(int paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
