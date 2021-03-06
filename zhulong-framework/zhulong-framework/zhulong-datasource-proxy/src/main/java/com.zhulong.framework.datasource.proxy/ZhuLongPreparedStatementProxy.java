package com.zhulong.framework.datasource.proxy;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

/**
 * Created by shanb on 2019-3-8.
 */
public class ZhuLongPreparedStatementProxy extends ZhuLongStatementProxy implements PreparedStatement  {

    private PreparedStatement preparedStatement;

    private ZhuLongConnectionProxy connection;

    private String sql;

    protected Object[] parameters;

    private void initParameterHolder() throws SQLException {
        int paramCount = preparedStatement.getParameterMetaData().getParameterCount();
        this.parameters = new Object[paramCount];
        for (int i = 0; i < paramCount; i++) {
            parameters[i] = null;
        }
    }

    public ZhuLongPreparedStatementProxy(ZhuLongConnectionProxy connection, PreparedStatement preparedStatement) throws SQLException {
        this(connection,preparedStatement,null);
        initParameterHolder();
    }

    public ZhuLongPreparedStatementProxy(ZhuLongConnectionProxy connection, PreparedStatement preparedStatement, String sql) throws SQLException {
        super(connection,preparedStatement,sql);
        this.preparedStatement = preparedStatement;
        this.connection = connection;
        this.sql = sql;
        initParameterHolder();
    }

    private void setParametersItem(int i,Object o){
        this.parameters[--i] = o;
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return ExcuteTemplate.excute(this,(s,args)->((PreparedStatement)s).executeQuery());
    }

    @Override
    public int executeUpdate() throws SQLException {
        return ExcuteTemplate.excute(this,(s,args)->((PreparedStatement)s).executeUpdate());
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        preparedStatement.setNull(parameterIndex,sqlType);
        setParametersItem(parameterIndex,null);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        preparedStatement.setBoolean(parameterIndex,x);
        setParametersItem(parameterIndex, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        preparedStatement.setByte(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        preparedStatement.setShort(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
       preparedStatement.setInt(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        preparedStatement.setLong(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        preparedStatement.setFloat(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        preparedStatement.setDouble(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        preparedStatement.setBigDecimal(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        preparedStatement.setString(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        preparedStatement.setBytes(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        preparedStatement.setDate(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        preparedStatement.setTime(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        preparedStatement.setTimestamp(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        preparedStatement.setAsciiStream(parameterIndex,x,length);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        preparedStatement.setUnicodeStream(parameterIndex,x,length);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        preparedStatement.setBinaryStream(parameterIndex,x,length);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void clearParameters() throws SQLException {
        preparedStatement.clearParameters();
        initParameterHolder();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        preparedStatement.setObject(parameterIndex,x,targetSqlType);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        preparedStatement.setObject(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public boolean execute() throws SQLException {
        return ExcuteTemplate.excute(this,(s,args)->((PreparedStatement)s).execute());
    }

    @Override
    public void addBatch() throws SQLException {
        preparedStatement.addBatch();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        preparedStatement.setCharacterStream(parameterIndex,reader,length);
        setParametersItem(parameterIndex,reader);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        preparedStatement.setRef(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        preparedStatement.setBlob(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        preparedStatement.setClob(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        preparedStatement.setArray(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return preparedStatement.getMetaData();
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        preparedStatement.setDate(parameterIndex,x,cal);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        preparedStatement.setTime(parameterIndex,x,cal);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        preparedStatement.setTimestamp(parameterIndex,x,cal);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        preparedStatement.setNull(parameterIndex,sqlType,typeName);
        setParametersItem(parameterIndex,null);
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        preparedStatement.setURL(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return preparedStatement.getParameterMetaData();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        preparedStatement.setRowId(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        preparedStatement.setNString(parameterIndex,value);
        setParametersItem(parameterIndex,value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        preparedStatement.setNCharacterStream(parameterIndex,value,length);
        setParametersItem(parameterIndex,value);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        preparedStatement.setNClob(parameterIndex,value);
        setParametersItem(parameterIndex,value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        preparedStatement.setClob(parameterIndex,reader,length);
        setParametersItem(parameterIndex,reader);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        preparedStatement.setBlob(parameterIndex,inputStream,length);
        setParametersItem(parameterIndex,inputStream);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        preparedStatement.setNClob(parameterIndex,reader,length);
        setParametersItem(parameterIndex,reader);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        preparedStatement.setSQLXML(parameterIndex,xmlObject);
        setParametersItem(parameterIndex,xmlObject);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        preparedStatement.setObject(parameterIndex,x,targetSqlType,scaleOrLength);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        preparedStatement.setAsciiStream(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        preparedStatement.setBinaryStream(parameterIndex,x,length);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        preparedStatement.setCharacterStream(parameterIndex,reader,length);
        setParametersItem(parameterIndex,reader);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        preparedStatement.setAsciiStream(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        preparedStatement.setBinaryStream(parameterIndex,x);
        setParametersItem(parameterIndex,x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        preparedStatement.setCharacterStream(parameterIndex,reader);
        setParametersItem(parameterIndex,reader);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        preparedStatement.setNCharacterStream(parameterIndex,value);
        setParametersItem(parameterIndex,value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        preparedStatement.setClob(parameterIndex,reader);
        setParametersItem(parameterIndex,reader);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        preparedStatement.setBlob(parameterIndex,inputStream);
        setParametersItem(parameterIndex,inputStream);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        preparedStatement.setNClob(parameterIndex,reader);
        setParametersItem(parameterIndex,reader);
    }

    public Object[] getParameters(){
        return this.parameters;
    }
}