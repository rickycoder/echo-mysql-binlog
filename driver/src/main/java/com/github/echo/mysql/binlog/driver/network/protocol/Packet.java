package com.github.echo.mysql.binlog.driver.network.protocol;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public interface Packet {

    /**
     * https://dev.mysql.com/doc/internals/en/sending-more-than-16mbyte.html
     */
    int MAX_LENGTH = 16777215;
}
