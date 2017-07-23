package com.github.echo.mysql.binlog.driver.network.protocol;

import com.github.echo.mysql.binlog.driver.network.io.BufferedSocketInputStream;
import com.github.echo.mysql.binlog.driver.network.io.ByteArrayInputStream;
import com.github.echo.mysql.binlog.driver.network.io.ByteArrayOutputStream;
import com.github.echo.mysql.binlog.driver.network.protocol.command.Command;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.Channel;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public class PacketChannel implements Channel {

    private Socket socket;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

    public PacketChannel(String hostname, int port) throws IOException {
        this(new Socket(hostname, port));
    }

    public PacketChannel(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = new ByteArrayInputStream(new BufferedSocketInputStream(socket.getInputStream()));
        this.outputStream = new ByteArrayOutputStream(socket.getOutputStream());
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    public byte[] read() throws IOException {
        int length = inputStream.readInteger(3);
        inputStream.skip(1); //sequence
        return inputStream.read(length);
    }

    public void write(Command command, int packetNumber) throws IOException {
        byte[] body = command.toByteArray();
        outputStream.writeInteger(body.length, 3); // packet length
        outputStream.writeInteger(packetNumber, 1);
        outputStream.write(body, 0, body.length);
        outputStream.flush();
    }

    public void write(Command command) throws IOException {
        write(command, 0);
    }

    @Override
    public boolean isOpen() {
        return !socket.isClosed();
    }

    @Override
    public void close() throws IOException {
        try {
            socket.shutdownInput();
        } catch (Exception e) {
            // ignore
        }
        try {
            socket.shutdownOutput();
        } catch (Exception e) {
            // ignore
        }
        socket.close();
    }
}
