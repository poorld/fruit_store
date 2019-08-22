package me.teenyda.mvp_template.common.mvp;

import android.content.Context;

import me.teenyda.mvp_template.common.net.resp.BaseResponse;

/**
 * author: teenyda
 * date: 2019/8/21
 * description:
 */
public interface BaseView {

    /**
     * 显示dialog
     */
    void showLoading();

    /**
     * 隐藏dialog
     */
    void hideLoading();

    void showToast(String msg);

    void onErrorCode(BaseResponse baseResponse);

    Context getMContext();

}
