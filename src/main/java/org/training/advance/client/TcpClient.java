package org.training.advance.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {

    private static final Logger log = LoggerFactory.getLogger(TcpClient.class);

    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        int port = 8080;

        Socket socket = new Socket(hostname, port);

        TcpClientReader reader = new TcpClientReader(socket.getInputStream());
        TcpClientWriter writer = new TcpClientWriter(socket.getOutputStream());

        reader.start();
        writer.start();

        Scanner scanner = new Scanner(System.in);

        log.info("Write numbers: ");
        String text = scanner.next();
        while(!"a".equals(text)) {
            writer.add(text);
            text = scanner.next();
        }

        reader.stop();
        writer.stop();
        socket.close();

        log.info("Finish Client");

    }

}
