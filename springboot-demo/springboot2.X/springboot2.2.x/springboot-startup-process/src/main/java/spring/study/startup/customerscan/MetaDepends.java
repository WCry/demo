package spring.study.startup.customerscan;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Meta
public class MetaDepends {
    public MetaDepends(NormalBean normalBean){
        System.out.println("depend bean!"+normalBean);
    }
}
