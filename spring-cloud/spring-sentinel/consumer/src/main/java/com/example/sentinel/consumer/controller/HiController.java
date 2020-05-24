package com.example.sentinel.consumer.controller;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.Check;
import com.example.api.User;
import com.example.sentinel.consumer.DispatchServiceHi;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Map;

@RestController
public class HiController {

    private final DispatchServiceHi dispatchServiceHi;
    private final LoadBalancerClient loadBalancerClient;
    /**
     * 获取Consul 的客户端
     */
    private final ConsulClient consulClient;

    public HiController(DispatchServiceHi dispatchServiceHi, LoadBalancerClient loadBalancerClient, ConsulClient consulClient) {
        this.dispatchServiceHi = dispatchServiceHi;
        this.loadBalancerClient = loadBalancerClient;
        this.consulClient = consulClient;
    }

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        User dasd = new User();
        dasd.setName("dasd");
        System.out.println(dispatchServiceHi.sayHiOne(dasd));
        return "json";
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
