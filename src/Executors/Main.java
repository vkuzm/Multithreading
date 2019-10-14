package Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService fixedExecutor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 20 ; i++) {
            fixedExecutor.submit(new RandomThread());
            Thread.sleep(10);
        }

        fixedExecutor.shutdown();
    }

    static class RandomThread implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
