package com.github.echo.mysql.binlog.driver.network.protocol.command;

import com.github.echo.mysql.binlog.driver.io.ByteArrayOutputStream;

import java.io.IOException;

public class QueryCommand implements Command {
    private String sql;

    public QueryCommand(String sql) {
        this.sql = sql;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        buffer.writeInteger(CommandType.QUERY.ordinal(), 1);
        buffer.writeString(this.sql);
        return buffer.toByteArray();
    }
}
