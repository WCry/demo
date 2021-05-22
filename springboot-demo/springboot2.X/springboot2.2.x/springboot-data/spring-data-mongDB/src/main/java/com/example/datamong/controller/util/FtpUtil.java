package com.example.datamong.controller.util;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class FtpUtil {

    /**
     * SFTP 登录用户名
     */
    private String username;
    /**
     * SFTP 登录密码
     */
    private String password;
    /**
     * SFTP 服务器地址IP地址
     */
    private String host;
    /**
     * SFTP 端口
     */
    private int port;

    private FTPClient ftp;

    /**
     * 是否登录
     */
    private Boolean hasLogin;



    private static final String CHARSET_UTF8 = "UTF-8";

    /**
     * 设置缓冲区大小
     **/
    private static final int BUFFER_SIZE = 1024 * 1024 * 10;

    /**
     * 构造基于密码认证的ftp对象
     */
    public FtpUtil(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }


    public Boolean getHasLogin() {
        return hasLogin;
    }


    public void changeWorkingDirectory(String directory) throws IOException {
          this.ftp.changeWorkingDirectory(directory);
    }

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param host     FTP服务器hostname
     * @param port     FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String host, int port, String username, String password, String basePath, String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath + filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir))
                        continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {  //进不去目录，说明该目录不存在
                        if (!ftp.makeDirectory(tempPath)) { //创建目录
                            //如果创建文件目录失败，则返回
                            System.out.println("创建文件目录" + tempPath + "失败");
                            return result;
                        } else {
                            //目录存在，则直接进入该目录
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @param host       FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     */
    public static boolean downloadFile(String host, int port, String username, String password, String remotePath, String fileName, String localPath) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());

                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /***
     * 判断文件是否存在
     * @param ftpPath
     * @return
     */
    public boolean isExsits(String ftpPath) throws Exception {
        Boolean result = false;
        if (hasLogin) {
            String[] files = ftp.listNames(ftpPath);
            if (files != null && files.length > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new Exception("请先登录ftp服务器");
        }
    }


    public Boolean downloadSingleFile(String remotePath, String fileName, String localPath) throws Exception {
        boolean result = false;
        if (hasLogin) {
            if (isExsits(remotePath)) {
                ftp.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                File localFile = new File(localPath + "/" + fileName);
                OutputStream is = new FileOutputStream(localFile);
                ftp.retrieveFile(remotePath, is);
                is.close();
                result = true;
            } else {
                throw new Exception("文件不存在");
            }
        } else {
            throw new Exception("请先登录ftp服务器");
        }
        return result;
    }

    /**
     * 下载指定文件夹下的指定文件
     *
     * @param remotePath 文件的父级目录
     * @param fileName   文件名
     * @param localPath
     * @return
     * @throws Exception
     */
    public boolean downloadFiles(String remotePath, String fileName, String localPath) throws Exception {
        System.out.println("filename： " + fileName);
        System.out.println("remotePath: " + remotePath);
        boolean result = false;
        if (hasLogin) {
            ftp.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
            FTPFile[] fs = ftp.listFiles(remotePath);
            String[] names = ftp.listNames(remotePath);
            for (FTPFile ff : fs) {
                String ftpName = ff.getName();
                if (ftpName.equals(fileName)) {
                    File localFile = new File(localPath + "/" + fileName);
                    System.out.println(localPath + remotePath);
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(remotePath, is);
                    is.close();
                }
            }
            result = true;
        } else {
            throw new Exception("请先登录ftp服务器");
        }
        return result;
    }


    public void loginOut() throws IOException {
        if (hasLogin) {
            ftp.logout();
            this.hasLogin = false;
        }
    }

    public void login() throws IOException {
        ftp = new FTPClient();
        int reply;
        ftp.setControlEncoding("GBK");
        ftp.connect(host, port);
        // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
        ftp.login(username, password);// 登录
        reply = ftp.getReplyCode();
        System.out.println(reply);
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            hasLogin = false;
        } else {
            hasLogin = true;
        }
    }

    public FTPFile[] dsalistFiles() throws IOException {
        ftp.changeWorkingDirectory("/tmstest/1/");
        System.out.println("/tmstest/1/" + ftp.listFiles("/tmstest/1/").length);
        System.out.println("=====" + ftp.listFiles().length);
        // 转移到FTP服务器目录
        ftp.changeWorkingDirectory("/tmstest/2/");
        System.out.println("/tmstest/2/" + ftp.listFiles("/tmstest/2/").length);
        System.out.println(ftp.listFiles().length);
        return ftp.listFiles();
    }

    /**
     * 获取某路径下的文件（包含文件夹）
     *
     * @param remotePath
     * @return
     * @throws Exception
     */
    public FTPFile[] listFiles(String remotePath) throws Exception {
        if (hasLogin) {
            ftp.enterLocalPassiveMode();
            ftp.setControlEncoding("GBK");
            return ftp.listFiles(remotePath);
        } else {
            throw new Exception("请先登录ftp服务器");
        }
    }

    /**
     * Description: 根据文件的绝对路径获取文件的InputStream
     *
     * @param remotePath 要下载文件的路径
     * @return
     */
    public InputStream getSingleFileInputStream(String remotePath) throws Exception {

        InputStream input = null;
        if (hasLogin) {
            if (isExsits(remotePath)) {
                ftp.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                input = ftp.retrieveFileStream(remotePath);
            } else {
                throw new Exception("文件不存在");
            }
        } else {
            throw new Exception("请先登录ftp服务器");
        }
        return input;
    }

    /**
     * 获取该目录下所有文件,以输入流返回
     *
     * @param remotePath FTP服务器上文件相对路径，例如：test/123
     * @return Map<String, InputStream> 其中key为文件名，value为输入流对象
     */
    public Map<String, InputStream> getFileInputSteams(String remotePath) throws Exception {
        Map<String, InputStream> map = new HashMap<String, InputStream>();
        if (hasLogin) {
            if (isExsits(remotePath)) {
                ftp.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                String[] fs = ftp.listNames();
                if (fs == null || fs.length == 0) {
                    System.out.println((remotePath + "该目录下没有文件"));
                    return map;
                } else {
                    for (String ff : fs) {
                        String ftpName = new String(ff.getBytes(CHARSET_UTF8), CHARSET_UTF8);
                        if (isDatFile(ftpName)) {
                            InputStream is = ftp.retrieveFileStream(ff);
                            map.put(ftpName, is);
                            ftp.completePendingCommand(); // 处理多个文件
                        }
                    }
                }
            } else {
                throw new Exception("文件不存在");
            }
        } else {
            throw new Exception("请先登录ftp服务器");
        }
        return null;
    }


    /**
     * Description: 根据文件的绝对路径获取文件的字节流
     *
     * @param remotePath 要下载文件的路径
     * @return
     */
    public byte[] getSingleFileInputBytes(String remotePath) throws Exception {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        InputStream input = null;
        if(ftp!=null){
            if (!isExsits(remotePath)) {
                System.out.println(remotePath + "该目录不存在");
                throw new Exception(remotePath + " : 文件不存在");
            }else{
                ftp.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
//                ftp.setControlEncoding("GBK");
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                input = ftp.retrieveFileStream(remotePath);
                byte[] buffer = new byte[BUFFER_SIZE];
                int len;
                while ((len = input.read(buffer, 0, BUFFER_SIZE)) != -1) {
                    byteStream.write(buffer, 0, len);
                }
            }
        }else{
            throw new Exception("请先登录ftp服务器");
        }
        input.close();
        ftp.completePendingCommand();
        return byteStream.toByteArray();
    }


    private boolean isDatFile(String fileName) {
        if (StringUtils.isNotBlank(fileName)) {
            int length = fileName.length();
            int index = fileName.indexOf(".");
            if (index == -1) {
                return false;
            }
            if (StringUtils.equals(fileName.substring(index + 1, length), "dat")) {
                return true;
            }
        }
        return false;
    }

}


