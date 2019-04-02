package com.zhulong.framework.datasource.proxy;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.util.StringUtils;

import java.util.List;

/**
 * 通过druid的sql解析来解析数据。
 * Created by shanb on 2019-3-11.
 */
public class DruidSqlPase implements SqlParse {

    private String sql;

    private String dbType;

    private SQLDeleteStatement sqlDeleteStatement;

    private DruidDbSupport druidDbSupport;

    public DruidSqlPase(String sql,String dbType){
        this.sql = sql;
        this.dbType = dbType;
        //判断sql是否为删除
        List<SQLStatement> asts = SQLUtils.parseStatements(sql, dbType);
        if (asts == null || asts.size() != 1) {
            throw new UnsupportedOperationException("Unsupported SQL: " + sql);
        }
        SQLStatement ast = asts.get(0);
        if(ast instanceof SQLDeleteStatement) {
            this.sqlDeleteStatement = (SQLDeleteStatement) ast;
        }
        this.druidDbSupport = DruidDbSupportFactory.getDbSupport(dbType);
    }

    @Override
    public boolean isDeleteSql() {
        return sqlDeleteStatement != null;
    }

    @Override
    public String getTable() {
        return this.druidDbSupport.getTable((SQLExprTableSource)sqlDeleteStatement.getTableSource());
    }

    @Override
    public String getTableAlias() {
        if(!StringUtils.isEmpty(sqlDeleteStatement.getTableSource().getAlias())){
            return sqlDeleteStatement.getTableSource().getAlias();
        }else{
            return "";
        }
    }

    @Override
    public String getWhere() {

        SQLExpr where = sqlDeleteStatement.getWhere();
        if (where == null) {
            return "";
        }
        return this.druidDbSupport.getWhere(where);
    }

    @Override
    public String getFormatSql(List<Object> params) {
        return SQLUtils.format(sql,dbType,params);
    }

}