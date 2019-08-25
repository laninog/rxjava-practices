package org.training.twitter;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.Configuration;

import static org.training.twitter.TwitterConfiguration.MSG_FORMAT;
import static org.training.twitter.TwitterConfiguration.getCredentials;

public class FlowableOnTwitterStream {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FlowableOnTwitterStream.class);

    public Flowable<Status> observe(final Configuration configuration) {
        return Flowable.create(subscriber -> {
            log.info("Connecting...");
            TwitterStream twitterStream = new TwitterStreamFactory(configuration).getInstance();

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
    }

    public static void main(String[] args) {

        FlowableOnTwitterStream m = new FlowableOnTwitterStream();

        log.info("Before subscribers");

        m.observe(getCredentials())
                .subscribe(
                    s -> log.info(MSG_FORMAT, "One Status", s.getUser().getScreenName(), s.getText()),
                    ex -> log.error("Error {}", ex)
                );
        log.info("Subscriber One");

        m.observe(getCredentials())
                .subscribe(
                        s -> log.info(MSG_FORMAT, "Two Status", s.getUser().getScreenName(), s.getText()),
                        ex -> log.error("Error {}", ex)
                );
        log.info("Subscriber Two");

    }

}
