package org.training.operators;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlowableElementAt {

    private static final Logger log = LoggerFactory.getLogger(FlowableElementAt.class);

    public static void main(String[] args) {

        Maybe<String> flowable =
                Flowable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma",
                        "Delta")
                        .elementAt(3);

        flowable.subscribe(l -> log.info("{}", l));

    }

}
