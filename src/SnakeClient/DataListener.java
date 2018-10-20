package SnakeClient;

import com.sun.org.apache.bcel.internal.generic.InstructionConstants;

import java.awt.*;
import java.util.Scanner;

public class DataListener implements Runnable {

    Client client = null;
    private Scanner scanner = null;

    public DataListener(Client client, Scanner scanner) {
        this.scanner = scanner;
        this.client = client;
    }

    @Override
    public void run() {
        while (scanner.hasNext()) {
            String rawData = scanner.nextLine();
            System.out.println(rawData);
            client.getCommand(rawData);
        }
    }
}
