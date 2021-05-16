package com.zxp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;



@Entity
@Table(name = "tbl_employee")
/*@DynamicInsert
@DynamicUpdate*/
public class Employee extends PageHelper {
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", columnDefinition = "comment '自增主键'")
    private Long id;
    @Column(name = "name", columnDefinition = "varchar(50) not null comment '用户姓名'")
    private String name;
    @Column(name = "email", columnDefinition = "varchar(50) not null comment '邮件'")
    private String email;
    @Column(name = "age", columnDefinition = "comment '年龄'")
    private Integer age;*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",columnDefinition = "int comment '自增主键'")
    private Long id;
    @Column(name = "last_name", columnDefinition = "varchar(50) DEFAULT NULL comment '用户姓名'")
    private String lastName;
    @Column(name = "email", columnDefinition = "varchar(50) not null comment '邮件'")
    private String email;
    @Column(name = "gender", columnDefinition = "char(1) DEFAULT NULL comment '性别'")
    private Integer gender;
    @Column(name = "age", columnDefinition = "int default null comment '姓名'")
    private Integer age;
//    @Version
    @Column(name = "version", columnDefinition = "int comment '乐观锁版本'")
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
