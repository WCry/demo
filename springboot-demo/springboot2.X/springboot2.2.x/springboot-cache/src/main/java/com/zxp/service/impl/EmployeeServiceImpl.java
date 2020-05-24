package com.zxp.service.impl;


import com.zxp.entity.Employee;
import com.zxp.mapper.EmployeeMapper;
import com.zxp.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Cacheable(value = "emp" , key = "#root.args[0]" , condition = "#id > 0" , unless = "#result eq null")
    @Override
    public Employee getById(Serializable id) {
        System.out.println("getById");
        return super.getById( id );
    }

    @Override
    @CachePut(value = "emp", key = "#root.args[0].id", unless = "#result eq null ")
    public Employee updateEmployeeById(Employee entity) {
        boolean res = super.updateById( entity );
        if (res){
            return entity;
        }
        return null;
    }

    @CacheEvict(value = "emp", key = "#root.args[0]", condition = "#result eq true")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById( id );
    }

}
