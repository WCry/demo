package com.example.datamong.controller.param;

import java.io.Serializable;

/**
 * Author: zhangxuepei
 * Date: 2020/3/20 9:49
 * Content:
 */
public class TMSFolderInfoDTO implements Serializable {
    public static final String FTP = "ftp";
    public static final String SFTP = "sftp";
    private String fetchType;
    private String IPHost;
    private int port;
    private String usrName;
    private String password;
    private String sourcePath;
    private String domainID;
    private String folderName;

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getDomainID() {
        return domainID;
    }

    public void setDomainID(String domainID) {
        this.domainID = domainID;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFetchType() {
        return fetchType;
    }

    public void setFetchType(String fetchType) {
        this.fetchType = fetchType;
    }

    public String getIPHost() {
        return IPHost;
    }

    public void setIPHost(String IPHost) {
        this.IPHost = IPHost;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public String toString() {
        return "TMSFolderInfoDTO{" +
                "fetchType='" + fetchType + '\'' +
                ", IPHost='" + IPHost + '\'' +
                ", port=" + port +
                ", usrName='" + usrName + '\'' +
                ", password='" + password + '\'' +
                ", sourcePath='" + sourcePath + '\'' +
                ", domainID='" + domainID + '\'' +
                ", folderName='" + folderName + '\'' +
                '}';
    }
}
