package org.training.twitter;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.Configuration;

import static org.training.twitter.TwitterConfiguration.MSG_FORMAT;
import static org.training.twitter.TwitterConfiguration.getCredentials;

public class SubjectOnTwitterStream {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FlowableOnTwitterStream.class);

    private final PublishSubject<Status> subject = PublishSubject.create();

    SubjectOnTwitterStream(final Configuration configuration) {
        log.info("Connecting...");
        TwitterStream twitterStream = new TwitterStreamFactory(configuration).getInstance();

        twitterStream.addListener(new StatusListener() {

            @Override
            public void onStatus(Status status) {
                subject.onNext(status);
            }

            @Override
            public void onException(Exception e) {
                subject.onError(e);
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
    }

    Flowable<Status> observe() {
        return subject.toFlowable(BackpressureStrategy.BUFFER);
    }

    public static void main(String[] args) {

        Flowable<Status> flowable = new SubjectOnTwitterStream(getCredentials()).observe();

        log.info("Before subscribers");

        flowable.subscribe(
                s -> log.info(MSG_FORMAT, "One Status", s.getUser().getScreenName(), s.getText()),
                ex -> log.error("Error {}", ex)
        );
        log.info("Subscriber One");

        flowable.subscribe(
                s -> log.info(MSG_FORMAT, "Two Status", s.getUser().getScreenName(), s.getText()),
                ex -> log.error("Error {}", ex)
        );
        log.info("Subscriber Two");

    }

}
