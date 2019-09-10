package org.training.threadpool;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;

public class StringsHandler {

    private ConnectableFlowable<String> processor;

    public StringsHandler(final StringsGenerator generator) {
        Flowable<String> flowable = Flowable.create(emitter ->
        {
            StringsListener listener = new StringsListener() {
                @Override
                public void onString(String event) {
                    emitter.onNext(event);
                }

                @Override
                public void onError(Throwable e) {
                    emitter.onError(e);
                }
            };

            generator.register(listener);

        }, BackpressureStrategy.BUFFER);
        processor = flowable.publish();
    }

    public ConnectableFlowable<String> processor() {
        return processor;
    }

    public void start() {
        processor.connect();
    }

}
