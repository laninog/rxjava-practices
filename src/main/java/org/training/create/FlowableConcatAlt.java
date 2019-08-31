package org.training.create;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.training.utils.Utils.hold;

public class FlowableConcatAlt {

    private static final Logger log = LoggerFactory.getLogger(FlowableConcatAlt.class);

    public static void main(String[] args) {

        Flowable<String> flowableOne =
                Flowable.interval(1, TimeUnit.SECONDS)
                        .take(4)
                        .map(l -> l + 1) // emit elapsed seconds
                        .map(l -> "One: " + l + " seconds");

        Flowable<String> flowableTwo =
                Flowable.interval(300, TimeUnit.MILLISECONDS)
                        .map(l -> (l + 1) * 300) // emit elapsed milliseconds
                        .map(l -> "Two: " + l + " milliseconds");

        // Keep flowable concat order
        Flowable.concat(flowableOne, flowableTwo)
                .subscribe(w -> log.info("{}", w));

        hold(10000);

    }

}
