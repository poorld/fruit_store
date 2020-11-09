package me.teenyda.fruit.common.constant;

public enum TabMenuEnum {

    Menu1(0, "全部"),
    /**
     * 待付款
     */
    Menu2(1, "待付款"),
    /**
     * 待发货
     */
    Menu3(2, "待发货"),
    /**
     * 待收货
     */
    Menu4(3, "待收货"),
    /**
     * 待评价
     */
    Menu5(4, "待评价");

    // todo 暂时不要
    // Menu6(5, "订单支付超时");

    int orderStatus;

    String desc;

    TabMenuEnum(int orderStatus, String desc) {
        this.orderStatus = orderStatus;
        this.desc = desc;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
