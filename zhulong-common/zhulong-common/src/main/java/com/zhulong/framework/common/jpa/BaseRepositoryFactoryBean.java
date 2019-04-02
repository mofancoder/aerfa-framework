package com.zhulong.framework.common.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * BaseRepository工厂
 * @param <T>
 * @param <ID>
 * @param <R>
 * @author xxc
 * @time 2019-1-7 20:09
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, ID >, T, ID extends Serializable > extends JpaRepositoryFactoryBean<R, T, ID> {

    public BaseRepositoryFactoryBean(Class < ? extends R > repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new BaseRepositoryFactory(em);
    }

    /**
     * 内部类工厂
     * @author xxc
     * @time 2019-1-8 9:11
     */
    private static class BaseRepositoryFactory<T, ID extends Serializable>
            extends JpaRepositoryFactory {

        private final EntityManager em;

        BaseRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        @Override
        protected BaseRepositoryImpl<T, ID> getTargetRepository(RepositoryInformation information) {
            JpaEntityInformation<?, ?> entityInformation = getEntityInformation(information.getDomainType());

            return getTargetRepositoryViaReflection(information, entityInformation, em);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }
}
