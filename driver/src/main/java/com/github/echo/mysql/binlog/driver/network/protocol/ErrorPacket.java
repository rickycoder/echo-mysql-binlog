package com.github.echo.mysql.binlog.driver.network.protocol;

import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

public class ErrorPacket implements Packet {
    private int errorCode;
    private String sqlState;
    private String errorMessage;

    public ErrorPacket(byte[] bytes) throws IOException {
        ByteArrayInputStream buffer = new ByteArrayInputStream(bytes);
        this.errorCode = buffer.readInteger(2);
        if (buffer.peek() == '#') {
            buffer.skip(1); // marker of the SQL State
            this.sqlState = buffer.readString(5);
        }
        this.errorMessage = buffer.readString(buffer.available());
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getSqlState() {
        return sqlState;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
