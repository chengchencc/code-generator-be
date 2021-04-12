package com.ludan.generator.common.controller;

import com.ludan.generator.common.Session;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description com.inspur.edp.epp.framework.web.common.entities
 * Created by chengch
 * date on 2019/11/15
 */
public abstract class ControllerBase {
    @Autowired
    protected Session session;
}
