package CallableFutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws Exception {
        Callable<Integer> callable = new Calculation();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        new Thread(futureTask).start();

        System.out.println(futureTask.get());
    }

    static class Calculation implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int sum = 0;

            for (int i = 0; i <= 100; i++) {
                Thread.sleep(10);
                sum = i;
            }

            return sum;
        }
    }
}
