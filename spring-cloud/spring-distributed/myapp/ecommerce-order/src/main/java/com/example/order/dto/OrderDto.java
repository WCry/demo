package com.example.order.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * user:zxp
 * Day:2020,06,14
 **/
@Data
public class OrderDto implements Serializable {
    private static final long serialVersionUID = 2025302019686210962L;
    private String orderID;
}
