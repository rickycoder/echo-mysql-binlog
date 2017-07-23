package com.github.echo.mysql.binlog.driver.event;

public class PreviousGtidSetEventData implements EventData {
    private final String gtidSet;

    public PreviousGtidSetEventData(String gtidSet) {
        this.gtidSet = gtidSet;
    }

    public String getGtidSet() {
        return gtidSet;
    }

    @Override
    public String toString() {
        return "PreviousGtidSetEventData {gtidSet='" + gtidSet + "'}";
    }
}
