package com.zxp.entity.po;

import lombok.*;


import javax.persistence.*;

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
    @Version
    private int version;

}
