package com.github.echo.mysql.binlog.driver.network.protocol.command;

import com.github.echo.mysql.binlog.driver.io.ByteArrayOutputStream;

import java.io.IOException;

public class DumpBinaryLogCommand implements Command {
    private long serverId;
    private String binlogFilename;
    private long binlogPosition;

    public DumpBinaryLogCommand(long serverId, String binlogFilename, long binlogPosition) {
        this.serverId = serverId;
        this.binlogFilename = binlogFilename;
        this.binlogPosition = binlogPosition;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        buffer.writeInteger(CommandType.BINLOG_DUMP.ordinal(), 1);
        buffer.writeLong(this.binlogPosition, 4);
        buffer.writeInteger(0, 2); // flag
        buffer.writeLong(this.serverId, 4);
        buffer.writeString(this.binlogFilename);
        return buffer.toByteArray();
    }
}
