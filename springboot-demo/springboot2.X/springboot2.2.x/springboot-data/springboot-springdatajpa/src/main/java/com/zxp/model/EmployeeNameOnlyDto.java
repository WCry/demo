package com.zxp.model;


public class EmployeeNameOnlyDto {

    private String lastName;

    // 使用entityManage Transformers.aliasToBean 的时候，要有默认的构造函数才可以
    public EmployeeNameOnlyDto(){}

    // 使用dto来获取部分字段内容
    public EmployeeNameOnlyDto(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
