package com.github.echo.mysql.binlog.driver.network.protocol.command;

import com.github.echo.mysql.binlog.driver.io.ByteArrayOutputStream;

import java.io.IOException;

public class PingCommand implements Command {
    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        buffer.writeInteger(CommandType.PING.ordinal(), 1);
        return buffer.toByteArray();
    }
}
