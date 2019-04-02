package com.zhulong.framework.datasource.proxy;

import com.alibaba.druid.util.JdbcConstants;

/**
 * Created by shanb on 2019-3-13.
 */
public class DruidDbSupportFactory {

    public static DruidDbSupport getDbSupport(String dbType){
        if(JdbcConstants.MYSQL.equalsIgnoreCase(dbType)){
            return new MysqlDruidDbSupport();
        }else if(JdbcConstants.ORACLE.equalsIgnoreCase(dbType)){
            return new OracleDruidDbSupport();
        }else{
            throw new IllegalArgumentException("not support the dbType sql parse,the dbType:"+dbType);
        }
    }
}