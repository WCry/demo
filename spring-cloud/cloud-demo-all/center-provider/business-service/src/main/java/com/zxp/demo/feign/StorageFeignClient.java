package com.zxp.demo.feign;

import com.zxp.demo.api.StorageServiceAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "storage-service")
public interface StorageFeignClient extends StorageServiceAPI {
}
