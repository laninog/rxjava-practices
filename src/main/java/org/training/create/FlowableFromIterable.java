package org.training.create;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class FlowableFromIterable {

    private static final Logger log = LoggerFactory.getLogger(FlowableFromIterable.class);

    public static void main(String[] args) {

        List<String> items =
                Arrays.asList("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        Flowable<String> flowable = Flowable.fromIterable(items);

        flowable.subscribe(log::info);

    }

}
