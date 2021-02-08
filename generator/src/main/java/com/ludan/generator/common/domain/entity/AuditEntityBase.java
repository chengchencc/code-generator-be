package com.ludan.generator.common.domain.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Description epp.cloud.widget.core.entities.common
 * Created by chengch
 * date on 2019/8/26
 */
@MappedSuperclass
@EntityListeners(value = AuditEntityListener.class)
public class AuditEntityBase<T extends Serializable> extends EntityBase<T> {

    @Column
    protected Date creationTime;
    @Column(length = 36)
    protected String creationUserId;
    @Column
    protected Date lastModifyTime;
    @Column(length = 36)
    protected String lastModifyUserId;
    @Column
    protected Date deletionTime;
    @Column(length = 36)
    protected String deletionUserId;


    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getCreationUserId() {
        return creationUserId;
    }

    public void setCreationUserId(String creationUserId) {
        this.creationUserId = creationUserId;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    public Date getDeletionTime() {
        return deletionTime;
    }

    public void setDeletionTime(Date deletionTime) {
        this.deletionTime = deletionTime;
    }

    public String getDeletionUserId() {
        return deletionUserId;
    }

    public void setDeletionUserId(String deletionUserId) {
        this.deletionUserId = deletionUserId;
    }
}

