package com.zxp.demo.feign;

import com.zxp.api.storage.StorageAPI;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "storage-service", url = "localhost:8081")
public interface StorageFeignClient extends StorageAPI {

}
