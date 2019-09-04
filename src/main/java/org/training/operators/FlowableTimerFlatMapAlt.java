package org.training.operators;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static io.reactivex.Flowable.range;
import static io.reactivex.Flowable.timer;
import static org.training.utils.Utils.hold;

public class FlowableTimerFlatMapAlt {

    private static final Logger log = LoggerFactory.getLogger(FlowableTimerFlatMapAlt.class);

    public static void main(String[] args) {

         Flowable<String> flowable =
                Flowable.just("Alpha", "Beta", "Gamma", "Delta","Epsilon")
                        .flatMap(word -> timer(word.length(), TimeUnit.SECONDS).map(x -> word));

        flowable.subscribe(
                l -> log.info("Timer for word {}", l),
                ex -> log.error("Error {}", ex.getMessage())
        );

        hold(10000);

    }

}
