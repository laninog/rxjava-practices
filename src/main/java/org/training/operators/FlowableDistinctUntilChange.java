package org.training.operators;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlowableDistinctUntilChange {

    private static final Logger log = LoggerFactory.getLogger(FlowableDistinctUntilChange.class);

    public static void main(String[] args) {

        Flowable<Integer> flowable =
                Flowable.just(1, 1, 1, 2, 2, 3, 3, 2, 1, 1)
                        .distinctUntilChanged();

        flowable.subscribe(l -> log.info("{}", l));

    }

}
