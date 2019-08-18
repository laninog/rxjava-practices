package org.training.create;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import static org.training.utils.Utils.hold;

public class FlowableFromIterableAlt {

    private static final Logger log = LoggerFactory.getLogger(FlowableFromIterableAlt.class);

    public static void main(String[] args) {

        Flowable<Integer> flowable = Flowable.fromIterable(new EvenNumberIterable());

        flowable.subscribe(n -> log.info("{}", n));

    }

    static class EvenNumberIterable implements Iterable<Integer> {

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private Integer currentNumber = Integer.valueOf(0);

                @Override
                public boolean hasNext() {
                    return true;
                }

                @Override
                public Integer next() {
                    hold(500);
                    return currentNumber += 2;
                }
            };
        }

    }

}
