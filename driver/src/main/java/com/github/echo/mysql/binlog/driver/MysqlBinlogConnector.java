package com.github.echo.mysql.binlog.driver;

import com.github.echo.mysql.binlog.driver.listener.EventListener;
import com.github.echo.mysql.binlog.driver.listener.LifecycleListener;
import com.github.echo.mysql.binlog.driver.manager.BinaryLogManager;
import com.github.echo.mysql.binlog.driver.manager.Connection;
import com.github.echo.mysql.binlog.driver.network.SocketFactory;
import com.github.echo.mysql.binlog.driver.network.protocol.PacketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public class MysqlBinlogConnector implements Connection, BinaryLogManager {

    private static final int MAX_PACKET_LENGTH = 16777215;

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final String host;
    private final int port;
    private final String username;
    private final String password;

    private boolean blocking = true;
    private long serverId = 65535;
    private volatile String binlogFileName;
    private volatile long binlogPosition = 4;
    private volatile long connectionId;

    private final Object gtidSetAccessLock = new Object();
    private boolean gtidSetFallbackToPurged;

    private EventDeserializer eventDeserializer = new EventDeserializer();

    private final List<EventListener> eventListeners = new LinkedList<EventListener>();
    private final List<LifecycleListener> lifecycleListeners = new LinkedList<LifecycleListener>();

    private SocketFactory socketFactory;

    private volatile PacketChannel channel;
    private volatile boolean connected;

    private ThreadFactory threadFactory;

    private boolean keepAlive = true;
    private long keepAliveInterval = TimeUnit.MINUTES.toMillis(1);

    private long heartbeatInterval;
    private volatile long eventLastSeen;

    private long connectTimeout = TimeUnit.SECONDS.toMillis(3);

    private volatile ExecutorService keepAliveThreadExecutor;

    private final Lock connectLock = new ReentrantLock();

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
            Callable cancelDisconnect = null;
            channel = openChannel();
            if (connectTimeout > 0 && !isKeepAliveThreadRunning()) {
                cancelDisconnect = scheduleDisconnectIn(connectTimeout -
                        (System.currentTimeMillis() - start));
            }
        } finally {
            connectLock.unlock();
        }
    }

    private PacketChannel openChannel() throws IOException {
        Socket socket = socketFactory != null ? socketFactory.createSocket() : new Socket();
        socket.connect(new InetSocketAddress(host, port), (int) connectTimeout);
        return new PacketChannel(socket);
    }

    boolean isKeepAliveThreadRunning() {
        return keepAliveThreadExecutor != null && !keepAliveThreadExecutor.isShutdown();
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
