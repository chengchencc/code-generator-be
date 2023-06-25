package com.ludan.generator.repository;

import com.ludan.generator.dto.DataEntityDto;
import com.ludan.generator.entity.DataEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: chengchen
 * @create: 2021-01-19 11:42
 **/
public interface DataEntityRepository extends JpaRepository<DataEntity,Integer>, JpaSpecificationExecutor<DataEntity>, QuerydslPredicateExecutor<DataEntity> {
    @Query("select new DataEntity(t.id,t.code,t.name) from DataEntity t where name like concat(:name,'%') order by name asc")
    List<DataEntity> findTop10ByNameStartsWithOrderByName(@Param("name") String name, Pageable pageable);
}
