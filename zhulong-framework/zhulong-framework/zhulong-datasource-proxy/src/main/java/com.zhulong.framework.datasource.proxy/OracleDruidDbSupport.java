package com.zhulong.framework.datasource.proxy;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLInListExpr;
import com.alibaba.druid.sql.ast.expr.SQLInSubQueryExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleOutputVisitor;

/**
 * Created by shanb on 2019-3-13.
 */
public class OracleDruidDbSupport implements DruidDbSupport {

    @Override
    public String getTable(SQLExprTableSource sqlExprTableSource) {
        StringBuffer sb = new StringBuffer();
        OracleOutputVisitor visitor = new OracleOutputVisitor(sb) {
            @Override
            public boolean visit(SQLExprTableSource x) {
                printTableSourceExpr(x.getExpr());
                return false;
            }
        };
        visitor.visit(sqlExprTableSource);
        return sb.toString();
    }

    @Override
    public String getWhere(SQLExpr where) {
        StringBuffer sb = new StringBuffer();
        OracleOutputVisitor visitor = new OracleOutputVisitor(sb);
        if(where instanceof SQLInSubQueryExpr){
            visitor.visit((SQLInSubQueryExpr)where);
        } else if(where instanceof SQLInListExpr){
            visitor.visit((SQLInListExpr)where);
        }else {
            visitor.visit((SQLBinaryOpExpr)where);
        }
        return sb.toString();
    }
}