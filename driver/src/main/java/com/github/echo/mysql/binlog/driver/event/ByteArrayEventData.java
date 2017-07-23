package com.github.echo.mysql.binlog.driver.event;

public class ByteArrayEventData implements EventData {
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ByteArrayEventData");
        sb.append("{dataLength=").append(data.length);
        sb.append('}');
        return sb.toString();
    }
}
