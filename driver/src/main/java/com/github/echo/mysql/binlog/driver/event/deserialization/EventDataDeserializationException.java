package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.EventHeader;

import java.io.IOException;

public class EventDataDeserializationException extends IOException {
    private EventHeader eventHeader;

    public EventDataDeserializationException(EventHeader eventHeader, Throwable cause) {
        super("Failed to deserialize data of " + eventHeader, cause);
        this.eventHeader = eventHeader;
    }

    public EventHeader getEventHeader() {
        return eventHeader;
    }
}
