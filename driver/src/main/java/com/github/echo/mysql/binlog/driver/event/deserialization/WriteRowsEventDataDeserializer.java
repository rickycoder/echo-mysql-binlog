package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.TableMapEventData;
import com.github.echo.mysql.binlog.driver.event.WriteRowsEventData;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.Serializable;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WriteRowsEventDataDeserializer extends AbstractRowsEventDataDeserializer<WriteRowsEventData> {
    private boolean mayContainExtraInformation;

    public WriteRowsEventDataDeserializer(Map<Long, TableMapEventData> tableMapEventByTableId) {
        super(tableMapEventByTableId);
    }

    public WriteRowsEventDataDeserializer setMayContainExtraInformation(boolean mayContainExtraInformation) {
        this.mayContainExtraInformation = mayContainExtraInformation;
        return this;
    }

    @Override
    public WriteRowsEventData deserialize(ByteArrayInputStream inputStream) throws IOException {
        WriteRowsEventData eventData = new WriteRowsEventData();
        eventData.setTableId(inputStream.readLong(6));
        inputStream.skip(2); // reserved
        if (mayContainExtraInformation) {
            int extraInfoLength = inputStream.readInteger(2);
            inputStream.skip(extraInfoLength - 2);
        }
        int numberOfColumns = inputStream.readPackedInteger();
        eventData.setIncludedColumns(inputStream.readBitSet(numberOfColumns, true));
        eventData.setRows(deserializeRows(eventData.getTableId(), eventData.getIncludedColumns(), inputStream));
        return eventData;
    }

    private List<Serializable[]> deserializeRows(long tableId, BitSet includedColumns, ByteArrayInputStream inputStream)
            throws IOException {
        List<Serializable[]> result = new LinkedList<Serializable[]>();
        while (inputStream.available() > 0) {
            result.add(deserializeRow(tableId, includedColumns, inputStream));
        }
        return result;
    }
}
