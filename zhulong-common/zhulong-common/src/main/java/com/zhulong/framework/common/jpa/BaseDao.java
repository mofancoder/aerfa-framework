package com.zhulong.framework.common.jpa;

import com.zhulong.framework.common.page.Pagination;
import org.hibernate.query.NativeQuery;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 父级dao，提供Finder查询和原生sql查询，包含分页查询
 * @author xxc
 * @return
 * @time 2019-1-8 17:50
 */
public abstract class BaseDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 获取EntityManager
     * @author xxc
     * @return javax.persistence.EntityManager
     * @param
     * @time 2019-1-8 15:28
     */
    protected EntityManager getEntityManager() {

        return this.entityManager;
    }

    /**
     * 设置参数
     * @author xxc
     * @return void
     * @param params
     * @param query
     * @time 2018-9-21 17:01
     */
    protected void setParam(Map<String,Object> params,Query query ){
        if(params!=null){
            for(Map.Entry<String,Object> entry:params.entrySet()){
                query.setParameter(entry.getKey(),entry.getValue());
            }
        }
    }

    /**
     * 创建查询
     * @author xxc
     * @return javax.persistence.Query
     * @param jpql
     * @time 2019-1-8 15:16
     */
    protected Query createQuery(String jpql, Object... params) {

        Assert.hasText(jpql,"jpql is not null！");

        Query query = entityManager.createQuery(jpql);

        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }

        return query;
    }

    /**
     * 创建本地sql查询
     * @author xxc
     * @return javax.persistence.SQLQuery
     * @param sql
     * @time 2018-9-25 20:08
     */
    protected NativeQuery createNativeQuery(String sql) {

        Assert.hasText(sql,"sql is not null！");

        return entityManager.createNativeQuery(sql).unwrap(NativeQuery.class);
    }

    /**
     * 查询对象列表数据
     * @author xxc
     * @return java.util.List
     * @param jpql
     * @param params
     * @time 2019-1-8 15:45
     */
    protected List find(String jpql, Object... params) {
        return createQuery(jpql, params).getResultList();
    }

    /**
     * 通过Finder获得列表数据
     * @author xxc
     * @return java.util.List
     * @param finder
     * @time 2019-1-8 16:10
     */
    protected List find(Finder finder) {
        Query query = finder.createQuery(entityManager);
        List list = query.getResultList();
        return list;
    }

    /**
     * 查询唯一对象
     * @author xxc
     * @return java.lang.Object
     * @param jpql
     * @param params
     * @time 2019-1-8 15:46
     */
    protected Object findUnique(String jpql, Object... params) {
        Query query = createQuery(jpql, params);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    /**
     * 通过Finder对象查询唯一对象
     * @author xxc
     * @return java.lang.Object
     * @param finder
     * @time 2019-1-8 16:03
     */
    protected Object findUnique(Finder finder) {
        Query query = finder.createQuery(entityManager);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    /**
     * 获得Finder的记录总数
     * @author xxc
     * @return int
     * @param finder
     * @time 2019-1-8 17:11
     */
    protected int countQueryResult(Finder finder) {
        Query query = createQuery(finder.getRowCountJpql());
        finder.setParamsToQuery(query);
        Long totalCount = (Long)query.getSingleResult();

        return totalCount.intValue();
    }

    /**
     * 通过Finder获得分页数据
     * @author xxc
     * @return com.zhulong.framework.common.page.Pagination
     * @param finder
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @time 2019-1-8 17:18
     */
    protected Pagination find(Finder finder, int pageNo, int pageSize) {

        //统计总数
        int totalCount = countQueryResult(finder);

        return find(finder,pageNo,pageSize,totalCount);

    }

    /**
     * 通过Finder获得分页数据
     * @author xxc
     * @return com.zhulong.framework.common.page.Pagination
     * @param finder
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param totalCount 总页数
     * @time 2019-1-8 17:23
     */
    protected Pagination find(Finder finder, int pageNo, int pageSize, int totalCount) {

        Pagination p = new Pagination(pageNo, pageSize, totalCount);

        if (totalCount < 1) {
            p.setList(new ArrayList());
            return p;
        }

        Query query = createQuery(finder.getOrigJpql());
        finder.setParamsToQuery(query);

        //分页
        query.setFirstResult(p.getFirstResult());
        query.setMaxResults(pageSize);

        List list = query.getResultList();

        p.setList(list);

        return p;
    }

    /**
     * sql统计总数
     * @author xxc
     * @return int
     * @param query
     * @param params
     * @time 2018-9-25 20:08
     */
    protected int countSQLQuery(NativeQuery query , Map params){

        String sql=query.getQueryString();

        //替换 {a.*},{b.*},c.xxx 中的{a.*},{b.*} 为1
        sql = sql.replaceAll("(\\{[a-z]+\\.\\*\\},)+", "1,").replaceAll("(\\{[a-z]+\\.\\*\\})", "2");

        NativeQuery countSql = createNativeQuery("select count(*) from ("+sql+") countTable ");

        this.setParam(params,countSql);

        BigInteger totalCount = (BigInteger)countSql.getSingleResult();

        return totalCount.intValue();//总数
    }

    /**
     * sql分页查询
     * @author xxc
     * @return com.zhulong.framework.common.page.Pagination
     * @param params 参数
     * @param query  本地查询
     * @param pageNO  页码
     * @param pageSize 分页大小
     * @time 2018-9-25 20:08
     */
    protected Pagination sqlQueryPagination(Map<String,Object> params, NativeQuery query,Integer pageNO,Integer pageSize) {

        //统计
        int totalCount =countSQLQuery(query,params);

        //分页查询
        return this.sqlQueryPagination(params,query,pageNO,pageSize,totalCount);

    }

    /**
     * sql分页查询
     * @author xxc
     * @return com.zhulong.framework.common.page.Pagination
     * @param params 参数
     * @param query 本地查询
     * @param pageNo 页码
     * @param pageSize 分页大小
     * @param totalCount 总数
     * @time 2019-1-9 10:53
     */
    protected Pagination sqlQueryPagination(Map<String,Object> params, NativeQuery query,Integer pageNo,Integer pageSize,Integer totalCount) {

        Pagination p = new Pagination(pageNo, pageSize, totalCount);

        if (totalCount < 1) {
            p.setList(new ArrayList());
            return p;
        }

        //分页
        query.setFirstResult(pageNo);
        query.setMaxResults(pageSize);
        query.setProperties(params);

        List list = query.list();

        p.setList(list);

        return p;

    }



}
