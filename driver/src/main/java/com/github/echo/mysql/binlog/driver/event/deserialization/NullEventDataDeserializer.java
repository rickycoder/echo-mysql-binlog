package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.EventData;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

public class NullEventDataDeserializer implements EventDataDeserializer {
    @Override
    public EventData deserialize(ByteArrayInputStream inputStream) throws IOException {
        return null;
    }
}
