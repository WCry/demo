package com.zxp.repository;


import com.zxp.model.Employee;
import com.zxp.model.EmployeeMixDto;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ChangLiang
 * @date 2019/6/3
 */
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Employee getEmployeeById2(Long id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public EmployeeMixDto getEmployeeMixDto(String mixId) {
//        long id = Long.parseLong(IdUtil.getId(mixId));
//        String sql = "select id, last_name from tbl_employee where id = ?";
//        Query query = entityManager.createNativeQuery(sql);
//        query.setParameter(1, id);
//        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        List resultList = query.getResultList();
//        EmployeeMixDto employeeMixDto = new EmployeeMixDto();
//        for (Object o : resultList) {
//            HashMap map = (HashMap) o;
//            employeeMixDto.setId(IdUtil.getMixId(String.valueOf(map.get("id"))));
//            employeeMixDto.setLastName(String.valueOf(map.get("last_name")));
//        }
        return null;
    }

    @Override
    public List<EmployeeMixDto> getPageEmployeeMixDto(Employee employee) {
        String sql = "select id,last_name from tbl_employee order by id desc limit ?,?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, (employee.getPageNum() - 1) * employee.getPageSize());
        query.setParameter(2, employee.getPageSize());
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List resultList = query.getResultList();
        List<EmployeeMixDto> list = new ArrayList<>();
        for (Object o : resultList) {
            EmployeeMixDto employeeMixDto = new EmployeeMixDto();
            HashMap map = (HashMap) o;
           // employeeMixDto.setId(IdUtil.getMixId(String.valueOf(map.get("id"))));
            employeeMixDto.setLastName(String.valueOf(map.get("last_name")));
            list.add(employeeMixDto);
        }
        return list;
    }
}
