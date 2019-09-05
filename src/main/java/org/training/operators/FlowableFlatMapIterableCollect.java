package org.training.operators;

import com.google.common.collect.ImmutableList;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FlowableFlatMapIterableCollect {

    private static final Logger log = LoggerFactory.getLogger(FlowableFlatMapIterableCollect.class);

    public static void main(String[] args) {

        List<String> words = ImmutableList.of("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        List<Integer> wordsLength = new ArrayList<>();

        Flowable.fromArray(words)
                .flatMapIterable(x -> x)
                .collect(() -> wordsLength, (l, p) -> l.add(p.length()))
                .subscribe(
                        l -> log.info("{}", l)
                );

    }

}
