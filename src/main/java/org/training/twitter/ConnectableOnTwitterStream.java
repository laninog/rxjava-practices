package org.training.twitter;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;
import org.slf4j.LoggerFactory;
import twitter4j.*;

import static org.training.twitter.TwitterConfiguration.MSG_FORMAT;

public class ConnectableOnTwitterStream {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ConnectableOnTwitterStream.class);

    private ConnectableFlowable<Status> connectable = Flowable.<Status>create(subscriber -> {
        log.info("Connecting...");
        TwitterStream twitterStream = new TwitterStreamFactory(TwitterConfiguration.getCredentials()).getInstance();

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

    }, BackpressureStrategy.BUFFER).publish();

    ConnectableFlowable<Status> observe() {
        return connectable;
    }

    public static void main(String[] args) {

        ConnectableOnTwitterStream m = new ConnectableOnTwitterStream();

        log.info("Before subscribers");

        m.observe().subscribe(
                    s -> log.info(MSG_FORMAT, "One Status", s.getUser().getScreenName(), s.getText()),
                    ex -> log.error("Error {}", ex)
                );
        log.info("Subscriber One");

        m.observe().subscribe(
                s -> log.info(MSG_FORMAT, "Two Status", s.getUser().getScreenName(), s.getText()),
                ex -> log.error("Error {}", ex)
        );
        log.info("Subscriber Two");

        log.info("Connect flowable");
        m.observe().connect();

    }

}
