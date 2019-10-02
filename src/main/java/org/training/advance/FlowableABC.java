package org.training.advance;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class FlowableABC {

    private static final Logger log = LoggerFactory.getLogger(FlowableABC.class);

    public static void main(String[] args) {

        List<String> words = Arrays.asList("alpha", "beta", "gamma", "delta", "epsilon");

        Flowable<String> result = Flowable.fromIterable(words)
                .flatMap(word -> Flowable.fromArray(word.split("")))
                .distinct()
                .sorted()
                .zipWith(Flowable.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        result.subscribe(l -> log.info("{}", l));

    }

}
