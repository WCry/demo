package concurrent;

import java.io.IOException;
import java.util.concurrent.Phaser;

/**
 * Phaser相位器
 * Content:
 *          //及第一个人 作为管理者
 *         //到达之前 线进行注册一个
 *         phaser.register();
 *         //注册的初始化不能为0 先注册一个 保证每次注册数量比到达数多一个
 *         //最后释放第一个注册 作为都到达的信号
 *  https://blog.csdn.net/u010739551/article/details/51083004
 */
public class TestPhaser {
    // 每个Phaser对象对应的工作线程（任务）数
    private static final int TASKS_PER_PHASER = 4;

    public static void main(String[] args) throws IOException {
        // 指定任务最多执行的次数
        int repeats = 3;
        Phaser phaser = new Phaser(1) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("---------------PHASE[" + phase + "]," + "Parties[" + registeredParties + "] ---------------");
                //phase  阶段时期
                return phase + 1 >= repeats || registeredParties == 0;
            }
        };
        ddd(phaser);
        System.out.println(phaser.getRegisteredParties()+"dsaaaaa");
    }
    private static  void ddd(Phaser phaser){
        Tasker[] taskers = new Tasker[10];
        // 根据任务数,为每个任务分配Phaser对象
        build(taskers, 0, taskers.length, phaser);
        // 执行任务
        for (int i = 0; i < taskers.length; i++) {
            Thread thread = new Thread(taskers[i]);
            thread.start();
        }
        System.out.println("主线程等待了");
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程也注册了");

    }

    private static void build(Tasker[] taskers, int lo, int hi, Phaser phaser) {
        if (hi - lo > TASKS_PER_PHASER) {
            for (int i = lo; i < hi; i += TASKS_PER_PHASER) {
                int j = Math.min(i + TASKS_PER_PHASER, hi);
                build(taskers, i, j, new Phaser(phaser));
            }
        } else {
            for (int i = lo; i < hi; ++i) {
                phaser.register();
                taskers[i] = new Tasker(phaser);
            }
        }
    }

    static class Tasker implements Runnable {
        private final Phaser phaser;

        Tasker(Phaser phaser) {

            this.phaser = phaser;
        }

        @Override
        public void run() {
            while (!phaser.isTerminated()) {   //只要Phaser没有终止, 各个线程的任务就会一直执行
                int i = phaser.arriveAndAwaitAdvance();     // 等待其它参与者线程到达
                // do something
                System.out.println(Thread.currentThread().getName() + ": 执行完任务");
            }
        }
    }
}
