package com.zxp.dao;

import com.zxp.entity.po.UserIdAndName;
import com.zxp.entity.po.UserStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

/**
 * 可以快速自定义查询方法
 */
public interface UserRepositoryByQuery extends JpaRepository<UserStudent, Long>,
        JpaSpecificationExecutor<UserStudent>{

    /**
     * 根据id查询用户信息，其中User为ORM映射的类名，即为ORM映射的实体类名
     * 根据参数的顺序，分别为?1，?2，?3......
     * @param id
     * @return
     */
    @Query("select u from UserStudent u where u.id=?1")
    public UserStudent findUser(Long id);

    /**
     * 根据id查询用户信息，其中User为ORM映射的类名，即为ORM映射的实体类名
     *
     * @param id
     * @return
     */
    @Query("select u from UserStudent u where u.id=:id")
    public UserStudent findUserByParam(@Param("id") Long id);

    /**
     * 根据id查询用户信息，其中User为ORM映射的类名，即为ORM映射的实体类名
     * 根据参数的顺序，分别为?1，?2，?3......
     *
     * @param id
     * @return
     */
    @Query("select u from UserStudent u where u.id=?1 and u.name=?2")
    public UserStudent findUserByIdAndName(Long id, String name);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Query("select u from UserStudent u")
    public List<UserStudent> findUserList();

    /**
     * 根据name模糊查询
     *
     * @param name
     * @return
     */
    @Query("select u from UserStudent u where name like %?1%")
    public List<UserStudent> findUserListByLikeName(String name);

    /**
     * 根据name模糊查询
     *
     * @param name
     * @return
     */
    @Query("select u from UserStudent u where name like concat('%',?1,'%') ")
    public List<UserStudent> findUserListByLikeConcatName(String name);

    /**
     * 根据name模糊查询
     *
     * @param id
     * @return
     */
//    @Query(value = "select new com.zxp.entity.po.UserIdAndName2(u.id,u.name,u.dddDsad) " +
//            "from UserStudent u")
//    //,nativeQuery=true)
//    List<UserIdAndName> findUserByNativeQuery();
//    @Query(value = "select id as id,name as name ,ddd_dsad as ddd_dsad  from user_student",nativeQuery=true)
//       // ,nativeQuery=true)
//    List<UserIdAndName> findUserByNativeQuery();

    //@Query(value = "select id,name,ddd_dsad as dddDsad  from user_student",nativeQuery=true)
    @Query(value = "select id,name,ddd_dsad  from user_student",nativeQuery=true)
        // ,nativeQuery=true)
    List<UserIdAndName> findUserByNativeQuery();

    /**
     * 使用SpEL表达式
     * <p>
     * 从Spring Data JPA 1.4版开始，我们支持在手动定义的查询中使用受限制的SpEL模板表达式@Query。
     * 在执行查询时，将根据预定义的变量集评估这些表达式。Spring Data JPA支持一个名为的变量entityName。
     * 它的用法是select x from #{#entityName} x。
     * 它插入entityName与给定存储库关联的域类型。
     * 该entityName解决如下：如果域类型已设置的name属性@Entity的注释，它被使用。否则，使用域类型的简单类名
     *
     * @param name
     * @return
     */
    @Query("select u from #{#entityName} u where u.name = ?1")
    public List<UserStudent> findBySpEL(String name);

}
