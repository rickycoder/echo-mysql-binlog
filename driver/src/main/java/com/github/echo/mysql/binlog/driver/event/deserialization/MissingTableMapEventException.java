package com.github.echo.mysql.binlog.driver.event.deserialization;

import java.io.IOException;

public class MissingTableMapEventException extends IOException {
    public MissingTableMapEventException(String message) {
        super(message);
    }
}
