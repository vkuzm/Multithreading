package CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Racing());

        new RaceCar(cyclicBarrier, "Red").start();
        new RaceCar(cyclicBarrier, "White").start();
        new RaceCar(cyclicBarrier, "Black").start();

        new RaceCar(cyclicBarrier, "Blue").start();
        new RaceCar(cyclicBarrier, "Yellow").start();
        new RaceCar(cyclicBarrier, "Purple").start();
    }

    static class Racing extends Thread {
        @Override
        public void run() {
            System.out.println("THE RACE HAS BEGUN!");
        }
    }

    static class RaceCar extends Thread {
        private final CyclicBarrier cyclicBarrier;
        private final String car;

        private RaceCar(CyclicBarrier cyclicBarrier, String car) {
            this.cyclicBarrier = cyclicBarrier;
            this.car = car;
        }

        @Override
        public void run() {
            try {
                System.out.println("The " + car + " car is ready to race.");
                sleep(1000);
                cyclicBarrier.await();

            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("The " + car + " car starts the race.");
        }
    }
}
