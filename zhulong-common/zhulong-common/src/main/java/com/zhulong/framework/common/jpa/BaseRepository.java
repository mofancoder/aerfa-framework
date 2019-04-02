package com.zhulong.framework.common.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

/**
 * BaseRepository的jpa接口
 * @param <T>
 * @param <ID>
 * @author xxc
 * @time 2018-9-4 14:56
 */
@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID>,JpaSpecificationExecutor<T>{

    /**
     * 保存/更新，null字段会强制更新
     * @author xxc
     * @return S
     * @param entity
     * @time 2019-1-7 18:02
     */
    @Transactional
    <S extends T > S save(S entity);

    /**
     * 只更新，null字段不会强制更新
     * @author xxc
     * @return S
     * @param entity
     * @time 2019-1-7 18:00
     */
    @Transactional
    <S extends T > S update(S entity);

    /**
     * 只更新，null字段不会强制更新，包含的属性会强制更新(无论是否为null)
     * @author xxc
     * @return S
     * @param entity
     * @param includeProperties
     * @time 2019-1-7 18:22
     */
    @Transactional
    <S extends T > S update(S entity, String[] includeProperties);
}
