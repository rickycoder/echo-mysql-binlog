package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.TableMapEventData;
import com.github.echo.mysql.binlog.driver.event.UpdateRowsEventData;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

public class UpdateRowsEventDataDeserializer extends AbstractRowsEventDataDeserializer<UpdateRowsEventData> {
    private boolean mayContainExtraInformation;

    public UpdateRowsEventDataDeserializer(Map<Long, TableMapEventData> tableMapEventByTableId) {
        super(tableMapEventByTableId);
    }

    public UpdateRowsEventDataDeserializer setMayContainExtraInformation(boolean mayContainExtraInformation) {
        this.mayContainExtraInformation = mayContainExtraInformation;
        return this;
    }

    @Override
    public UpdateRowsEventData deserialize(ByteArrayInputStream inputStream) throws IOException {
        UpdateRowsEventData eventData = new UpdateRowsEventData();
        eventData.setTableId(inputStream.readLong(6));
        inputStream.skip(2); // reserved
        if (mayContainExtraInformation) {
            int extraInfoLength = inputStream.readInteger(2);
            inputStream.skip(extraInfoLength - 2);
        }
        int numberOfColumns = inputStream.readPackedInteger();
        eventData.setIncludedColumnsBeforeUpdate(inputStream.readBitSet(numberOfColumns, true));
        eventData.setIncludedColumns(inputStream.readBitSet(numberOfColumns, true));
        eventData.setRows(deserializeRows(eventData, inputStream));
        return eventData;
    }

    private List<Map.Entry<Serializable[], Serializable[]>> deserializeRows(UpdateRowsEventData eventData,
                                                                            ByteArrayInputStream inputStream) throws IOException {
        long tableId = eventData.getTableId();
        BitSet includedColumnsBeforeUpdate = eventData.getIncludedColumnsBeforeUpdate(),
                includedColumns = eventData.getIncludedColumns();
        List<Map.Entry<Serializable[], Serializable[]>> rows =
                new ArrayList<Map.Entry<Serializable[], Serializable[]>>();
        while (inputStream.available() > 0) {
            rows.add(new AbstractMap.SimpleEntry<Serializable[], Serializable[]>(
                    deserializeRow(tableId, includedColumnsBeforeUpdate, inputStream),
                    deserializeRow(tableId, includedColumns, inputStream)
            ));
        }
        return rows;
    }
}
