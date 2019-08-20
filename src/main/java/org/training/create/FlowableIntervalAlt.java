package org.training.create;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.training.utils.Utils.hold;

public class FlowableIntervalAlt {

    private static final Logger log = LoggerFactory.getLogger(FlowableIntervalAlt.class);

    public static void main(String[] args) {

        Flowable<Long> seconds =
                Flowable.interval(1, TimeUnit.SECONDS);

        seconds.subscribe(t ->
                log.info("One {}", t));

        hold(5000);

        seconds.subscribe(t ->
                log.info("Two {}", t));

        hold(6000);

    }

}
