package com.zxp.entity.po;

import lombok.*;


import javax.persistence.*;

/**
 *Entity 是表的实体
 *@Table 的catalog 代表数据库实例
 *@schema 表空间类似的概念
 *可以通过领域对象接口定义方式 返回部分需要的字段
 * 参看UserIDAndName的定义返回
 */
@Entity
@Data
public class UserStudent {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String sex;
    private String address;
    @Column(name="ddd_dsad")
    private Integer dddDsad;
    @Version
    private int version;

}
