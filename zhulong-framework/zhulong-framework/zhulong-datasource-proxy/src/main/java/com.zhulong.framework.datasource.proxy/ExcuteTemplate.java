package com.zhulong.framework.datasource.proxy;

import java.sql.*;
import java.util.*;

/**
 * 执行模板，所有的statement都通过此模板进行执行。
 * 执行的时候，会解析sql语句，拦截删除的sql语句，进行处理。
 * Created by shanb on 2019-3-11.
 */
public class ExcuteTemplate {
    /**
     * 先从Statement中去获取sql,如果为null，则从第一个参数表示sql。
     */
    public static <T> T excute(ZhuLongStatementProxy statementProxy,ExcuteCallback<T> callback,String... args) throws SQLException {
        String sql = statementProxy.getSql();
        if(sql==null){
            if(args!=null) {
                sql = args[0];
            }else{
                throw new RuntimeException("can not get the sql");
            }
        }

        SqlParse sqlParse = new DruidSqlPase(sql,statementProxy.getConnectionProxy().getDbType());
        //是删除语句，并且不是记录删除语句的表
        if(sqlParse.isDeleteSql() &&!DeleteRecordInfo.DELETE_RECORD_TABLE_NAME.equalsIgnoreCase(sqlParse.getTable())) {
            String table = sqlParse.getTable();
            String whereCondition = sqlParse.getWhere();
            //组装查询语句
            StringBuilder sb = new StringBuilder("SELECT * FROM "+table+" "+sqlParse.getTableAlias()+" where "+whereCondition);
            Connection targetConn = statementProxy.getConnectionProxy().getTargetConnection();
            PreparedStatement preparedStatement = targetConn.prepareStatement(sb.toString());
            if(statementProxy instanceof ZhuLongPreparedStatementProxy){
                //设置参数
                ZhuLongPreparedStatementProxy zlPreStatementProxy = (ZhuLongPreparedStatementProxy)statementProxy;
                int i = 1;
                for (Object obj : zlPreStatementProxy.getParameters()) {
                    preparedStatement.setObject(i,obj);
                    i++;
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            List<String> columnNameList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                columnNameList.add(metaData.getColumnName(i+1));
            }

            List<Map<String,Object>> dataMapList = new ArrayList<>();
            while(resultSet.next()){
                Map<String,Object> itemMap = new HashMap<>();
                for (int i = 0; i < columnNameList.size(); i++) {
                    itemMap.put(columnNameList.get(i),resultSet.getObject(columnNameList.get(i)));
                }
                dataMapList.add(itemMap);
            }
            if(!dataMapList.isEmpty()) {
                DeleteRecordInfo deleteRecordInfo = new DeleteRecordInfo();
                deleteRecordInfo.setTable(table);
                deleteRecordInfo.setRecords(dataMapList);
                List<Object> paramList;
                if(statementProxy instanceof ZhuLongPreparedStatementProxy){
                    paramList = Arrays.asList(((ZhuLongPreparedStatementProxy) statementProxy).getParameters());
                }else{
                    paramList = new ArrayList<>();
                }
                deleteRecordInfo.setFormatSql(sqlParse.getFormatSql(paramList));
                //插入到数据库中
                if (targetConn.getAutoCommit()) {
                    //如果为自动提交，则需要设置成非自动提交，保证此sql的执行与插入到删除记录的sql在同一个事务中。
                    try {
                        targetConn.setAutoCommit(false);
                        deleteRecordInfo.flush2Db(targetConn);
                        T result = callback.excute(statementProxy.getTargetStatement(), args);
                        targetConn.commit();
                        return result;
                    } finally {
                        targetConn.setAutoCommit(true);
                    }
                } else {
                    deleteRecordInfo.flush2Db(targetConn);
                }
            }
        }
        return callback.excute(statementProxy.getTargetStatement(),args);
    }
}