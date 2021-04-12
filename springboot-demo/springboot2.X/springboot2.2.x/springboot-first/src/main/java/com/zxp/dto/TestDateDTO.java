package com.zxp.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class TestDateDTO {
    //@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    Date date;
}
