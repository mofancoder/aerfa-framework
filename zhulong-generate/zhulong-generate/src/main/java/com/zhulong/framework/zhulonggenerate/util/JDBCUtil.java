package com.zhulong.framework.zhulonggenerate.util;

import com.zhulong.framework.zhulonggenerate.boot.ConstantInterface;

import java.sql.*;

/**
 * jdbc连接工具
 * @author xxc
 * @time 2019-2-28 9:22
 */
public final class JDBCUtil {

    private static final String URL = ConstantInterface.url;
    private static final String USER = ConstantInterface.userName;
    private static final String PASSWORD = ConstantInterface.password;
    private static final String DRIVER = ConstantInterface.driver;

    private JDBCUtil(){}

    private static volatile Connection connection=null;

    public static Connection getConnection() throws Exception{

        if(connection==null){

            synchronized (JDBCUtil.class){

                if(connection==null){
                    Class.forName(DRIVER);
                    connection= DriverManager.getConnection(URL, USER, PASSWORD);
                }

            }
        }
        return connection;
    }

    /**
     * 关闭连接
     * @author xxc
     * @time 2019-3-1 17:10
     */
    public static void closed() throws Exception {

        connection.close();
    }


    /**
     * 获取结果集
     * @author xxc
     * @time 2019-3-1 17:09
     */
    public static ResultSet getResult(String tableName) throws Exception {

        Connection conn = getConnection();
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet resultSet = databaseMetaData.getColumns(null,"%", tableName,"%");

        return resultSet;

    }

    /**
     * 执行sql
     * @author xxc
     * @time 2019-3-1 17:07
     */
    public static void execute(String sql) throws Exception {

        Connection conn = getConnection();

        Statement stmt = conn.createStatement();

        stmt.execute(sql);

        stmt.close();
        conn.close();


    }



}
