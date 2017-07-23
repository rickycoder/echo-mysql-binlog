/*
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
package com.github.echo.mysql.binlog.driver.jmx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface BinaryLogClientMXBean {
    String getBinlogFilename();
    void setBinlogFilename(String binlogFilename);
    long getBinlogPosition();
    void setBinlogPosition(long binlogPosition);
    void connect(long timeoutInMilliseconds) throws IOException, TimeoutException;
    boolean isConnected();
    void disconnect() throws IOException;
}
