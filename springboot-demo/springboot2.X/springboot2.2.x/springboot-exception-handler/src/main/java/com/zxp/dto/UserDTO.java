package com.zxp.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @author zhangxuepei
 * @since 3.0
 */

public class UserDTO {
    @NotEmpty(groups = {DtoGroup.Update.class}, message = "")
    private String id;
    @NotEmpty
    private String name;
    /**
     * 嵌套校验@Valid 证明该参数需要进行嵌套验证
     */
    @Valid
    private UserTel tel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserTel getTel() {
        return tel;
    }

    public void setTel(UserTel tel) {
        this.tel = tel;
    }
}
