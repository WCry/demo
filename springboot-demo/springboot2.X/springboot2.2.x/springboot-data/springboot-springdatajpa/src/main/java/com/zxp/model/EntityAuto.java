package com.zxp.model;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author ChangLiang
 * @date 2019/6/3
 */
@MappedSuperclass
@EntityListeners({EntityAutoListener.class})
public class EntityAuto {
    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
