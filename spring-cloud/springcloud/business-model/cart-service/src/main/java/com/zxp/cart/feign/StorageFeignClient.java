package com.zxp.cart.feign;

import com.zxp.user.api.storage.StorageAPI;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "storage-service", url = "localhost:8081")
public interface StorageFeignClient extends StorageAPI {

}
