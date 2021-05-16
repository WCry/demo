//package com.zxp;
//
//
//import com.zxp.dao.UserRepositoryByQuery;
//import com.zxp.entity.po.UserIdAndName2;
//import com.zxp.entity.po.UserStudent;
//import com.zxp.entity.po.UserIdAndName;
//import com.zxp.model.Employee;
//import com.zxp.model.EmployeeNameOnly;
//import com.zxp.model.EmployeeNameOnlyDto;
//import com.zxp.repository.EmployeeRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
////@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserStudentRepositoryByQueryTests {
//
//    @Autowired
//    private UserRepositoryByQuery userRepository;
//    @Autowired
//    EmployeeRepository employeeRepository;
//    @Test
//    public void findUserTest() {
////        Employee employee=new Employee();
////        employee.setId(3L);
////        employee.setAge(12);
////        employee.setLastName("dasd");
////        employee.setEmail("dsadddddddd");
////        employeeRepository.save(employee);
////        List<Employee> dasd=  employeeRepository.findLastNamePage(0,5);
////        dasd.get(0);
////        System.out.println(dasd.get(0).getLastName());
////        UserStudent userStudent = new UserStudent();
////        userStudent.setId("222");
////        userStudent.setName("dsad");
////        userStudent.setDddDsad(1122);
////        userRepository.save(userStudent);
//
//        List<UserIdAndName> dsa=userRepository.findUserByNativeQuery();
//        System.out.println(dsa.get(0).getId());
//        System.out.println(dsa.get(0).getName());
//        dsa.get(0).getDddDsad();
//        UserIdAndName ds=dsa.get(0);
//        ds.getName();
//        System.out.println(ds.getDddDsad());
//
////        UserIdAndName dsa=userRepository.findUserByNativeQuery("222");
////        System.out.println(dsa.getId());
////        System.out.println(dsa.getName());
////        System.out.println(dsa.getDddDsad());
//    }
//
//    @Test
//    public void findUserByParamTest() {
//        Long id = 1L;
//        UserStudent userStudent = userRepository.findUserByParam(id);
//        System.out.println("user:" + userStudent);
//    }
//
//    @Test
//    public void findUserByIdAndNameTest() {
//        Long id = 1L;
//        String name = "ljk";
//        UserStudent userStudent = userRepository.findUserByIdAndName(id, name);
//        System.out.println("user:" + userStudent);
//    }
//
//    @Test
//    public void findUserListTest() {
//        List<UserStudent> userStudentList = userRepository.findUserList();
//        System.out.println("userList:" + userStudentList);
//    }
//
//    @Test
//    public void findUserListByLikeNameTest() {
//        String name = "j";
//        List<UserStudent> userStudentListByLikeName = userRepository.findUserListByLikeName(name);
//        System.out.println("userListByLikeName:" + userStudentListByLikeName);
//    }
//
//    @Test
//    public void findUserListByLikeConcatNameTest() {
//        String name = "j";
//        List<UserStudent> userStudentListByLikeName = userRepository.findUserListByLikeConcatName(name);
//        System.out.println("userListByLikeName:" + userStudentListByLikeName);
//    }
//
////    @Test
////    public void findUserByNativeQueryTest() {
////        int id = 1;
////        UserIdAndName user = userRepository.findUserByNativeQuery(id);
////        System.out.println("user:" + user);
////    }
//
//    @Test
//    public void findByLastnameTest() {
//        String str = "wyj";
//        List<UserStudent> userStudentList = userRepository.findBySpEL(str);
//        System.out.println("userList:" + userStudentList);
//    }
//
//}
