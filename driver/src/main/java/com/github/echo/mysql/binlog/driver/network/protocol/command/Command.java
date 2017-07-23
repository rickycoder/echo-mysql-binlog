package com.github.echo.mysql.binlog.driver.network.protocol.command;

import com.github.echo.mysql.binlog.driver.network.protocol.Packet;

import java.io.IOException;

public interface Command extends Packet {
    byte[] toByteArray() throws IOException;
}
