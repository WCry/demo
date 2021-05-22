package com.zxp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxp.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
