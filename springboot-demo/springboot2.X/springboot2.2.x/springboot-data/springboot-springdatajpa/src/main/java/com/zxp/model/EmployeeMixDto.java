package com.zxp.model;

/**
 * @author ChangLiang
 * @date 2019/6/4
 */
public class EmployeeMixDto {
    private String id;

    private String lastName;

    public EmployeeMixDto() {
    }

    public EmployeeMixDto(String id, String lastName) {
        this.id = id;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
