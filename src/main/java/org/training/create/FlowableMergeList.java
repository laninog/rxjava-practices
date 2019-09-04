package org.training.create;

import com.google.common.collect.ImmutableList;
import io.reactivex.Flowable;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.training.utils.Utils.hold;

public class FlowableMergeList {

    private static final Logger log = LoggerFactory.getLogger(FlowableMergeList.class);

    public static void main(String[] args) {

        Flowable<String> source1 =
                Flowable.just("Alpha", "Beta").delay(10, TimeUnit.MILLISECONDS);
        Flowable<String> source2 =
                Flowable.just("Gamma", "Delta");
        Flowable<String> source3 =
                Flowable.just("Epsilon", "Zeta").delay(20, TimeUnit.MILLISECONDS);
        Flowable<String> source4 =
                Flowable.just("Eta", "Theta");
        Flowable<String> source5 =
                Flowable.just("Iota", "Kappa");

        List<Flowable<String>> sources =
                ImmutableList.of(source1, source2, source3, source4,
                        source5);

        // Keep event order emitted by flowables
        Flowable.merge(sources)
                .subscribe(log::info);

        hold(2000);

    }

}
