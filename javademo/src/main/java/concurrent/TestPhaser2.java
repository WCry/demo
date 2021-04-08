package concurrent;

import java.io.IOException;
import java.util.concurrent.Phaser;

/**
 *
 */
public class TestPhaser2 {

    public static void main(String[] args) throws IOException {
        final Phaser parent = new Phaser();
        final Phaser childPhaser1 = new Phaser(parent);
        final Phaser childPhaser2 = new Phaser(parent);
        childPhaser1.bulkRegister(5);
        childPhaser2.bulkRegister(4);
        for (int i = 0; i < 5; i++) {
            final int index = i;
            Thread t = new Thread(() -> {
                System.out.println("arrive1:" + index);
                childPhaser1.arriveAndAwaitAdvance();
            });
            t.start();
        }
        for (int i = 0; i < 4; i++) {
            final int index = i;
            Thread t = new Thread(() -> {
                System.out.println("arrive2:" + index);
                childPhaser2.arriveAndAwaitAdvance();
            });
            t.start();
        }
        System.out.println("进入等待任务阶段：" + parent.getPhase());
        //await代表需要进入状态
        System.out.println(parent.awaitAdvance(parent.getPhase()));
        System.out.println("任务执行结束");
    }


}
