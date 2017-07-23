package com.github.echo.mysql.binlog.driver.network.protocol;

import com.github.echo.mysql.binlog.driver.network.io.ByteArrayInputStream;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public class ResultSetRowPacket implements Packet {

    private String[] values;

    public ResultSetRowPacket(byte[] bytes) throws IOException {
        ByteArrayInputStream buffer = new ByteArrayInputStream(bytes);
        List<String> values = new LinkedList<String>();
        while (buffer.available() > 0) {
            values.add(buffer.readLengthEncodedString());
        }
        this.values = values.toArray(new String[values.size()]);
    }

    public String[] getValues() {
        return values;
    }

    public String getValue(int index) {
        return values[index];
    }

    public int size() {
        return values.length;
    }

}