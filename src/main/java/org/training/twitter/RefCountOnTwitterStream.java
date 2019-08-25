package org.training.twitter;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.slf4j.LoggerFactory;
import twitter4j.*;

import static org.training.twitter.TwitterConfiguration.MSG_FORMAT;
import static org.training.twitter.TwitterConfiguration.getCredentials;

public class RefCountOnTwitterStream {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RefCountOnTwitterStream.class);

    static Flowable<Status> flowable = Flowable.create(subscriber -> {
        log.info("Connecting...");
        TwitterStream twitterStream = new TwitterStreamFactory(getCredentials()).getInstance();

        twitterStream.addListener(new StatusListener() {

            @Override
            public void onStatus(Status status) {
                subscriber.onNext(status);
            }

            @Override
            public void onException(Exception e) {
                subscriber.onError(e);
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}

            @Override
            public void onTrackLimitationNotice(int i) {}

            @Override
            public void onScrubGeo(long l, long l1) {}

            @Override
            public void onStallWarning(StallWarning stallWarning) {}

        });
        twitterStream.sample();

    }, BackpressureStrategy.BUFFER);

    public static void main(String[] args) {

        Flowable<Status> refCount = flowable.publish().refCount();

        log.info("Before subscribers");

        refCount.subscribe(
                s -> log.info(MSG_FORMAT, "One Status", s.getUser().getScreenName(), s.getText()));
        log.info("Subscriber One");

        refCount.subscribe(
                s -> log.info(MSG_FORMAT, "Two Status", s.getUser().getScreenName(), s.getText()));
        log.info("Subscriber Two");

    }

}
