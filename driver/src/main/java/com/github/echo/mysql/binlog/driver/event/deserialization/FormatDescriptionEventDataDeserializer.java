package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.FormatDescriptionEventData;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

public class FormatDescriptionEventDataDeserializer implements EventDataDeserializer<FormatDescriptionEventData> {
    @Override
    public FormatDescriptionEventData deserialize(ByteArrayInputStream inputStream) throws IOException {
        FormatDescriptionEventData eventData = new FormatDescriptionEventData();
        eventData.setBinlogVersion(inputStream.readInteger(2));
        eventData.setServerVersion(inputStream.readString(50).trim());
        inputStream.skip(4); // redundant, present in a header
        eventData.setHeaderLength(inputStream.readInteger(1));
        // lengths for all event types
        return eventData;
    }
}
