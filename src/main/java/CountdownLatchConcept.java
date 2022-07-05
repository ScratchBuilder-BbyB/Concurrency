import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountdownLatchConcept {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    CountDownLatch countDownLatch = new CountDownLatch(3);
    executorService.submit(new Task(countDownLatch, 2));
    executorService.submit(new Task(countDownLatch, 1));
    executorService.submit(new Task(countDownLatch, 6));

    System.out.println("Waiting :: "+ Thread.currentThread().getName());
    countDownLatch.await(3, TimeUnit.SECONDS);
    System.out.println("All services established DB connection :: "+ countDownLatch.getCount());
    executorService.shutdown();

  }

    private static class Task implements Runnable {

    private final CountDownLatch countDownLatch;
    private final int delay;

      Task(CountDownLatch countDownLatch, int delay) {
        this.countDownLatch = countDownLatch;
        this.delay = delay;
      }


      @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        ConcurrencyUtils.sleep(delay);
        System.out.println("Executing :: "+ threadName);
        countDownLatch.countDown();
      }
  }
}
