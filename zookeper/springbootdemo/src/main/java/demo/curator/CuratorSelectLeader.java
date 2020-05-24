package demo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Author: zhangxuepei
 * Date: 2020/3/15 20:27
 * Content: 通过Curator 实现Leader选举
 */
@Component
public class CuratorSelectLeader {
    private String zookeeperConnectString = "10.19.154.187:2181,10.19.154.188:2181,10.19.154.189:2181,10.19.154.191:2181,10.19.154.192:2181";
    private Boolean leader = false;
    private LeaderSelector leaderSelector;
    private CuratorFramework client;
    private CountDownLatch countDownLatch;
    public CuratorSelectLeader() {
        countDownLatch=new CountDownLatch(1);
        //zk的重连策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //获取连接
        client = CuratorFrameworkFactory.newClient(zookeeperConnectString, retryPolicy);
        client.start();
        //选举的节点信息放在这个path下
        String path = "/newserver/leader";
        //这里建议使用LeaderSelectorListenerAdapter，它实现了stateChanged，
        // 当与zk失连后，会自动取消领导权
        leaderSelector = new LeaderSelector(client, path, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("成为leader了");
                leader=true;
                countDownLatch.await();
                System.out.println("放弃成为leader");
                leader=false;
            }

        });
        //放弃领导权之后，自动再次竞选
        leaderSelector.autoRequeue();
        leaderSelector.start();
    }

    public void closeSelect() {
        countDownLatch.countDown();
//        leaderSelector.close();
//        client.close();
    }

    public Boolean getLeader() {
        return leader;
    }
}
