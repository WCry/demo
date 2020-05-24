package com.example.springjpademo.repositorie;

import com.example.springjpademo.projo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends CrudRepository<User, Long> {
    //OPtional  JAVA 8 新特性  允许为空操作
    Optional<User> findById(Long id);
    @Query(value = "select * from t_user where name like %?1%", nativeQuery = true)
    List<User> findByNameLike(String name);

    Page<User> findAll(@PageableDefault(value = 15, sort = {"datetime"}, direction = Sort.Direction.DESC) Pageable pageable);
}
