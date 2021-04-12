package com.zxp.entity.po;

import lombok.*;


import javax.persistence.*;

/**
 *Entity 是表的实体
 *@Table 的catalog 代表数据库实例
 *@schema 表空间类似的概念
 *
 */
@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private String address;
    @Version
    private int version;

}
