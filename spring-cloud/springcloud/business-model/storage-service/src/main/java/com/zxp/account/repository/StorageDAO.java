package com.zxp.account.repository;

import com.zxp.account.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StorageDAO extends JpaRepository<Storage, String> {

    Storage findByCommodityCode(String commodityCode);

}
