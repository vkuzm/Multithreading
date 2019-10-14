package BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    private static final BlockingQueue<String> messages = new LinkedBlockingDeque<>();

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            Producer producer = new Producer("Number #" + i);
            producer.start();
        }

        Consumer consumer = new Consumer();
        consumer.start();
    }

    static class Producer extends Thread {
        private final String text;

        private Producer(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            messages.offer(text);
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            try {
                while (!messages.isEmpty()) {
                    sleep(1000);
                    System.out.println(messages.take());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
