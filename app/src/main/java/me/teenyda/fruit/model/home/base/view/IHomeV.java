package me.teenyda.fruit.model.home.base.view;

import java.util.List;

import me.teenyda.fruit.common.entity.Banner;
import me.teenyda.fruit.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public interface IHomeV extends BaseView {

    void setBanners(List<Banner> banners);
}
