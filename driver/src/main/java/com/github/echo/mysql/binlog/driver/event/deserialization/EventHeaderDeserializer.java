package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.EventHeader;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

/**
 * @param <T> event header this deserializer is responsible for
 */
public interface EventHeaderDeserializer<T extends EventHeader> {
    T deserialize(ByteArrayInputStream inputStream) throws IOException;
}
