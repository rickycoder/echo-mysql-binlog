package com.github.echo.mysql.binlog.driver.listener;

import com.github.echo.mysql.binlog.driver.MysqlBinlogConnector;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public interface LifecycleListener {

    void onConnect(MysqlBinlogConnector connector);

    void onCommunicationFailure(MysqlBinlogConnector connector, Exception ex);

    void onEventDeserializationFailure(MysqlBinlogConnector connector, Exception ex);

    void onDisconnect(MysqlBinlogConnector connector);
}
