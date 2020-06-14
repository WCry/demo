package com.example.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * user:zxp
 * Day:2020,06,14
 **/
@Data
public class NewOrderDto extends OrderDto implements Serializable {
    private static final long serialVersionUID = -3725205753545023877L;
    private String orderID;
    private List<String> goodsIDs;
}
