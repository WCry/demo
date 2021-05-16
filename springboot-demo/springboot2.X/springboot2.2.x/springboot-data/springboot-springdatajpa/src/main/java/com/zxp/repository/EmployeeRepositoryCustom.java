package com.zxp.repository;


import com.zxp.model.Employee;
import com.zxp.model.EmployeeMixDto;

import java.util.List;

/**
 * @author ChangLiang
 * @date 2019/6/3
 */
public interface EmployeeRepositoryCustom {

    Employee getEmployeeById2(Long id);

    EmployeeMixDto getEmployeeMixDto(String mixId);

    List<EmployeeMixDto> getPageEmployeeMixDto(Employee employee);
}
