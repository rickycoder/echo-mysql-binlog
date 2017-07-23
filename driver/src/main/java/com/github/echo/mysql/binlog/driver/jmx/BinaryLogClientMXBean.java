package com.github.echo.mysql.binlog.driver.jmx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface BinaryLogClientMXBean {
    String getBinlogFilename();

    void setBinlogFilename(String binlogFilename);

    long getBinlogPosition();

    void setBinlogPosition(long binlogPosition);

    void connect(long timeoutInMilliseconds) throws IOException, TimeoutException;

    boolean isConnected();

    void disconnect() throws IOException;
}
