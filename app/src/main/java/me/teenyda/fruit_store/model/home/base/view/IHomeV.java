package me.teenyda.fruit_store.model.home.base.view;

import java.io.File;

import me.teenyda.fruit_store.common.entity.FileUploadResponse;
import me.teenyda.fruit_store.common.mvp.BaseView;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public interface IHomeV extends BaseView {

    void compressImageSuccess(File file);

    void showImage(FileUploadResponse file);
}
