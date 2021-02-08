package com.ludan.generator.common.domain;

/**
 * Description com.inspur.edp.epp.infrastructure.domain.entities
 * Created by chengch
 * date on 2020/1/3
 */
public interface Entity<T> {
    public T getId();
    public void setId(T id);
}
