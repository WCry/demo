package pproxy.testcglibproxy;

import pproxy.testjdkproxy.LiuDeHua;
import pproxy.testjdkproxy.Star;

public class TestCglib {
    public static void main(String[] args) {
        CglibProxy proxy2 = new CglibProxy();
        long time5 = System.currentTimeMillis();
        Star star2 = (Star)proxy2.CreatProxyedObj(LiuDeHua.class);
        long time6 = System.currentTimeMillis();
        System.out.println(star2.dance("dd"));
        System.out.println("cglib创建时间：" + (time6 - time5));
    }
}
