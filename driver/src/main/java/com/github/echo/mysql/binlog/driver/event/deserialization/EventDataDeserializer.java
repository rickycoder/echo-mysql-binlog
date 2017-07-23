package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.EventData;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

/**
 * @param <T> event data this deserializer is responsible for
 */
public interface EventDataDeserializer<T extends EventData> {
    T deserialize(ByteArrayInputStream inputStream) throws IOException;
}
