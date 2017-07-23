package com.github.echo.mysql.binlog.driver.network.protocol.command;

import com.github.echo.mysql.binlog.driver.network.protocol.Packet;

import java.io.IOException;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public interface Command extends Packet {

    byte[] toByteArray() throws IOException;

}