package org.training.operators;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlowableDistinctUntilChangeAlt {

    private static final Logger log = LoggerFactory.getLogger(FlowableDistinctUntilChangeAlt.class);

    public static void main(String[] args) {

        Flowable<String> flowable =
                Flowable.just("Alpha", "Beta", "Zeta", "Eta", "Ate", "Gamma",
                        "Delta")
                        .distinctUntilChanged(String::length);

        flowable.subscribe(log::info);

    }

}
