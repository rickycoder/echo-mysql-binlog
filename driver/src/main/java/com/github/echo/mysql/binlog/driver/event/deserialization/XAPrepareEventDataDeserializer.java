package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.XAPrepareEventData;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;

/**
 * https://github.com/mysql/mysql-server/blob/5.7/libbinlogevents/src/control_events.cpp#L590
 * <p>
 * onePhase : boolean, 1byte
 * formatID : int, 4byte
 * gtridLength : int, 4byte
 * bqualLength : int, 4byte
 * data : String, gtrid + bqual, (gtridLength + bqualLength)byte
 * <p>
 *
 * @author <a href="https://github.com/stevenczp">Steven Cheng</a>
 */

public class XAPrepareEventDataDeserializer implements EventDataDeserializer<XAPrepareEventData> {
    @Override
    public XAPrepareEventData deserialize(ByteArrayInputStream inputStream) throws IOException {
        XAPrepareEventData xaPrepareEventData = new XAPrepareEventData();
        xaPrepareEventData.setOnePhase(inputStream.read() != 0x00);
        xaPrepareEventData.setFormatID(inputStream.readInteger(4));
        xaPrepareEventData.setGtridLength(inputStream.readInteger(4));
        xaPrepareEventData.setBqualLength(inputStream.readInteger(4));
        xaPrepareEventData.setData(inputStream.read(
                xaPrepareEventData.getGtridLength() + xaPrepareEventData.getBqualLength()));
        return xaPrepareEventData;
    }
}
