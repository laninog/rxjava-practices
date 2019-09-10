package org.training.threadpool;

import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.training.utils.Utils.hold;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        String[] strings = {"IBM", "NMR", "BAC", "AAA", "MFT"};
        StringsGenerator generator = new StringsGenerator(strings);

        StringsHandler handler = new StringsHandler(generator);

        // Subscriber One
        Disposable one = handler.processor().subscribe(str ->
                log.info("Subscriber One - string {}", str)
        );

        // Subscriber Two
        Disposable two = handler.processor().take(40).subscribe(str ->
                log.info("Subscriber Two - string {}", str)
        );

        // Start generator
        handler.start();

        hold(20000);

        // Stop generator
        generator.terminate();

        hold(1000);

        System.exit(0);

    }

}
