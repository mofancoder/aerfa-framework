package com.zhulong.framework.datasource.proxy;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;

/**
 * Created by shanb on 2019-3-13.
 */
public interface DruidDbSupport {

    String getTable(SQLExprTableSource sqlExprTableSource);

    String getWhere(SQLExpr where);
}
