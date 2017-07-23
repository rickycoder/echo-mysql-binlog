/*
 * Copyright 2015 Stanley Shyiko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.echo.mysql.binlog.driver.event.deserialization;

import com.github.echo.mysql.binlog.driver.event.IntVarEventData;
import com.github.echo.mysql.binlog.driver.io.ByteArrayInputStream;
import java.io.IOException;

public class IntVarEventDataDeserializer implements EventDataDeserializer<IntVarEventData> {
    @Override
    public IntVarEventData deserialize(ByteArrayInputStream inputStream) throws IOException {
        IntVarEventData event = new IntVarEventData();
        event.setType(inputStream.readInteger(1));
        event.setValue(inputStream.readLong(8));
        return event;
    }
}
