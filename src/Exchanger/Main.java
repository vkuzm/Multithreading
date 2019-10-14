package Exchanger;

import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread thread1 = new Thread1(exchanger);
        thread1.start();

        Thread2 thread2 = new Thread2(exchanger);
        thread2.join();
        thread2.start();
    }

    static class Thread1 extends Thread {
        Exchanger<String> exchanger;

        Thread1(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                String message = exchanger.exchange("Hi Thread 2! I'm a big fan of you!");
                System.out.println("Thread 1 received message: " + message);
                sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread2 extends Thread {
        Exchanger<String> exchanger;

        Thread2(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                String message = exchanger.exchange("Hi Thread 1! It's cool to hear such nice words from you.");
                System.out.println("Thread 2 received message: " + message);
                sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
