package com.zxp.api.goods.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="tb_category_brand")
public class CategoryBrand implements Serializable{

	@Id
    @Column(name = "category_id")
	private Integer categoryId;//分类ID

    @Column(name = "brand_id")
	private Integer brandId;//品牌ID

}
