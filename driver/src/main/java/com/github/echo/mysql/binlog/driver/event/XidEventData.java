package com.github.echo.mysql.binlog.driver.event;


public class XidEventData implements EventData {
    private long xid;

    public long getXid() {
        return xid;
    }

    public void setXid(long xid) {
        this.xid = xid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("XidEventData");
        sb.append("{xid=").append(xid);
        sb.append('}');
        return sb.toString();
    }
}
