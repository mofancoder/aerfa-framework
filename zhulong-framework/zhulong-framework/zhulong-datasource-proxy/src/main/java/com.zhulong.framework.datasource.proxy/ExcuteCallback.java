package com.zhulong.framework.datasource.proxy;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * 执行的回调。
 * Created by shanb on 2019-3-11.
 */
public interface ExcuteCallback<T> {

    T excute(Statement statement,String... args) throws SQLException;
}
