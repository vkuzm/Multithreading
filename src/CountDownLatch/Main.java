package CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(3);

        new RunThread(latch).start();
        new RunThread(latch).start();
        new RunThread(latch).start();

        latch.await();
        Thread.sleep(300);
        System.out.println("Main thread continues after running 3 threads");
    }

    static class RunThread extends Thread {
        private final CountDownLatch latch;

        RunThread(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                sleep(300);
                latch.countDown();
                System.out.println(currentThread().getName() + " finished");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


