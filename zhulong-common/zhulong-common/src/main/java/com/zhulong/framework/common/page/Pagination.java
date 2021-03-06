package com.zhulong.framework.common.page;

import java.util.List;

/**
 * 列表分页。包含list属性。
 * 
 * @author dengfl
 * 
 */
@SuppressWarnings("serial")
public class Pagination<T> extends BasePage implements java.io.Serializable,
		Paginable {

	public Pagination() {
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 */
	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 * @param list
	 *            分页内容
	 */
	public Pagination(int pageNo, int pageSize, int totalCount, List<T> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	/**
	 * 第一条数据位置
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return pageNo  * pageSize;
	}

	/**
	 * 当前页的数据
	 */
	private List<T> list;

	/**
	 * 获得分页内容
	 * 
	 * @return
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 设置分页内容
	 * 
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	public void setList(List<T> list) {
		this.list = list;
	}

	public <S> Pagination<S> of(List<S> list){
		return new Pagination<S>(pageNo,pageSize,totalCount,list);
	}
}
