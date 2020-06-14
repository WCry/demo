package com.zxp.dto;

import com.zxp.entity.po.User;
import lombok.Data;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Data
public class UserWithCard extends User {
    private int count;
}
