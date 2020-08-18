package com.zxp.api.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Table(name="tb_cities")
public class Cities implements Serializable{

	@Id
    @Column(name = "cityid")
	private String cityid;//城市ID

    @Column(name = "city")
	private String city;//城市名称

    @Column(name = "provinceid")
	private String provinceid;//省份ID


}
