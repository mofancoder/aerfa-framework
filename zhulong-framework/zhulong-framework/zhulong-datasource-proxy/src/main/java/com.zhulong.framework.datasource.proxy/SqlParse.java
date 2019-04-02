package com.zhulong.framework.datasource.proxy;

import java.util.List;

/**
 * sql解析，用于解析删除语句，获取表名称和执行条件，用于组装查询语句。
 * Created by shanb on 2019-3-11.
 */
public interface SqlParse {

    boolean isDeleteSql();

    String getTable();

    String getTableAlias();

    String getWhere();

    /**
     * 替换参数，格式化sql语句。
     */
    String getFormatSql(List<Object> params);
}
