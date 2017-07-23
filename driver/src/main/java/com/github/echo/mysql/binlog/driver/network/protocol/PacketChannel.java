package com.github.echo.mysql.binlog.driver.network.protocol;

import com.github.echo.mysql.binlog.driver.io.BufferedSocketInputStream;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;
import com.github.echo.mysql.binlog.driver.io.ByteArrayOutputStream;
import com.github.echo.mysql.binlog.driver.network.IdentityVerificationException;
import com.github.echo.mysql.binlog.driver.network.SSLSocketFactory;
import com.github.echo.mysql.binlog.driver.network.protocol.command.Command;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.Socket;
import java.nio.channels.Channel;

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
        // though it has no effect in case of default (underlying) output stream (SocketOutputStream),
        // it may be necessary in case of non-default one
        outputStream.flush();
    }

    public void write(Command command) throws IOException {
        write(command, 0);
    }

    public void upgradeToSSL(SSLSocketFactory sslSocketFactory, HostnameVerifier hostnameVerifier) throws IOException {
        SSLSocket sslSocket = sslSocketFactory.createSocket(this.socket);
        sslSocket.startHandshake();
        socket = sslSocket;
        inputStream = new ByteArrayInputStream(sslSocket.getInputStream());
        outputStream = new ByteArrayOutputStream(sslSocket.getOutputStream());
        if (hostnameVerifier != null && !hostnameVerifier.verify(sslSocket.getInetAddress().getHostName(),
                sslSocket.getSession())) {
            throw new IdentityVerificationException("\"" + sslSocket.getInetAddress().getHostName() +
                    "\" identity was not confirmed");
        }
    }

    @Override
    public boolean isOpen() {
        return !socket.isClosed();
    }

    @Override
    public void close() throws IOException {
        try {
            socket.shutdownInput(); // for socketInputStream.setEOF(true)
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
