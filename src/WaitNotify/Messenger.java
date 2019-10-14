package WaitNotify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Messenger {
    private final static Queue<String> messages = new LinkedList<>();

    public static void main(String[] args) {
        new Sender().start();
        new Receiver().start();
    }

    static class Sender extends Thread {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Write a message:");

            while (true) {
                synchronized (messages) {
                    messages.offer(scanner.nextLine());
                    messages.notify();
                }

                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Receiver extends Thread {
        @Override
        public void run() {
            while (messages.isEmpty()) {
                synchronized (messages) {
                    try {
                        messages.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Receiver got: " + messages.poll());
                }
            }
        }
    }
}
