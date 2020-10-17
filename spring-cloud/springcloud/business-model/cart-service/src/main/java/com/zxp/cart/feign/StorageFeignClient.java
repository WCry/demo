package com.zxp.cart.feign;

import com.zxp.stock.StockAPI;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "storage-service")
public interface StorageFeignClient extends StockAPI {

}
