package org.training.create;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlowableEmpty {

    private static final Logger log = LoggerFactory.getLogger(FlowableEmpty.class);

    public static void main(String[] args) {

        Flowable<String> flowable = Flowable.empty();

        flowable.subscribe(log::info,
                Throwable::printStackTrace,
                () -> log.info("Done!"));

    }

}
