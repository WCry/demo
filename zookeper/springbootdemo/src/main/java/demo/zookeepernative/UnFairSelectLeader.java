package demo.zookeepernative;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * Author: zhangxuepei
 * Date: 2020/3/14 20:09
 * Content:
 */

public class UnFairSelectLeader {
    private static CountDownLatch latch;
    private boolean leader = false;
    private ZooKeeper zk;
    //连接的zk的地址及端口号配置
    private String connectString = "10.19.154.187:2181,10.19.154.188:2181,10.19.154.189:2181" + ",10.19.154.191:2181,10.19.154.192:2181";
    //会话超时时间,单位毫秒
    private int sessionTimeout = 15000;

    private UnFairSelectLeader() {
        try {
            latch = new CountDownLatch(1);
            zk = new ZooKeeper(connectString, sessionTimeout, event -> {
                System.out.println(event.getType() + "---" + event.getPath() + "---" + event.getState());
                if (zk.getState().equals(ZooKeeper.States.CONNECTED)) {
                    latch.countDown();
                    System.out.println("---------------------------------");
                }
            });
            //zk启动后试着进行选举
            latch.await();
            selection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selection() throws Exception {
        try {
            //无法递归创建 节点，  如果父节点 不存在则会创建失败
            //采用 非公平方式 谁县创建成功税作为Leader
            //参数 1. 路径 2.数据，3开放的都可以修改  4.临时性节点，短暂节点
            zk.create("/server", "node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            leader = true;
            //2、没有抛异常，表示创建节点成功了
            System.out.println("选举成功！我是Leader");
        } catch (KeeperException.NodeExistsException e) {
            System.out.println("被选举失败！我是Follower。");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //3、监听节点删除事件，如果删除了，重新进行选举
            zk.getData("/server", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println(event.getType() + "---" + event.getPath() + "---" + event.getState());
                    try {
                        //监听事件 如果节点删除
                        if (Objects.equals(event.getType(), Event.EventType.NodeDeleted)) {
                            leader = false;
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
