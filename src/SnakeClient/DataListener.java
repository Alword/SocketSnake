package SnakeClient;

import java.awt.*;
import java.util.Scanner;

public class DataListener implements Runnable {

    private Scanner scanner = null;

    public DataListener(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (scanner.hasNext()) {
            String rawData = scanner.nextLine();
            System.out.println(rawData);
            Client.ParseCommand(rawData);
        }
    }
}
