package com.github.echo.mysql.binlog.driver.network.protocol;

public interface Packet {
    // https://dev.mysql.com/doc/internals/en/sending-more-than-16mbyte.html
    int MAX_LENGTH = 16777215;
}
