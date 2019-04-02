package com.zhulong.framework.common.jpa;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * BaseRepository的jpa接口的实现
 * @param <T>
 * @param <ID>
 * @author xxc
 * @time 2019-1-7 17:34
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID> {

    private final JpaEntityInformation entityInformation;

    private EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity){
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public <S extends T> S update(S entity) {
        return this.update(entity,new String[]{});
    }

    @Override
    @Transactional
    public <S extends T>  S update(S entity, String[] includeProperties) {
        //获取id
        ID id = (ID) entityInformation.getId(entity);
        //查询数据库
        Optional<T> target=super.findById(id);
        if(target.isPresent()) {
            //获取为空的属性-覆盖的时候忽略
            String[] ignoreProperties = getNotNullProperties(entity, includeProperties);
            //用数据库对象对应的信息覆盖实体中属性为null的信息
            BeanUtils.copyProperties(target.get(), entity, ignoreProperties);
            //最后再更新
            return entityManager.merge(entity);
        }else{
            return null;
        }
    }

    /**
     * 获取不为null的属性
     * @author xxc
     * @return java.lang.String[]
     * @param src
     * @time 2018-9-17 17:03
     */
    private static String[] getNotNullProperties(Object src, String[] includeProperties) {
        //1.获取Bean
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        //2.获取Bean的属性描述
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        //3.获取Bean的属性
        Set<String> properties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : pds) {
            String propertyName = propertyDescriptor.getName();

            //4.包含某些属性
            boolean isExclude=false;
            for (String includePropertie : includeProperties) {
                if(includePropertie.equalsIgnoreCase(propertyName)){
                    isExclude=true;
                    properties.add(propertyName);
                    break;
                }
            }
            if(isExclude){
                continue;
            }

            //5.最终得到的属性
            Object propertyValue = srcBean.getPropertyValue(propertyName);
            if (propertyValue!=null) {
                properties.add(propertyName);
            }
        }
        return properties.toArray(new String[0]);
    }

}
