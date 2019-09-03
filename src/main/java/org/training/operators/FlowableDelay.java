package org.training.operators;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static io.reactivex.Flowable.empty;
import static io.reactivex.Flowable.just;
import static org.training.utils.Utils.hold;

public class FlowableDelay {

    private static final Logger log = LoggerFactory.getLogger(FlowableDelay.class);

    public static void main(String[] args) {

        log.info("Delay 1 second");
        Flowable<Integer> flowable = Flowable.range(1, 10)
                .delay(1, TimeUnit.SECONDS);

        log.info("Starts sending events");
        flowable.subscribe(
                l -> log.info("Index {}", l),
                ex -> log.error("Error {}", ex.getMessage())
        );

        hold(2000);

    }

}
