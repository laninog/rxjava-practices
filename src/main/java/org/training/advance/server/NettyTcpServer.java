package org.training.advance.server;

import io.netty.handler.codec.LineBasedFrameDecoder;
import io.reactivex.netty.protocol.tcp.server.TcpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.training.advance.CleanDecoder;
import rx.Observable;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.StandardCharsets.UTF_8;

public class NettyTcpServer {

    private static final Logger log = LoggerFactory.getLogger(NettyTcpServer.class);

    private static final BigDecimal RATE = new BigDecimal("1.06448");

    public static void main(final String[] args) {
        TcpServer
                .newServer(8080)
                .<String, String>pipelineConfigurator(pipeline -> {
                    pipeline.addLast(new LineBasedFrameDecoder(1024));
                    pipeline.addLast(new CleanDecoder(UTF_8));
                })
                .start(connection -> {
                    Observable<String> output = connection
                            .getInput()
                            .map(BigDecimal::new)
                            .flatMap(NettyTcpServer::eurToUsd);
                    return connection.writeAndFlushOnEach(output);
                })
                .awaitShutdown();
    }

    private static Observable<String> eurToUsd(BigDecimal eur) {
        log.info("EurToUsd {}", eur.doubleValue());
        return Observable
                .just(eur.multiply(RATE))
                .map(amount -> eur + " EUR is " + amount + " USD\r\n")
                .delay(200, TimeUnit.MILLISECONDS);
    }

}
