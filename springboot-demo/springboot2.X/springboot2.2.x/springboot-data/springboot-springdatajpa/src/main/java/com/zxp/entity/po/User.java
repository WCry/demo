package com.zxp.entity.po;

import lombok.*;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 必须有Entity 是表的实体
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

}
