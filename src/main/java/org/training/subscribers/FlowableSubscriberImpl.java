package org.training.subscribers;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.training.utils.Utils.hold;

public class FlowableSubscriberImpl {

    private static final Logger log = LoggerFactory.getLogger(FlowableSubscriberImpl.class);

    public static void main(String[] args) {

        Flowable<String> flowable = Flowable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        FlowableSubscriber<String> subscriber = new FlowableSubscriber<String>() {

            Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                subscription = s;
                log.info("Start!!");
                subscription.request(1);
            }
            @Override
            public void onNext(String s) {
                log.info("{}", s);
                subscription.request(1);
            }
            @Override
            public void onError(Throwable t) {
                log.error(t.getMessage());
            }
            @Override
            public void onComplete() {
                log.info("Completed!!");
            }
        };

        flowable.subscribe(subscriber);

        hold(2000);

    }

}
