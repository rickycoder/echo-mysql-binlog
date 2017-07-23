package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.RotateEventData;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

public class RotateEventDataDeserializer implements EventDataDeserializer<RotateEventData> {
    @Override
    public RotateEventData deserialize(ByteArrayInputStream inputStream) throws IOException {
        RotateEventData eventData = new RotateEventData();
        eventData.setBinlogPosition(inputStream.readLong(8));
        eventData.setBinlogFilename(inputStream.readString(inputStream.available()));
        return eventData;
    }
}
