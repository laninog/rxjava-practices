package org.training.operators;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static io.reactivex.Flowable.range;
import static org.training.utils.Utils.hold;

public class FlowableTimerFlatMap {

    private static final Logger log = LoggerFactory.getLogger(FlowableTimerFlatMap.class);

    public static void main(String[] args) {

        log.info("Delay 1 second");
        Flowable<Integer> flowable = Flowable.timer(1, TimeUnit.SECONDS)
                .flatMap(i -> range(1, 10));

        log.info("Starts sending events");
        flowable.subscribe(
                l -> log.info("Index {}", l),
                ex -> log.error("Error {}", ex.getMessage())
        );

        hold(2000);

    }

}
