package com.ludan.generator.repository;

import com.ludan.generator.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: chengchen
 * @create: 2021-01-19 11:42
 **/
public interface DataEntityRepository extends JpaRepository<DataEntity,Integer>, JpaSpecificationExecutor<DataEntity> {


}
