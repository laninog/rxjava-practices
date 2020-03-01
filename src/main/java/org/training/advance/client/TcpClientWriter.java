package org.training.advance.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TcpClientWriter implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(TcpClientWriter.class);

    private final OutputStream outputStream;

    private boolean write = true;
    private List<String> messages = new ArrayList<>();

    public TcpClientWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void start() {
        new Thread(this).start();
    }

    public void stop() {
        this.write = false;
        synchronized (messages) {
            messages.notifyAll();
        }
    }

    public void add(String message) {
        synchronized (messages) {
            messages.add(message);
            messages.notifyAll();
        }
    }

    @Override
    public void run() {
        log.info("Writer waiting for data...");

        PrintWriter writer = new PrintWriter(outputStream);

        try {
            while(write) {
                synchronized (messages) {
                    if (messages.size() > 0) {
                        writer.println(messages.remove(0));
                        writer.flush();
                    } else {
                        messages.wait();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        log.info("Writer stopped");
    }

}
