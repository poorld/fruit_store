package me.teenyda.mvp_template.model.home.base.view;

import java.io.File;

import me.teenyda.mvp_template.common.entity.FileUploadResponse;
import me.teenyda.mvp_template.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public interface IHomeV extends BaseView {

    void compressImageSuccess(File file);

    void showImage(FileUploadResponse file);
}
