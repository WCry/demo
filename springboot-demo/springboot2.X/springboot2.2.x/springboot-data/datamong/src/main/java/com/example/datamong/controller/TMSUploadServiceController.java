package com.example.datamong.controller;


import com.example.datamong.controller.param.TMSFolderInfoDTO;
import com.example.datamong.controller.param.TMSUploadQueryParams;
import com.example.datamong.controller.param.TMSUploadResultDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;


@Api(tags = "Tms上传相关API")
@RequestMapping("/tms")
@RestController
@EnableAsync
public class TMSUploadServiceController {

    @Autowired
    private UploadTMSImpl uploadTMSImpl;

    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    @ResponseBody
    public TMSUploadResultDTO uploadTmsFiles(@RequestBody TMSUploadQueryParams tmsUploadQueryParam, HttpServletRequest request) {
        try {
            String ftpServerUrl = tmsUploadQueryParam.getFtpServerUrl();
            if(!ftpServerUrl.substring(ftpServerUrl.length()-1,ftpServerUrl.length()).equals("/")){
                ftpServerUrl=ftpServerUrl+"/";
            }
            tmsUploadQueryParam.setFtpServerUrl(ftpServerUrl);
            //判断是否能连上ftp服务器，不能的话终止
            Boolean checkFtpResult = checkFtpUrl(tmsUploadQueryParam.getFtpServerUrl(), tmsUploadQueryParam.getUserName(), tmsUploadQueryParam.getPassword());
            if (checkFtpResult) {
                TMSFolderInfoDTO tMSFolderInfoDTO = getTMSFolderInfoDTO(tmsUploadQueryParam);
                if (tMSFolderInfoDTO != null) {
                        String upLoadId="tms";
                        //重置上传的文件数和文件总数
                        uploadTMSImpl.setFileCount(0);
                        uploadTMSImpl.ftpFetchUpLoad(tMSFolderInfoDTO, upLoadId);
                        TMSUploadResultDTO tmsUploadResultDTO = new TMSUploadResultDTO();
                        tmsUploadResultDTO.setUploadId(upLoadId);
                        tmsUploadResultDTO.setMessage("The upload has started. " +
                                                      "This process takes a lot of time. " +
                                                      "Please wait patiently");
                        return tmsUploadResultDTO;

                } else {
                    throw  new Exception("上传有误");
                }
            } else {
                throw  new Exception("上传有误");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }







    /**
     * 通过解析ftp URL对TMSFolderInfoDTO进行装配
     *
     * @param tmsUploadQueryParam
     *
     * @return
     *
     * @throws MalformedURLException
     */
    private TMSFolderInfoDTO getTMSFolderInfoDTO(TMSUploadQueryParams tmsUploadQueryParam) throws MalformedURLException {
        String ftpUrl =  URLDecoderString(tmsUploadQueryParam.getFtpServerUrl());
        URL url = new URL(ftpUrl);
        int port = url.getPort();
        if (port == -1) {
            port = url.getDefaultPort();
        }
        String host = url.getHost();
        String path = url.getPath();
        TMSFolderInfoDTO result = new TMSFolderInfoDTO();
        result.setDomainID(tmsUploadQueryParam.getDomainName());
        result.setIPHost(host);
        result.setPort(port);
        result.setPassword(tmsUploadQueryParam.getPassword());
        result.setUsrName(tmsUploadQueryParam.getUserName());
        //如果是免密登录的话，ftp客户端对Java默认的用户是ftp
        if ("".equals(tmsUploadQueryParam.getPassword())) {
            result.setUsrName("ftp");
        }
        result.setFolderName(tmsUploadQueryParam.getServiceName());
        result.setSourcePath(path);
        if (host == null || port == -1) {
            return null;
        }
        return result;
    }


    /**
     * 校验ftp url，包括url的正确性，用户名与密码是否正确，能否链接ftp服务器
     *
     * @param ftpUrl
     * @param userName
     * @param password
     *
     * @return
     */
    public Boolean checkFtpUrl(String ftpUrl, String userName, String password) throws Exception {
        URL url = null;
        try {
            url = new URL(ftpUrl);
        } catch (MalformedURLException e) {
            throw  new Exception("ftp url 有误");
        }
        int port = url.getPort();
        if (port == -1) {
            port = url.getDefaultPort();
        }
        String host = url.getHost();
        //如果是免密登录的话，ftp客户端对Java默认的用户是ftp
        if ("".equals(password)) {
            userName = "ftp";
        }
        Boolean isCanConFTP = uploadTMSImpl.cherckFtpConnect(userName, password, port, host);
        if (isCanConFTP) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ftp url中文路径解析
     * @param str
     * @return
     */
    public  String URLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
