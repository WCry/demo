package com.zxp.entity.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "user_count")
public class UserCount {
    @Id
    @GeneratedValue
    private int id;
    private String userID;
    private BigDecimal count;
    private String name;
    private Integer age;
    private String sex;
    private String address;
}
