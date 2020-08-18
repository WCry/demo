package com.example.consulfeignclient;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Iterator;
import java.util.Map;

/**
 * user:zxp
 * Day:2020,08,16
 **/
public class LoadBalanceController {
    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    private LoadBalancerClient loadBalancerClient;
    /**
     * 获取Consul 的客户端
     */
    private ConsulClient consulClient;



    @Autowired
    public LoadBalanceController(LoadBalancerClient loadBalancerClient, ConsulClient consulClient) {
        this.loadBalancerClient = loadBalancerClient;
        this.consulClient = consulClient;
    }

    @PutMapping(value = "/servers/")
    public String cleanServerRegister() {
        if (consulClient != null) {
            //获取所有的services  健康检查状况
            Iterator<Map.Entry<String, Check>> iterator = consulClient.getAgentChecks().getValue().entrySet().iterator();
            Map.Entry<String, Check> serviceCheckMap = null;
            while (iterator.hasNext()) {
                serviceCheckMap = iterator.next();
                //获取服务名称
                String serviceName = serviceCheckMap.getValue().getServiceName();
                //获取服务ID
                String serviceId = serviceCheckMap.getValue().getServiceId();
                //判断Service的状态
                if (serviceCheckMap.getValue().getStatus() == Check.CheckStatus.CRITICAL) {
                    //取消严重警告的服务的注册
                    consulClient.agentServiceDeregister(serviceId);
                }
            }
        }
        return "json";
    }
}
