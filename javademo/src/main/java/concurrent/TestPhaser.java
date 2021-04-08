package concurrent;

import java.io.IOException;
import java.util.concurrent.Phaser;

/**
 */
public class TestPhaser {
    // 每个Phaser对象对应的工作线程（任务）数
    private static final int TASKS_PER_PHASER = 1;

    public static void main(String[] args) throws IOException {
        // 指定任务最多执行的次数
        int repeats = 3;
        Phaser phaser = new Phaser(0) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("---------------PHASE[" + phase + "],"
                        + "Parties[" + registeredParties + "] ---------------");
                //phase  阶段时期
                //判断条件 是否需要继续向下执行，
                //当前采用重复次数和注册的数量作为判断的条件
                return phase + 1 >= repeats || registeredParties == 0;
            }
        };
        startTask(phaser);
        System.out.println("当前注册数量："+phaser.getRegisteredParties());
    }
    private static  void startTask(Phaser phaser){
        Task[] tasks = new Task[10];
        // 根据任务数,为每个任务分配Phaser对象
        build(tasks, 0, tasks.length, phaser);
        // 执行任务
        for (int i = 0; i < tasks.length; i++) {
            Thread thread = new Thread(tasks[i]);
            thread.start();
        }
        System.out.println("主线程也注册了");
    }

    private static void build(Task[] tasks, int lo, int hi, Phaser phaser) {
        if (hi - lo > TASKS_PER_PHASER) {
            for (int i = lo; i < hi; i += TASKS_PER_PHASER) {
                int j = Math.min(i + TASKS_PER_PHASER, hi);
                build(tasks, i, j, new Phaser(phaser));
            }
        } else {
            for (int i = lo; i < hi; ++i) {
                phaser.register();
                tasks[i] = new Task(phaser);
            }
        }
    }

    static class Task implements Runnable {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            //只要Phaser没有终止, 各个线程的任务就会一直执行
            while (!phaser.isTerminated()) {
                // 等待其它参与者线程到达
                int i = phaser.arriveAndAwaitAdvance();
                // do something
                System.out.println(Thread.currentThread().getName() + ": 执行完阶段"+i+"任务");
            }
        }
    }
}
