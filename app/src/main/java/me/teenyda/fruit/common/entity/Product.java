package me.teenyda.fruit.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2020-10-09 17:10:52
 */
@Data
public class Product {

    private Integer productId;

    private String name;

    // 描述
    private String explain;

    // 市场价格
    private Double shopPrice;

    // 售卖价格
    private Double price;

    private Integer hot;


    // 是否加推荐标签
    private Boolean recommended;

    // 产品状态 0上线 1下线
    private Integer productStatus;

    // 封面图片
    private String defaultImg;

    private Date updateTime;

    private Integer updateUserId;


    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private String createTime;

    private Integer createUserId;

    // 产品分类
    private ProductCategory productCategory;

    // 规格
    private List<Spec> spec;

    // 优惠
    private List<Discounts> discounts;

    // 产品图片
    private List<ProductInfoImage> productInfoImages;

    // 产品幻灯片
    private List<ProductBannerImage> productBannerImages;

    // 产品标签
    private List<Tag> tags;

    // 评论
    // private List<Comments> comments;

    /**
     *
     {
         "productId":1,
         "name":"新疆葡萄",
         "explain":"特色新疆美味葡萄巴拉巴拉",
         "shopPrice":35.2,
         "price":23.5,
         "hot":null,
         "recommended":null,
         "productStatus":0,
         "defaultImg":null,
         "updateTime":null,
         "updateUserId":null,
         "createTime":null,
         "createUserId":null,
         "productCategory":{
         "productCategoryId":11001,
         "name":"新疆葡萄",
         "sortOrder":null,
         "description":"葡萄类哦",
         "image":null,
         "updateTime":null,
         "updateUserId":null
         },
         "spec":[
         {
         "specId":12100,
         "productId":1,
         "specName":"小果",
         "price":23.5,
         "quantity":30,
         "sku":{
         "skuId":101,
         "attrbute":"斤"
         }
         }
         ],
         "discounts":[
         {
         "discountsId":2,
         "discountsExplain":"特色新疆美味葡萄巴拉巴拉",
         "conditions":22,
         "conditionsExplain":"满22元才能享受优惠",
         "discounts":"2",
         "members":true,
         "discountsCategory":{
         "discountsCategoryId":2001,
         "discountsType":"满减",
         "discountsFlag":0,
         "discountsDescription":"满减呀呀呀"
         }
         }
         ],
         "productInfoImages":[
         {
         "productId":1,
         "sort":null,
         "url":null
         }
         ],
         "productBannerImages":[
         {
         "productId":1,
         "type":null,
         "url":null
         }
         ],
         "tags":[
         {
         "tagId":13100,
         "name":"产地直销"
         },
         {
         "tagId":13101,
         "name":"会员优惠"
         }
         ],
         "comments":[
         {
         "commentsId":null,
         "user":null,
         "productId":1,
         "content":null,
         "img1":null,
         "img2":null,
         "img3":null,
         "audit":null,
         "reply":null,
         "createTime":null
         }
         ]
     }
     */
}