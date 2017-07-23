package com.github.echo.mysql.binlog.driver.network;

import java.net.Socket;
import java.net.SocketException;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public interface SocketFactory {

    Socket createSocket() throws SocketException;

}