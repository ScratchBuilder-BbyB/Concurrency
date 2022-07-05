import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserAsCountdownLatchConcept {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    Phaser phaser = new Phaser(3);
    executorService.submit(new Task(phaser, 2));
    executorService.submit(new Task(phaser, 1));
    executorService.submit(new Task(phaser, 6));

    System.out.println("Waiting :: "+ Thread.currentThread().getName());
    phaser.awaitAdvance(0);
    System.out.println("All services established DB connection :: "+ phaser.getArrivedParties());
    executorService.shutdown();

  }

    private static class Task implements Runnable {

    private final Phaser phaser;
    private final int delay;

      Task(Phaser phaser, int delay) {
        this.phaser = phaser;
        this.delay = delay;
      }


      @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        ConcurrencyUtils.sleep(delay);
        System.out.println("Executing :: "+ threadName+" arrived :: "+ phaser.arrive());
      }
  }
}
