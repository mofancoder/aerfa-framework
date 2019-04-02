package com.zhulong.framework.datasource.proxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 删除的数据信息
 * Created by shanb on 2019-3-11.
 */
public class DeleteRecordInfo {

    public static final String DELETE_RECORD_TABLE_NAME = "DELETE_RECORD";

    private String table;

    //格式化的，已替换了参数的sql
    private String formatSql;

    private List<Map<String,Object>> records;

    public void setTable(String table) {
        this.table = table;
    }

    public void setRecords(List<Map<String, Object>> records) {
        this.records = records;
    }

    public void setFormatSql(String formatSql){
        this.formatSql = formatSql;
    }

    public void flush2Db(Connection conn) throws SQLException {
        String sql = "insert into "+DELETE_RECORD_TABLE_NAME+"(GUID,TABLE_NAME,CONTENT,FORMAT_SQL,CREATE_TIME) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUID.randomUUID().toString());
        preparedStatement.setString(2,this.table);
        Clob clob = conn.createClob();
        clob.setString(1,content2json());
        preparedStatement.setClob(3,clob);
        preparedStatement.setString(4,formatSql);
        preparedStatement.setLong(5,System.currentTimeMillis());
        preparedStatement.execute();
    }

    private String content2json(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(records);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}