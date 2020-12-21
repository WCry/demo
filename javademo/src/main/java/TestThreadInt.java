public class TestThreadInt {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            int www=0;
            @Override
            public void run() {
                System.out.println("子线程开始");
                for (int i = 0; i < 10000000; i++) {
                    if(i%2==0){
                        System.out.println(www);
                        www+=i;
                    }
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int  dd=0;
        for(int w=0;w<100000;w++){
            if(w/2==0){
                dd+=w;
            }
        }
        System.out.println("准备中断");
        thread.interrupt();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
