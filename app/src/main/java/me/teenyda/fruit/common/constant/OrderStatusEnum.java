package me.teenyda.fruit.common.constant;

public enum OrderStatusEnum {

    ToCart(0, "加入购物车"),
    WaitingPayment(1, "立即购买,等待支付"),
    HaveToPay(2, "已支付"),
    Distribution(3, "配送中"),
    DistributionComplete(4, "配送完成");

    int paymentType;

    String desc;

    OrderStatusEnum(int paymentType, String desc) {
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
