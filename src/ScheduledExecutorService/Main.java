package ScheduledExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutor.schedule(new RunTask(), 5, TimeUnit.SECONDS);
        scheduledExecutor.shutdown();
    }

    static class RunTask extends Thread {
        @Override
        public void run() {
            System.out.println("OK!");
        }
    }
}
