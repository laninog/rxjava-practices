package org.training.threadpool;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class StringListener {

    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private final int ID;

    public StringListener() {
        ID = COUNTER.getAndIncrement();
    }

    protected abstract void onString(String w);
    protected abstract void onError(Throwable thr);

    @Override
    public String toString() {
        return String.format("Listener ID:%d:%s", ID, super.toString());
    }

}
