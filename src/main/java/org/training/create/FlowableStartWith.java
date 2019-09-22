package org.training.create;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlowableStartWith {

    private static final Logger log = LoggerFactory.getLogger(FlowableStartWith.class);

    public static void main(String[] args) {

        Flowable.just(1, 2, 3)
                .startWith(0)
                .subscribe(n -> log.info("{}", n));

    }

}
