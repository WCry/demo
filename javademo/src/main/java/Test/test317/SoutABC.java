package Test.test317;

import java.util.concurrent.CyclicBarrier;
public class SoutABC {
    volatile Boolean a=false;
    volatile Boolean b=false;
    volatile Boolean c=false;
    public static void main(String[] args) {
        SoutABC soutABC=new SoutABC();
        soutABC.conditionOut();
    }

    public void conditionOut(){
        Thread threadA = new Thread(() -> {
            try {
                for(int i=0;i<10;i++){
                    System.out.println("A");
                    c=false;
                    a=true;
                   while (!c){
                       Thread.sleep(0);
                   }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                for(int i=0;i<10;i++){
                    while (!a){
                        Thread.sleep(0);
                    }
                    System.out.println("B");
                    a=false;
                    b=true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                for(int i=0;i<10;i++){
                    while (!b){
                        Thread.sleep(0);
                    }
                    System.out.println("C");
                    b=false;
                    c=true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        threadC.start();
        threadB.start();
        threadA.start();
    }
    public void cyclicBarrierOut(){
        final CyclicBarrier cyclicBarrierA = new CyclicBarrier(2);
        final CyclicBarrier cyclicBarrierB = new CyclicBarrier(2);
        final CyclicBarrier cyclicBarrierC = new CyclicBarrier(2);
        Thread threadA = new Thread(() -> {
            try {
                for(int i=0;i<10;i++){
                    System.out.println("A");
                    cyclicBarrierA.await();
                    cyclicBarrierC.await();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                for(int i=0;i<10;i++){
                    cyclicBarrierA.await();
                    System.out.println("B");
                    cyclicBarrierB.await();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                for(int i=0;i<10;i++){
                    cyclicBarrierB.await();
                    System.out.println("C");
                    //等待ABC都运行完
                    cyclicBarrierC.await();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        threadC.start();
        threadB.start();
        threadA.start();
    }

}
