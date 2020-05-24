import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.Check;
import com.ecwid.consul.v1.agent.model.Service;
import com.ecwid.consul.v1.health.HealthServicesRequest;
import com.ecwid.consul.v1.health.model.HealthService;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class consulApI {
    public static void main(String[] args) {
        LocalDateTime localDateTime= LocalDateTime.now();
        ConsulClient consulClient=new ConsulClient("10.19.154.66", 8500);
        consulApI consulApI=new consulApI();
        consulApI.deleteUnHealthService(consulClient);
//        consulClient.getAgentServices();
//        for (int i = 0; i < 10000; i++) {
//            consulClient.deleteKVValue("consulClient"+i);
//        }
//        Duration duration=Duration.between(LocalDateTime.now(),localDateTime);
//        System.out.println(duration);
//        //consul 客户端 获取健康的Services。
//        HealthServicesRequest request = HealthServicesRequest.newBuilder()
//                .setTag("EU-West")
//                .setPassing(true)
//                .setQueryParams(QueryParams.DEFAULT)
//                .build();
//        consulClient.getAgentServices();
//        //查询出健康的节点
//        //
//        Response<List<HealthService>> healthServicesRe=consulClient.getHealthServices("server-hi",request);
//
//        //获取健康节点的
//        //
//        List<HealthService> healthServices=healthServicesRe.getValue();
//        for(int i=0;i<healthServices.size();i++){
//            //获取Service 的ID
//            System.out.println(healthServices.get(i).getService().getId());
//        }
//        //查询出所有代理的节点  及所有注册在改注册中心的所有的节点
//        System.out.println( consulClient.getAgentServices().getValue().toString());
    }

    public void deleteUnHealthService( ConsulClient consulClient){
        //获取所有的services检查信息
        Iterator<Map.Entry<String, Check>> it = consulClient.getAgentChecks().getValue().entrySet().iterator();
        Map.Entry<String, Check> serviceMap = null;
        while (it.hasNext()) {
            //迭代数据
            serviceMap = it.next();
            //获取服务名称
            String serviceName = serviceMap.getValue().getServiceName();
            //获取服务ID
            String serviceId = serviceMap.getValue().getServiceId();
            System.out.println(MessageFormat.format("服务名称 :{0}**服务ID:{1}", serviceName, serviceId));
            //获取健康状态值  PASSING：正常  WARNING  CRITICAL  UNKNOWN：不正常
            System.out.println(MessageFormat.format("服务 :{0}的健康状态值：{1}", serviceName, serviceMap.getValue().getStatus()));
            if ( serviceMap.getValue().getStatus() == Check.CheckStatus.CRITICAL) {
                System.out.println(MessageFormat.format("服务 :{0}为无效服务，准备清理...................", serviceName));
                consulClient.agentServiceDeregister(serviceId);
            }
        }
    }
}
