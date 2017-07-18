package com.github.echo.mysql.binlog.driver.manager;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public interface BinaryLogManager {
    String getBinlogFileName();
    void setBinlogFileName();
    long getBinlogPosition();
    void setBinlogPosition(long binlogPosition);
}
