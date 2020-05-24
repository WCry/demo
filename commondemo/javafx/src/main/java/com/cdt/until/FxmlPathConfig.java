package com.cdt.until;

import java.net.URL;

/**
 * Created by root on 17-5-25.
 */
public class FxmlPathConfig {

    /**
     * fxml的路径
     */
    public static String fxmlPath ="/ui/";
    /**
     * 省市县三级xml配置文件的存放路径
     */
    public static String districtXmlPath ="/district/";
    /**
     * 主界面fxml路径
     */
    public static URL mainStageFxmlPath = FxmlPathConfig.class.getResource(fxmlPath + "Main.fxml");

    /**
     * 确认框fxml路径
     */
    public static URL confirmFxmlPath=FxmlPathConfig.class.getResource(fxmlPath + "Confirm.fxml");

    /**
     * 省级地理范围各下载路径
     */
    public static URL provinceXml = FxmlPathConfig.class.getResource(districtXmlPath + "province.xml");


    /**
     * 地市级地理范围各下载路径
     *
     */
    public static URL cityXml = FxmlPathConfig.class.getResource(districtXmlPath + "city.xml");

    /**
     * 区县级地理范围各下载路径
     *
     */
    public static URL countyXml = FxmlPathConfig.class.getResource(districtXmlPath + "county.xml");

    /**
     * 数据库配置文件地址
     */
    public static URL paramPath=FxmlPathConfig.class.getResource("/params.properties");
    //public static URL paramPath=FxmlPathConfig.getClass().getResource("/params.properties");

}
