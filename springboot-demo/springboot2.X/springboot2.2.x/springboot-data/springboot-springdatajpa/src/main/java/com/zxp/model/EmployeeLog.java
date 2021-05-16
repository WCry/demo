package com.zxp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ChangLiang
 * @date 2019/6/5
 */
@Entity
@Table(name = "tbl_employee_log")
public class EmployeeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",columnDefinition = "int comment '自增主键'")
    private Long id;

    @Column(name = "user_id", columnDefinition = "int not null comment '用户id'")
    private Long userId;

    @Column(name = "log", columnDefinition = "varchar(255) default null comment '用户行为日志'")
    private String log;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
