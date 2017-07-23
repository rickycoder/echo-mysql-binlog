package com.github.echo.mysql.binlog.driver.network.protocol;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public final class CapabilityFlags {

    public static final int LONG_PASSWORD = 1;
    public static final int FOUND_ROWS = 1 << 1;
    public static final int LONG_FLAG = 1 << 2;
    public static final int CONNECT_WITH_DB = 1 << 3;
    public static final int NO_SCHEMA = 1 << 4;
    public static final int COMPRESS = 1 << 5;
    public static final int ODBC = 1 << 6;
    public static final int LOCAL_FILES = 1 << 7;
    public static final int IGNORE_SPACE = 1 << 8;
    public static final int PROTOCOL_4_1 = 1 << 9;
    public static final int INTERACTIVE = 1 << 10;
    public static final int SSL = 1 << 11;
    public static final int IGNORE_SIGPIPE = 1 << 12;
    public static final int TRANSACTIONS = 1 << 13;
    public static final int RESERVED = 1 << 14;
    public static final int SECURE_CONNECTION = 1 << 15;
    public static final int MULTI_STATEMENTS = 1 << 16;
    public static final int MULTI_RESULTS = 1 << 17;
    public static final int PS_MULTI_RESULTS = 1 << 18;
    public static final int PLUGIN_AUTH = 1 << 19;
    public static final int SSL_VERIFY_SERVER_CERT = 1 << 30;
    public static final int REMEMBER_OPTIONS = 1 << 31;

    private CapabilityFlags() {
    }
}