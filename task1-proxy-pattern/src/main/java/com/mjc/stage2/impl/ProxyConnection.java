package com.mjc.stage2.impl;


import com.mjc.stage2.Connection;

public class ProxyConnection implements Connection {
    private RealConnection realConnection;

    public ProxyConnection(RealConnection realConnection) {
        this.realConnection = realConnection;
    }

    public void reallyClose() {
        realConnection.close();
    }
    public void close() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        if (!connection.isClosed()) {
            connectionPool.releaseConnection(connection);
        }

    }
    public boolean isClosed() {
        if (realConnection.isClosed()) {
            return isClosed();
        } else {
            return false;
        }
    }
}
