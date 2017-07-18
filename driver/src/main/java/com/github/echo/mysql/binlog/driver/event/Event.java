package com.github.echo.mysql.binlog.driver.event;

import java.io.Serializable;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public class Event implements Serializable {

    private EventHeader header;
    private EventData data;

    public Event(EventHeader header, EventData data) {
        this.header = header;
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    public <T extends EventHeader> T getHeader() {
        return (T) header;
    }

    @SuppressWarnings("unchecked")
    public <T extends EventData> T getData() {
        return (T) data;
    }

}
