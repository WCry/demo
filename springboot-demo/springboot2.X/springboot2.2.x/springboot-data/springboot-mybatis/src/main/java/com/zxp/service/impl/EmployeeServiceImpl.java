package com.zxp.service.impl;

import com.zxp.entity.Employee;
import com.zxp.mapper.EmployeeMapper;
import com.zxp.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gf
 * @since 2018-11-25
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
   @TransactionalEventListener
}
