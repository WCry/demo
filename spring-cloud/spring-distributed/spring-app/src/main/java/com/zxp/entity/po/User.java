package com.zxp.entity.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String userID;
    private String name;
    private Integer age;
    private String sex;
    private String address;
}
