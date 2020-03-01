package org.training.advance.server;

import io.reactivex.netty.protocol.tcp.server.events.TcpServerEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ConnectionListener extends TcpServerEventListener {

    private static final Logger log = LoggerFactory.getLogger(ConnectionListener.class);

    @Override
    public void onNewClientConnected() {
        log.info("New connection");
    }

    @Override
    public void onConnectionHandlingStart(long duration, TimeUnit timeUnit) {
        log.info("Start connection {}, {}", duration, timeUnit);
    }

    @Override
    public void onConnectionCloseSuccess(long duration, TimeUnit timeUnit) {
        log.info("Connection close success {}, {}", duration, timeUnit);
    }

    @Override
    public void onConnectionCloseFailed(long duration, TimeUnit timeUnit, Throwable throwable) {
        log.error("Error", throwable);
    }

}
