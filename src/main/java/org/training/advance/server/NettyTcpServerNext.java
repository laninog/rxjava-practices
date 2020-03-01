package org.training.advance.server;

import io.netty.handler.codec.LineBasedFrameDecoder;
import io.reactivex.netty.protocol.tcp.server.TcpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.StandardCharsets.UTF_8;

public class NettyTcpServerNext {

    private static final Logger log = LoggerFactory.getLogger(NettyTcpServerNext.class);

    private static final BigDecimal RATE = new BigDecimal("1.06448");

    public static void main(final String[] args) {
        TcpServer<MessageCurrency, String> server = TcpServer
            .newServer(8080)
            .<MessageCurrency, String>pipelineConfigurator(pipeline -> {
                pipeline.addLast(new LineBasedFrameDecoder(1024));
                pipeline.addLast(new ClientRequestDecoder(UTF_8));
            });

        server.getEventPublisher().subscribe(new ConnectionListener());

        server.start(connection -> {
            Observable<String> output = connection
                    .getInput()
                    .map(i -> i.getQuantity())
                    .flatMap(NettyTcpServerNext::eurToUsd);
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
