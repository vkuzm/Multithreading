package Semaphore;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore parkingSpot = new Semaphore(5);

        for (int i = 1; i <= 10; i++) {
            Parking parking = new Parking();
            parking.setName("Car " + i);
            parking.setParkingSpot(parkingSpot);

            parking.start();
        }
    }

    static class Parking extends Thread {
        private Semaphore parkingSpot;

        void setParkingSpot(Semaphore parkingSpot) {
            this.parkingSpot = parkingSpot;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " is waiting");

            try {
                parkingSpot.acquire();
                System.out.println(this.getName() + " is parked");

                Thread.sleep(1000);

                parkingSpot.release();
                System.out.println(this.getName() + " is out");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
