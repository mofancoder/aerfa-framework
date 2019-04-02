package com.zhulong.framework.datasource.proxy;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Created by shanb on 2019-3-8.
 */
public class ZhuLongDataSourceProxy implements DataSource {

    private DataSource targetDataSource;

    private String dbType;

    public ZhuLongDataSourceProxy(DataSource dataSource, String dbType){
        this.targetDataSource = dataSource;
        this.dbType = dbType;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = targetDataSource.getConnection();
        return new ZhuLongConnectionProxy(conn,dbType);
    }

    public Connection getConnection(String username, String password) throws SQLException {
        Connection conn = targetDataSource.getConnection(username,password);
        return new ZhuLongConnectionProxy(conn,dbType);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return targetDataSource.unwrap(iface);
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return targetDataSource.isWrapperFor(iface);
    }

    public PrintWriter getLogWriter() throws SQLException {
        return targetDataSource.getLogWriter();
    }

    public void setLogWriter(PrintWriter out) throws SQLException {
        targetDataSource.setLogWriter(out);
    }

    public void setLoginTimeout(int seconds) throws SQLException {
        targetDataSource.setLoginTimeout(seconds);
    }

    public int getLoginTimeout() throws SQLException {
        return targetDataSource.getLoginTimeout();
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return targetDataSource.getParentLogger();
    }
}