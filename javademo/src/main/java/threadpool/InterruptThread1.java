package threadpool;

public class InterruptThread1 extends Thread {

    public static void main(String[] args) throws InterruptedException {
        try {
            InterruptThread1 t = new InterruptThread1();
            t.start();
            Thread.sleep(200);
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i <= 2000000; i++) {

                Thread.sleep(100);

//           if(Thread.currentThread().isInterrupted()){
//               break;
//           }
                System.out.println("i=" + i);
            }
        } catch (InterruptedException e) {
            //保存当前
            e.printStackTrace();
        }
    }
}
