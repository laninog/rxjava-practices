package org.training.create;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlowableCreateSimple {

    private static final Logger log = LoggerFactory.getLogger(FlowableCreateSimple.class);

    public static void main(String[] args) {

        Flowable<String> strings =
                Flowable.just("Alpha", "Beta", "Gamma", "Delta",
                        "Epsilon");

        strings.subscribe(log::info);

    }

}
