package com.ludan.generator.common;

/**
 * Description com.inspur.edp.epp.infrastructure
 * Created by chengch
 * date on 2020/3/6
 */
public interface Session {
    String getUserId();
    String getUserName();
    String getUserCode();
    String getTenantId();
}
