package com.zxp.dao;

import com.zxp.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LockUserJpaRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("update User m set m.age=?2 where  m.id=?1")
    void updateAgeByID(Long id, Integer age);

    Optional<User> findById(Long id);

}
