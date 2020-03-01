package org.training.advance.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;

public class TcpClientReader implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(TcpClientReader.class);

    private final InputStream inputStream;

    private boolean read = true;

    public TcpClientReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void start() {
        new Thread(this).start();
    }

    public void stop() {
        this.read = false;
    }

    @Override
    public void run() {
        log.info("Reader waiting for data...");

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            while(read) {
                String line;

                while ((line = reader.readLine()) != null)
                    log.info("Line {}", line);
            }
        } catch (SocketException skex) {
            log.error("Socket error");
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }

        log.info("Reader stopped");
    }

}
