package com.github.echo.mysql.binlog.driver;

import com.github.echo.mysql.binlog.driver.listener.LifecycleListener;
import com.github.echo.mysql.binlog.driver.manager.BinaryLogManager;
import com.github.echo.mysql.binlog.driver.manager.Connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public class MysqlBinlogConnector implements Connection, BinaryLogManager {

    private final String host;
    private final int port;
    private final String username;
    private final String password;

    private boolean blocking = true;
    private long serverId = 65535;
    private volatile String binlogFileName;
    private volatile long binlogPosition = 4;
    private volatile long connectionId;

    private final Lock connectLock = new ReentrantLock();
    private final List<LifecycleListener> lifecycleListeners = new LinkedList<LifecycleListener>();

    public MysqlBinlogConnector(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Override
    public void connect(long timeout, TimeUnit timeUnit) throws IOException {
        if (!connectLock.tryLock()) {
            throw new IllegalStateException("MySQL binlog server is already connected");
        }
        try {

        } finally {
            connectLock.unlock();
        }
    }



    @Override
    public void disConnect() throws IOException {

    }

    @Override
    public String getBinlogFileName() {
        return binlogFileName;
    }

    @Override
    public void setBinlogFileName() {
        this.binlogFileName = binlogFileName;
    }

    @Override
    public long getBinlogPosition() {
        return binlogPosition;
    }

    @Override
    public void setBinlogPosition(long binlogPosition) {
        this.binlogPosition = binlogPosition;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public long getConnectionId() {
        return connectionId;
    }

}
