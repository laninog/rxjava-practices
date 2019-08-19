package org.training.subscribers;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.training.utils.Utils.hold;

public class FlowableSubscriberSimple {

    private static final Logger log = LoggerFactory.getLogger(FlowableSubscriberSimple.class);

    public static void main(String[] args) {

        Flowable<String> flowable = Flowable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        flowable.map(String::length)
                .filter(i -> i >= 5)
                .subscribe(
                        length -> log.info("{}", length),
                        Throwable::printStackTrace,
                        () -> log.info("Done!")
                );

    }

}
