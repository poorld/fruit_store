# 水果小超市APP
水果小超市 APP 是为客户提供一个基于移动网络的线上水果销售平台，实现水果商品展示、分类浏览、提交 订单、加入购物车、会员功能、管理员产品管理、添加活动等功能。该系统手机端基于 Android 平台，使 Android 开发技术结合 MVP 设计模式。服务器端使用 SpringBoot 后台开发框架进行开发，MySQL 作为后 台数据库支持，使用 Vue.js 和 ElementUI 实现后台管理系统的界面布局。
涉及技术: SpringBoot、Mybatis、Vue.js、ElementUI、Android、MVP

项目参考:
- [【Android架构】基于MVP模式的Retrofit2+RXjava封装](https://yq.aliyun.com/articles/620836)
- [Android Rxjava+Retrofit网络请求框架封装（一）](https://blog.csdn.net/cs_lwb/article/details/82016997)
- [Android Log日志封装](https://blog.csdn.net/cs_lwb/article/details/82823536)

- mvp
- Rxjava
- Retrofit
- 相片选择
- 拍照
- 透明状态栏


- 状态栏
关于状态栏透明，可以用```StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0,null);````
如果需要预留状态栏，可以用
1.
```StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), start_bar);```
start_bar是最顶部的控件，StatusBarUtil根据控件做出偏移

2.
```
<View
        android:id="@+id/fake_status_bar"
        android:layout_width="match_parent"
        android:layout_height="@dimen/statusbar_view_height"
        android:background="@color/状态栏颜色"/>
```
使用<View/>来预留状态栏，然后使用StatusBarUtil.setColor设置状态栏颜色
```java
StatusBarUtil.setColor(MainActivity.this, getColor(R.color.colorPrimary), 0);
```

```
0下订单（立即购买/加入购物车）
1立即支付(还没支付)
2已支付（交易完成）
3配送中
4配送完成
5订单支付超时
6取消订单
```
