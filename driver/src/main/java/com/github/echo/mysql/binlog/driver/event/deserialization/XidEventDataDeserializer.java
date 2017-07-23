package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.XidEventData;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

public class XidEventDataDeserializer implements EventDataDeserializer<XidEventData> {
    @Override
    public XidEventData deserialize(ByteArrayInputStream inputStream) throws IOException {
        XidEventData eventData = new XidEventData();
        eventData.setXid(inputStream.readLong(8));
        return eventData;
    }
}
