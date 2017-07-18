package com.github.echo.mysql.binlog.driver.manager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public interface Connection {

    void connect(long timeout, TimeUnit timeUnit) throws IOException;
    void disConnect() throws IOException;
}
