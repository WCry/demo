package com.zxp.repository;


import com.zxp.model.Employee;
import com.zxp.model.EmployeeNameOnly;
import com.zxp.model.EmployeeNameOnlyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee>, EmployeeRepositoryCustom {

    Optional<Employee> findById(Long id);

    @Query(value = "select lastName from tbl_employee order by id desc limit ?1,?2",nativeQuery = true)
    List<Employee> findLastNamePage(Integer pageNum, Integer pageSize);

    @Modifying
    @Query(value = "update tbl_employee e set e.email = :email where id=:id",nativeQuery = true)
    Integer updateEmployeeEmail(@Param("id") Long id, @Param("email") String email);
}
