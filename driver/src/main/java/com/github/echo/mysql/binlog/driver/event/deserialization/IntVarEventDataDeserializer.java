package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.IntVarEventData;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

public class IntVarEventDataDeserializer implements EventDataDeserializer<IntVarEventData> {
    @Override
    public IntVarEventData deserialize(ByteArrayInputStream inputStream) throws IOException {
        IntVarEventData event = new IntVarEventData();
        event.setType(inputStream.readInteger(1));
        event.setValue(inputStream.readLong(8));
        return event;
    }
}
