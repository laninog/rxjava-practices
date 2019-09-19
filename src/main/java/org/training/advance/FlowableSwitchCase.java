package org.training.advance;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FlowableSwitchCase {

    private static final Logger log = LoggerFactory.getLogger(FlowableSwitchCase.class);

    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 3, 4);

        Flowable<String> switchCase = Flowable.fromIterable(numbers)
                .flatMap(number -> {
                        switch (number) {
                            case 1:
                                return Flowable.just("Case 1 - ".concat(String.valueOf(number)));
                            case 2:
                                return Flowable.just("Case 2 - ".concat(String.valueOf(number)));
                            case 3:
                                return Flowable.just("Case 3 - ".concat(String.valueOf(number)));
                            default:
                                return Flowable.empty();
                        }
                    }
                );

        switchCase.subscribe(log::info);

    }

}
