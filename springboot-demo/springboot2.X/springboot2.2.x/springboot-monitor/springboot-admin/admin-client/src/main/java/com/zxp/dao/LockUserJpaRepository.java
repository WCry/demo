package com.zxp.dao;

import com.zxp.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;


public interface LockUserJpaRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("update User m set m.age=?2 where  m.id=?1")
    @Lock(LockModeType.WRITE)
    void updateStateByMoney(Long id, Integer age);


    @Lock(LockModeType.WRITE)
    Optional<User> findById(Long id);


}
