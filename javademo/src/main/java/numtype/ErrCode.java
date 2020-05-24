package numtype;

/**
 * user:zxp
 * Day:2020,02,15
 **/
enum ErrCode{
    //通过一个Properties 文件进行映射
    //key 是枚举值的字符串，值是Code和message的组合
    FileNotFound{
        @Override
        public String getMessage(String... paramters) {
            return null;
        }

        @Override
        public String getCode() {
            return null;
        }
    };

    /**
     * 设置错误前缀
     *   软件标识，模块标识
     * @param preCode
     */
    public static void setPre(String preCode){
    }

    /**
     * 获取错误码提示信息
     *   用于展示的提示信息
     * @param paramters
     * @return
     */
    public abstract String getMessage(String... paramters);

    /**
     * 获取错误数值
     *    对应的错误码的值
     * @return
     */
    public abstract String getCode();
}
