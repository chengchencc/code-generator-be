package com.ludan.generator.common.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Description epp.cloud.widget.core.entities.common
 * Created by chengch
 * date on 2019/8/26
 */
@Embeddable
public class SoftDelete {
    @Column
    private Boolean isDelete;

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
