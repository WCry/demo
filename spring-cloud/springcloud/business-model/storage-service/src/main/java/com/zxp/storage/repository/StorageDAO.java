package com.zxp.storage.repository;

import com.zxp.storage.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StorageDAO extends JpaRepository<Storage, String> {

    Storage findByCommodityCode(String commodityCode);

}
