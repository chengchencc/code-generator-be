package com.ludan.generator.common.domain.entity;

import com.ludan.generator.common.Session;
import com.ludan.generator.common.utils.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * Description com.inspur.edp.epp.infrastructure.domain
 * Created by chengch
 * date on 2020/3/6
 */
@Component
@Transactional
public class AuditEntityListener {

    @Autowired
    ApplicationContext context;

//    public AuditEntityListener(ApplicationContext context) {
//        userSession = context.getBean(ISession.class);
//    }

    @PrePersist
    public void touchForCreate(AuditEntityBase entity){
        Session userSession = context.getBean(Session.class);
        entity.setCreationTime(Clock.now());
        entity.setCreationUserId(userSession.getUserId());
    }

    @PreUpdate
    public void touchForUpdate(AuditEntityBase entity){
        Session userSession = context.getBean(Session.class);
        entity.setLastModifyTime(Clock.now());
        entity.setLastModifyUserId(userSession.getUserId());
    }
    @PreRemove
    public void touchForDelete(AuditEntityBase entity){
        Session userSession = context.getBean(Session.class);
        entity.setDeletionTime(Clock.now());
        entity.setDeletionUserId(userSession.getUserId());
    }

}
