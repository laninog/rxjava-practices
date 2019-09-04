package org.training.create;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlowableConcat {

    private static final Logger log = LoggerFactory.getLogger(FlowableConcat.class);

    public static void main(String[] args) {

        Flowable<String> flowableOne =
                Flowable.just("Alpha", "Beta", "Gamma", "Delta",
                        "Epsilon");

        Flowable<String> flowableTwo =
                Flowable.just("Zeta", "Eta", "Theta");

        // Keep flowable concat order
        Flowable.concat(flowableOne, flowableTwo)
                .subscribe(w -> log.info("Word {}", w));

    }

}
