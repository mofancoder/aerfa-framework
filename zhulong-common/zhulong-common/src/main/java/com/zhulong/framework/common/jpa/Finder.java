package com.zhulong.framework.common.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * jpql语句查询
 * @author xxc
 * @time 2019-1-8 15:48
 */
public class Finder {

	private StringBuilder jpqlBuilder; //jpql语句字符串

	private List<String> params;  //参数名
	private List<Object> values;  //参数值

	private int firstResult = 0;  // 分页页码

	private int maxResults = 0;   // 分页每页数量

	public static final String ROW_COUNT = "select count(*) ";
	public static final String FROM = "from";
	public static final String JPQL_FETCH = "fetch";
	public static final String ORDER_BY = "order";

	protected Finder() {
		jpqlBuilder = new StringBuilder();
	}

	protected Finder(String jpql) {
		jpqlBuilder = new StringBuilder(jpql);
	}

	public static Finder create() {
		return new Finder();
	}

	public static Finder create(String jpql) {
		return new Finder(jpql);
	}

	public Finder append(String jpql) {
		jpqlBuilder.append(jpql);
		return this;
	}


	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	private List<String> getParams() {
		if (params == null) {
			params = new ArrayList<String>();
		}
		return params;
	}

	private List<Object> getValues() {
		if (values == null) {
			values = new ArrayList<Object>();
		}
		return values;
	}

	/**
	 * 获得原始jpql语句
	 * @author xxc
	 * @return java.lang.String
	 * @param
	 * @time 2019-1-8 15:59
	 */
	public String getOrigJpql() {
		return jpqlBuilder.toString();
	}

	/**
	 * 获取统计的jpql语句
	 * @author xxc
	 * @return java.lang.String
	 * @param
	 * @time 2019-1-8 17:09
	 */
	public String getRowCountJpql() {
		String jpql = jpqlBuilder.toString();

		int fromIndex = jpql.toLowerCase().indexOf(FROM);

		jpql = jpql.substring(fromIndex);
		String rowCountJpql = jpql.replace(JPQL_FETCH, "");

		int index = rowCountJpql.indexOf(ORDER_BY);
		if (index > 0) {
			rowCountJpql = rowCountJpql.substring(0, index);
		}
		return ROW_COUNT  + rowCountJpql;
	}

	/**
	 * 设置参数
	 * @author xxc
	 * @return com.zhulong.framework.common.jpa.Finder
	 * @param param
	 * @param value
	 * @time 2019-1-8 15:56
	 */
	public Finder setParam(String param, Object value) {
		getParams().add(param);
		getValues().add(value);
		return this;
	}

	/**
	 * 设置参数map集合
	 * @author xxc
	 * @return com.zhulong.framework.common.jpa.Finder
	 * @param paramMap
	 * @time 2019-1-8 15:56
	 */
	public Finder setParams(Map<String, Object> paramMap) {
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			setParam(entry.getKey(), entry.getValue());
		}
		return this;
	}

	/**
	 * 将finder中的参数设置到query中
	 * @author xxc
	 * @return javax.persistence.Query
	 * @param query
	 * @time 2019-1-8 16:14
	 */
	protected Query setParamsToQuery(Query query) {
		if (params != null) {

			for (int i = 0; i < params.size(); i++) {
				query.setParameter(params.get(i), values.get(i));
			}
		}
		return query;
	}

	/**
	 * 创建查询
	 * @author xxc
	 * @return javax.persistence.Query
	 * @param em
	 * @time 2019-1-8 16:04
	 */
	public Query createQuery(EntityManager em) {
		Query query = setParamsToQuery(em.createQuery(getOrigJpql()));
		if (getFirstResult() > 0) {
			query.setFirstResult(getFirstResult());
		}
		if (getMaxResults() > 0) {
			query.setMaxResults(getMaxResults());
		}
		return query;
	}

}
