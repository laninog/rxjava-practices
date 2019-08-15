package org.training.create;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.training.utils.Utils.hold;

public class FlowableInterval {

    private static final Logger log = LoggerFactory.getLogger(FlowableInterval.class);

    public static void main(String[] args) {

        Flowable<Long> seconds =
                Flowable.interval(1, TimeUnit.SECONDS);

        seconds.subscribe(t ->
                log.info("{}", t));

        hold(2000);
    }

}
