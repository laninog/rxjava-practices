package org.training.create;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlowableRange {

    private static final Logger log = LoggerFactory.getLogger(FlowableRange.class);

    public static void main(String[] args) {

        Flowable<String> flowable = Flowable.range(65, 122)
                .map(r -> "[".concat("" + (char)r.intValue())
                        .concat("] ")
                        .concat(String.valueOf(r)));

        flowable.subscribe(log::info);

    }

}
