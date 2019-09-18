package org.training.operators;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.training.utils.Utils.hold;

public class FlowableZipAsyncError {

    private static final Logger log = LoggerFactory.getLogger(FlowableZipAsyncError.class);

    public static void main(String[] args) {

        Flowable<String> intervalA =
                Flowable.interval(18, TimeUnit.MILLISECONDS).map(i -> "A".concat(i.toString()));

        Flowable<String> intervalB =
                Flowable.interval(10, TimeUnit.MILLISECONDS).map(i -> "B".concat(i.toString()));

        Flowable<String> zip = Flowable.zip(intervalA, intervalB,
                (a, b) -> a.concat(" : ").concat(b));

        zip.subscribe(ab -> log.info("{}", ab),
                ex -> log.error("Error : {}", ex.getMessage()));

        hold(2000);

    }

}
