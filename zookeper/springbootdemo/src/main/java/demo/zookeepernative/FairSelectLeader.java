package demo.zookeepernative;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * Author: zhangxuepei
 * Date: 2020/3/15 9:48
 * Content:
 */
@Component
public class FairSelectLeader {
    private static CountDownLatch latch;

    private boolean leader = false;
    private ZooKeeper zk;
    //连接的zk的地址及端口号配置
    private String connectString = "10.19.154.187:2181,10.19.154.188:2181,10.19.154.189:2181,10.19.154.191:2181,10.19.154.192:2181";
    //会话超时时间,单位毫秒
    private int sessionTimeout = 15000;
    private String serverPathpre = "/server";
    private String serverPath = "/server/";
    private String leaderPath = "/server/leader";
    private String nodeVal;

    public FairSelectLeader() {
        try {
            latch = new CountDownLatch(1);
            zk = new ZooKeeper(connectString, sessionTimeout, event -> {
                System.out.println(event.getType() + "---" + event.getPath() + "---" + event.getState());
                if (zk.getState().equals(ZooKeeper.States.CONNECTED)) {
                    latch.countDown();
                }
            });
            //zk启动后试着进行选举
            latch.await();
            Stat stat = zk.exists(serverPathpre, true);
            if (stat == null) {
                nodeVal = zk.create(serverPathpre, "node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.CONTAINER);
            }
            //1、创建/server（这个通过zkCli创建好了），注意这里是EPHEMERAL_SEQUENTIAL的
            //2、和非公平模式不一样，只需要创建一次节点就可以了
            nodeVal = zk.create(leaderPath, "node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(nodeVal);
            selection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selection() throws Exception {
        //2、遍历/server下的子节点，看看自己的序号是不是最小的
        List<String> children = zk.getChildren(serverPathpre, null);
        Collections.sort(children);
        String formerNode = "";  //前一个节点，用于监听
        for (int i = 0; i < children.size(); i++) {
            String node = children.get(i);
            if (nodeVal.equals(serverPath + node)) {
                if (i == 0) {
                    leader = true;
                    //第一个
                    System.out.println("我被选为leader节点了");
                } else {
                    formerNode = children.get(i - 1);
                }
            }
        }
        if (!"".equals(formerNode)) {
            //自己不是第一个，如果是第一个formerNode应该没有值
            System.out.println("我竞选失败了");
            //3、监听前一个节点的删除事件，如果删除了，重新进行选举
            zk.getData(serverPath + formerNode, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    try {
                        if (Objects.equals(event.getType(), Event.EventType.NodeDeleted)) {

                            selection();
                        }
                    } catch (Exception e) {
                    }
                }
            }, null);
        }
    }

    public void CloseZk() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean getLeader() {
        return leader;
    }
}
