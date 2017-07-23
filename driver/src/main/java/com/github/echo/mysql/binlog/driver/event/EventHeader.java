package com.github.echo.mysql.binlog.driver.event;

import java.io.Serializable;

public interface EventHeader extends Serializable {
    long getTimestamp();

    EventType getEventType();

    long getServerId();

    long getHeaderLength();

    long getDataLength();
}
