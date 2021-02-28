package com.zxp.service;

import com.zxp.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface EmployeeService extends IService<Employee> {

    Employee updateEmployeeById(Employee entity);

}
