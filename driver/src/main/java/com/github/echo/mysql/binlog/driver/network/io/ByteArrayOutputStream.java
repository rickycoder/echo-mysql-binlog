package com.github.echo.mysql.binlog.driver.network.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public class ByteArrayOutputStream extends OutputStream {

    private OutputStream outputStream;

    public ByteArrayOutputStream() {
        this(new java.io.ByteArrayOutputStream());
    }

    public ByteArrayOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeInteger(int value, int length) throws IOException {
        for (int i = 0; i < length; i++) {
            write(0x000000FF & (value >>> (i << 3)));
        }
    }

    public void writeLong(long value, int length) throws IOException {
        for (int i = 0; i < length; i++) {
            write((int) (0x00000000000000FF & (value >>> (i << 3))));
        }
    }

    public void writeString(String value) throws IOException {
        write(value.getBytes());
    }

    public void writeZeroTerminatedString(String value) throws IOException {
        write(value.getBytes());
        write(0);
    }

    public void write(int b) throws IOException {
        outputStream.write(b);
    }

    public byte[] toByteArray() {
        if (outputStream instanceof java.io.ByteArrayOutputStream) {
            return ((java.io.ByteArrayOutputStream) outputStream).toByteArray();
        }
        return new byte[0];
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }
}
