package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.EventHeaderV4;
import com.github.echo.mysql.binlog.driver.event.EventType;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

public class EventHeaderV4Deserializer implements EventHeaderDeserializer<EventHeaderV4> {
    private static final EventType[] EVENT_TYPES = EventType.values();

    private static EventType getEventType(int ordinal) throws IOException {
        if (ordinal >= EVENT_TYPES.length) {
            throw new IOException("Unknown event type " + ordinal);
        }
        return EVENT_TYPES[ordinal];
    }

    @Override
    public EventHeaderV4 deserialize(ByteArrayInputStream inputStream) throws IOException {
        EventHeaderV4 header = new EventHeaderV4();
        header.setTimestamp(inputStream.readLong(4) * 1000L);
        header.setEventType(getEventType(inputStream.readInteger(1)));
        header.setServerId(inputStream.readLong(4));
        header.setEventLength(inputStream.readLong(4));
        header.setNextPosition(inputStream.readLong(4));
        header.setFlags(inputStream.readInteger(2));
        return header;
    }
}
