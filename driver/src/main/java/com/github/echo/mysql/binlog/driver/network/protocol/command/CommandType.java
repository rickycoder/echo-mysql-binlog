package com.github.echo.mysql.binlog.driver.network.protocol.command;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public enum CommandType {
    SLEEP,
    QUIT,
    INIT_DB,
    QUERY,
    FIELD_LIST,
    CREATE_DB,
    DROP_DB,
    REFRESH,
    SHUTDOWN,
    STATISTICS,
    PROCESS_INFO,
    CONNECT,
    PROCESS_KILL,
    DEBUG,
    PING,
    TIME,
    DELAYED_INSERT,
    CHANGE_USER,
    BINLOG_DUMP,
    TABLE_DUMP,
    CONNECT_OUT,
    REGISTER_SLAVE,
    STMT_PREPARE,
    STMT_EXECUTE,
    STMT_SEND_LONG_DATA,
    STMT_CLOSE,
    STMT_RESET,
    SET_OPTION,
    STMT_FETCH,
    DAEMON,
    BINLOG_DUMP_GTID
}