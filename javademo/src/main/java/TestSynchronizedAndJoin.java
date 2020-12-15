/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestSynchronizedAndJoin {
    public static void main(String[] args) throws InterruptedException {
        String a="1111";
        String b="222";
        Thread thread=new Thread(() -> {
            synchronized (a){
                try {
                    Thread.sleep(1000);
                    b.replace("222","ssss");
                    System.out.println(b.startsWith("sss"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2=new Thread(() -> {
            synchronized (b){
                try {
                    Thread.sleep(1000);
                    a.replace("11","aaa");
                    System.out.println(a.startsWith("sss"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
    }
}
