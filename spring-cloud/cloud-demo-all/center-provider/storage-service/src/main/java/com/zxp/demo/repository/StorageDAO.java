package com.zxp.demo.repository;

import com.zxp.demo.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StorageDAO extends JpaRepository<Storage, String> {

    Storage findByCommodityCode(String commodityCode);

}
