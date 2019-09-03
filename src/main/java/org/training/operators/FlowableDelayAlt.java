package org.training.operators;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static io.reactivex.Flowable.timer;
import static org.training.utils.Utils.hold;

public class FlowableDelayAlt {

    private static final Logger log = LoggerFactory.getLogger(FlowableDelayAlt.class);

    public static void main(String[] args) {

        Flowable<String> flowable =
                Flowable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Pi")
                    .delay(word -> timer(word.length(), TimeUnit.SECONDS));

        flowable.subscribe(
                w -> log.info("{} seconds for word {}", w.length(), w),
                ex -> log.error("Error {}", ex.getMessage())
        );

        hold(8000);

    }

}
