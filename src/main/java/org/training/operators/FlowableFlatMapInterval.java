package org.training.operators;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static io.reactivex.Flowable.*;
import static org.training.utils.Utils.hold;

public class FlowableFlatMapInterval {

    private static final Logger log = LoggerFactory.getLogger(FlowableFlatMapInterval.class);

    public static void main(String[] args) {

        Flowable<Long> flowable = Flowable.range(1, 5)
                .filter(i -> i % 2 == 0)
                .flatMap(i -> interval(i, TimeUnit.SECONDS));

        flowable.subscribe(
                l -> log.info("Even time intervals {}", l),
                ex -> log.error("Error {}", ex.getMessage())
        );

        hold(30000);

    }

}
