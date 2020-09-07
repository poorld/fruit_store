package me.teenyda.mvp_template.common.entity;

import lombok.Data;

/**
 * author: teenyda
 * date: 2020/9/7
 * description:
 */

@Data
public class FileUploadResponse {
    private String fileName;
    private String fileDownloadUrl;
    private String fileType;
    private long size;

}
