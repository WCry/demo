package com.example.datamong.controller.param;

import java.io.Serializable;

/**
 * @ClassName TMSUploadResultDTO
 * @Description TMS返回客户端的DTO
 * @Author fangqiwei
 * @Date 2020/3/25 11:03
 */
public class TMSUploadResultDTO implements Serializable {

    String message;

    //用于表示上传的ID，保留。为以后查询上传进度做准备，目前直接使用 "TMS"+"_"+domainName+"_"+serverName
    String uploadId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    @Override
    public String toString() {
        return "TMSUploadResultDTO{" +
                "message='" + message + '\'' +
                ", uploadId='" + uploadId + '\'' +
                '}';
    }
}
