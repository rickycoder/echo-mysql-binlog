package com.github.echo.mysql.binlog.driver.network;


public class AuthenticationException extends ServerException {
    public AuthenticationException(String message, int errorCode, String sqlState) {
        super(message, errorCode, sqlState);
    }

    public AuthenticationException(String message) {
        super(message, 0, "HY000");
    }
}
