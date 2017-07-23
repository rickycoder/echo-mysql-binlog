package com.github.echo.mysql.binlog.driver.jmx;

public interface BinaryLogClientStatisticsMXBean {
    String getLastEvent();

    long getSecondsSinceLastEvent();

    long getSecondsBehindMaster();

    long getTotalNumberOfEventsSeen();

    long getTotalBytesReceived();

    long getNumberOfSkippedEvents();

    long getNumberOfDisconnects();

    void reset();
}
