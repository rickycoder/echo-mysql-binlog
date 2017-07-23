package com.github.echo.mysql.binlog.driver.network;

import java.net.Socket;
import java.net.SocketException;

public interface SocketFactory {
    Socket createSocket() throws SocketException;
}
