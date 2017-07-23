package com.github.echo.mysql.binlog.driver.network.protocol;

import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

public class GreetingPacket implements Packet {
    private int protocolVersion;
    private String serverVersion;
    private long threadId;
    private String scramble;
    private int serverCapabilities;
    private int serverCollation;
    private int serverStatus;
    private String pluginProvidedData;

    public GreetingPacket(byte[] bytes) throws IOException {
        ByteArrayInputStream buffer = new ByteArrayInputStream(bytes);
        this.protocolVersion = buffer.readInteger(1);
        this.serverVersion = buffer.readZeroTerminatedString();
        this.threadId = buffer.readLong(4);
        String scramblePrefix = buffer.readZeroTerminatedString();
        this.serverCapabilities = buffer.readInteger(2);
        this.serverCollation = buffer.readInteger(1);
        this.serverStatus = buffer.readInteger(2);
        buffer.skip(13); // reserved
        this.scramble = scramblePrefix + buffer.readZeroTerminatedString();
        if (buffer.available() > 0) {
            this.pluginProvidedData = buffer.readZeroTerminatedString();
        }
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public String getScramble() {
        return scramble;
    }

    public long getThreadId() {
        return threadId;
    }

    public int getServerStatus() {
        return serverStatus;
    }

    public int getServerCapabilities() {
        return serverCapabilities;
    }

    public String getPluginProvidedData() {
        return pluginProvidedData;
    }

    public int getServerCollation() {
        return serverCollation;
    }
}
