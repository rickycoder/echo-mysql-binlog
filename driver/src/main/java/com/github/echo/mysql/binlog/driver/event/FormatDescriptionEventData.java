package com.github.echo.mysql.binlog.driver.event;

public class FormatDescriptionEventData implements EventData {
    private int binlogVersion;
    private String serverVersion;
    private int headerLength;

    public int getBinlogVersion() {
        return binlogVersion;
    }

    public void setBinlogVersion(int binlogVersion) {
        this.binlogVersion = binlogVersion;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public int getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(int headerLength) {
        this.headerLength = headerLength;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FormatDescriptionEventData");
        sb.append("{binlogVersion=").append(binlogVersion);
        sb.append(", serverVersion='").append(serverVersion).append('\'');
        sb.append(", headerLength=").append(headerLength);
        sb.append('}');
        return sb.toString();
    }
}
