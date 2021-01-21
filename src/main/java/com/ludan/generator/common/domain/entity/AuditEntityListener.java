package com.ludan.generator.common.domain.entity;

import com.ludan.generator.common.ISession;
import com.ludan.generator.common.utils.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PrePersist;
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
        ISession userSession = context.getBean(ISession.class);
        entity.setCreationTime(Clock.now());
        entity.setCreationUserId(userSession.getUserId());
    }

    @PreUpdate
    public void touchForUpdate(AuditEntityBase entity){
        ISession userSession = context.getBean(ISession.class);
        entity.setLastModifyTime(Clock.now());
        entity.setLastModifyUserId(userSession.getUserId());
    }

}
