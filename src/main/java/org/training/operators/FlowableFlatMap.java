package org.training.operators;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static io.reactivex.Flowable.empty;
import static io.reactivex.Flowable.just;

public class FlowableFlatMap {

    private static final Logger log = LoggerFactory.getLogger(FlowableFlatMap.class);

    public static void main(String[] args) {

        Flowable<Integer> flowable = Flowable.range(1, 10)
                .flatMap(i -> (i % 2 == 0)? just(i) : empty());

        flowable.subscribe(
                l -> log.info("Even numbers {}", l),
                ex -> log.error("Error {}", ex.getMessage())
        );

    }

}
