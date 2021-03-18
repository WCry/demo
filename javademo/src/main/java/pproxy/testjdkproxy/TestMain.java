package pproxy.testjdkproxy;

public class TestMain {
    public static void main(String[] args) {
        Star ldh = new LiuDeHua();

        StarProxy proxy = new StarProxy();

        proxy.setTarget(ldh);

        Object obj = proxy.CreatProxyedObj();

        Star star = (Star)obj;
        star.dance("测试.dsad");
    }
}
