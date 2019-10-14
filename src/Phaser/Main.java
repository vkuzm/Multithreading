package Phaser;

import java.util.concurrent.Phaser;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(1);

        for (int i = 1; i <= 5; i++) {
            System.out.println("Building the house #" + i);
            new Build(phaser, "frame");
            new Build(phaser, "roof");
            new Build(phaser, "doors");
            new Build(phaser, "windows");

            Thread.sleep(1000);
            System.out.println("Building the house #" + i + " is finished\n");
            phaser.arriveAndAwaitAdvance();
        }

        phaser.arriveAndDeregister();
    }

    static class Build extends Thread {
        private final Phaser phaser;
        private final String target;

        private Build(Phaser phaser, String target) {
            this.phaser = phaser;
            this.target = target;

            phaser.register();
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                sleep(20);
                System.out.println(" -Building the " + this.target + " in the phase #" + phaser.getPhase() + " of the house.");
                phaser.arriveAndAwaitAdvance();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            phaser.arriveAndDeregister();
        }
    }
}
