package com.zxp.model;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * @author ChangLiang
 * @date 2019/6/3
 */
public class EntityAutoListener {


    @PrePersist
    public void prePersist(EntityAuto entityAuto) {
        Date now = new Date();
        entityAuto.setCreateTime(now);
        entityAuto.setModifyTime(now);
    }

    @PreUpdate
    public void preUpdate(EntityAuto entityAuto) {
        Date now = new Date();
        entityAuto.setModifyTime(now);
    }
}
