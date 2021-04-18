package com.example.jpa.repository;

import com.example.jpa.projo.User;
import com.example.jpa.projo.UserOnlyName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository2 extends JpaRepository<User, Long> {
    @Query(value = "select name from t_user where name like %?1%", nativeQuery = true)
    List<UserOnlyName> findByNameLike(String name);
}
