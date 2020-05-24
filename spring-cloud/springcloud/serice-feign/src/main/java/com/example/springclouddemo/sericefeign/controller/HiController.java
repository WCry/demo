package com.example.springclouddemo.sericefeign.controller;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.Check;
import com.example.springclouddemo.sericefeign.SchedualServiceHi;
import common.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Map;

@RestController
public class HiController {
    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    SchedualServiceHi schedualServiceHi;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    /**
     * 获取Consul 的客户端
     */
    @Autowired
    private ConsulClient consulClient;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        String json = "{indexname：\"test\"}";
        user dasd = new user();
        dasd.setName("dasd");
        System.out.println(schedualServiceHi.sayHiOne(dasd));
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
