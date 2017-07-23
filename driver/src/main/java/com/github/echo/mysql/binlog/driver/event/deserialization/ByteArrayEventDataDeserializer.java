package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.ByteArrayEventData;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

public class ByteArrayEventDataDeserializer implements EventDataDeserializer<ByteArrayEventData> {
    @Override
    public ByteArrayEventData deserialize(ByteArrayInputStream inputStream) throws IOException {
        ByteArrayEventData eventData = new ByteArrayEventData();
        eventData.setData(inputStream.read(inputStream.available()));
        return eventData;
    }
}
