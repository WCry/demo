package com.example.springclouddemo.sericefeign;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

//需要继承抽象类 AbstractLoadBalancerRule

public class MyRule extends AbstractLoadBalancerRule {

    public MyRule(){
        int i=0;
    }
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
       System.out.println(iClientConfig);
    }

    @Override
    public Server choose(Object o) {
        ILoadBalancer loadBalancer = this.getLoadBalancer();
        //获取所有已注册服务，选取一个分配
        //获取所有的Server 的ServerID 维护ServerID
        //和任务。
        //启动时候首先获取server ID 和Server 的能力,
        // 在这里发现Server更新去更新Server的能力。
        //对于获取数据可以扩展权重分配规则
        List<Server> reachableServers = loadBalancer.getReachableServers();
        for (Server server:reachableServers) {
           System.out.println(server.getId());
        }
        int index=1;
        int dsad=Math.round(8);
        System.out.println(dsad);
        if(dsad/2==0){
            index=0;
        }
        return reachableServers.get(0);
    }

}
