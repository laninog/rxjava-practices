package org.training.operators;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.training.utils.Utils.hold;

public class FlowableTakeInterval {

    private static final Logger log = LoggerFactory.getLogger(FlowableTakeInterval.class);

    public static void main(String[] args) {

        Flowable<Long> flowable =
                Flowable.interval(500, TimeUnit.MILLISECONDS)
                    .take(2, TimeUnit.SECONDS);

        flowable.subscribe(l -> log.info("{}", l));

        hold(6000);

    }

}
