package com.example.datamong.controller;

import com.example.datamong.FileInfo;
import com.example.datamong.controller.param.RecursiveUploadParams;
import com.example.datamong.controller.param.TMSFolderInfoDTO;
import com.example.datamong.controller.util.FtpUtil;
import com.example.datamong.service.DataService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


@Component
public class UploadTMSImpl {
    private final static String PATH_SEP_CHAR = "/";
    private Integer fileCount = 0;
    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }

    /**
     * 递归上传tms文件
     * @param tmsFolderInfoDTO
     * @throws Exception
     */

    @Async
    public void ftpFetchUpLoad(TMSFolderInfoDTO tmsFolderInfoDTO, String uploadId){
        FtpUtil ftpUtil = new FtpUtil(tmsFolderInfoDTO.getUsrName(),tmsFolderInfoDTO.getPassword(),tmsFolderInfoDTO.getIPHost(),tmsFolderInfoDTO.getPort());
        try {
            ftpUtil.login();
//            recursiveFTFolderAndComputeFileCount(ftpUtil, tmsFolderInfoDTO.getSourcePath());
//            System.out.println("FileCount  :" + fileCount);
            RecursiveUploadParams recursiveUploadParam=new RecursiveUploadParams();
            recursiveUploadParam.setDataDomain(tmsFolderInfoDTO.getDomainID());
            recursiveUploadParam.setServiceName(tmsFolderInfoDTO.getFolderName());
            recursiveUploadParam.setOriginalSource(tmsFolderInfoDTO.getSourcePath());
            recursiveUploadParam.setUploadId(uploadId);
            recursiveFTFolderAndUpLoad(ftpUtil, tmsFolderInfoDTO.getSourcePath(),recursiveUploadParam);
            ftpUtil.loginOut();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                ftpUtil.loginOut();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }


    /**
     * 校验能否链接上ftp服务器
     * @param userName
     * @param password
     * @param port
     * @param ipHost
     * @return
     */
    public Boolean cherckFtpConnect(String userName,String password,int port,String ipHost){
        FtpUtil ftpUtil = new FtpUtil(userName,password,ipHost,port);
        try {
            ftpUtil.login();
            if(ftpUtil.getHasLogin()){
                ftpUtil.loginOut();
            }else{
                return false;
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 递归遍历ftp目录,并计算文件的总数
     * @param ftpUtil
     * @param source
     * @throws Exception
     */
    private void recursiveFTFolderAndComputeFileCount(FtpUtil ftpUtil, String source) throws Exception {
        FTPFile[] ftpFiles = ftpUtil.listFiles(source);
        for (int i = 0; i < ftpFiles.length; i++) {
            FTPFile ftpFile = ftpFiles[i];
            if (ftpFile.isDirectory()) {
                recursiveFTFolderAndComputeFileCount(ftpUtil, source + ftpFile.getName() + PATH_SEP_CHAR);
            }
            if (ftpFile.isFile()) {
                if(ftpUtil.isExsits(source + ftpFile.getName())){
                    fileCount++;
                    if(0==(fileCount%10000)){
                        System.out.println("fileCount: "+fileCount);
                    }
                }
            }
        }

    }




    /**
     * 递归遍历ftp目录,并将文件上传到db
     * @param ftpUtil
     * @param source
     * @param recursiveUploadParam
     * @throws Exception
     */
    private void recursiveFTFolderAndUpLoad(FtpUtil ftpUtil, String source, RecursiveUploadParams recursiveUploadParam) throws Exception {
        FTPFile[] ftpFiles = ftpUtil.listFiles(source);
            for (int i = 0; i < ftpFiles.length; i++) {
                FTPFile ftpFile = ftpFiles[i];
                if (ftpFile.isDirectory()) {
                    recursiveFTFolderAndUpLoad(ftpUtil, source + ftpFile.getName() + PATH_SEP_CHAR, recursiveUploadParam);
                }
                if (ftpFile.isFile()) {
                    try{
                    if(ftpUtil.isExsits(source + ftpFile.getName())){
                        String upRelativePath=(source + ftpFile.getName()).
                                replace(recursiveUploadParam.getOriginalSource(),"");
                        String dataDomain=  recursiveUploadParam.getDataDomain();
                        String serviceName= recursiveUploadParam.getServiceName();
                        byte[] fileBytes= ftpUtil.getSingleFileInputBytes(source + ftpFile.getName());
                        String fileName=getFileNameNoEx(upRelativePath);
                        String[] strArr=fileName.split("/");
                        String z=strArr[0];
                        String x=strArr[1];
                        String y=strArr[2];
                        String type=getExtensionName(upRelativePath).replace(".","");
                        upLoadFileToDB(fileBytes,z,x,y,type,dataDomain, serviceName,recursiveUploadParam.getUploadId());
                    }
                    } catch (Exception e){

                    }
                }
            }


    }

    /**
     * 获取文件扩展名
     * @param filename
     * @return
     */
    public  String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }


    /**
     * 获取不带扩展名的文件名
     * @param filename
     * @return
     */
    public  String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }




    @Autowired
    private DataService dataService;
    /**
     * 调用接口将数据传到后台db
     * @param inputBytes 图片内容
     * @param z
     * @param x
     * @param y
     * @param type 图片类型
     * @param dataDomain   数据域
     * @param serviceName  服务名称
     */
    private Boolean upLoadFileToDB(byte[] inputBytes, String z,String x,String y,String type, String dataDomain,
                                   String serviceName,String uploadId) throws Exception {
        Boolean upLoadresult=false;
        try {
            if (inputBytes != null) {
                if(StringUtils.isNumeric(z)||StringUtils.isNumeric(x)||StringUtils.isNumeric(y)) {
                    System.out.println(z+"_"+x+"_"+y);
                    String  filename=dataDomain+"/"+serviceName+"/"+z+"/"+x+"/"+y+".png";
                    System.out.println(filename);
                    FileInfo fileInfo=new FileInfo();
                    fileInfo.setPath(filename);
                    fileInfo.setContent(new Binary(inputBytes));
                    fileInfo.setContentType(".png");
                    fileInfo.setSize(inputBytes.length);
                    dataService.saveData(fileInfo);
                }
            }
        } catch (Exception e) {

        }
        return upLoadresult;
    }
}
