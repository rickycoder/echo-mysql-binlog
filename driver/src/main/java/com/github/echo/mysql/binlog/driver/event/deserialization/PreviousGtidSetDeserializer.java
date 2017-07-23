package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.PreviousGtidSetEventData;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

import static java.lang.String.format;

/**
 * @author <a href="https://github.com/jolivares">Juan Olivares</a>
 */

public class PreviousGtidSetDeserializer implements EventDataDeserializer<PreviousGtidSetEventData> {
    private static String byteArrayToHex(byte[] a, int offset, int len) {
        StringBuilder sb = new StringBuilder();
        for (int idx = offset; idx < (offset + len) && idx < a.length; idx++) {
            sb.append(format("%02x", a[idx] & 0xff));
        }
        return sb.toString();
    }

    private static String join(String[] values, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(values[i]);
        }
        return sb.toString();
    }

    @Override
    public PreviousGtidSetEventData deserialize(
            ByteArrayInputStream inputStream) throws IOException {
        int nUuids = inputStream.readInteger(8);
        String[] gtids = new String[nUuids];
        for (int i = 0; i < nUuids; i++) {
            String uuid = formatUUID(inputStream.read(16));
            int nIntervals = inputStream.readInteger(8);
            String[] intervals = new String[nIntervals];
            for (int j = 0; j < nIntervals; j++) {
                long start = inputStream.readLong(8);
                long end = inputStream.readLong(8);
                intervals[j] = start + "-" + (end - 1);
            }
            gtids[i] = format("%s:%s", uuid, join(intervals, ":"));
        }
        return new PreviousGtidSetEventData(join(gtids, ","));
    }

    private String formatUUID(byte[] bytes) {
        return format("%s-%s-%s-%s-%s",
                byteArrayToHex(bytes, 0, 4),
                byteArrayToHex(bytes, 4, 2),
                byteArrayToHex(bytes, 6, 2),
                byteArrayToHex(bytes, 8, 2),
                byteArrayToHex(bytes, 10, 6));
    }
}
