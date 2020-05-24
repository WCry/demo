package com.zxp.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    private String userId;
    private BigDecimal money;
}
