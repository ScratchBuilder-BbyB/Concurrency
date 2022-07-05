import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserAsCyclicBarrierConcept {

  public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    Phaser phaser = new Phaser(3);
    executorService.submit(new Task(phaser, 0));
    executorService.submit(new Task(phaser, 0));
    executorService.submit(new Task(phaser, 5));

    ConcurrencyUtils.sleep(20);
    System.out.println("All reached....");

    executorService.shutdown();

  }

  private static class Task implements Runnable{

    private final Phaser phaser;
    private final int delay;

    private Task(Phaser phaser, int delay) {
      this.phaser = phaser;
      this.delay = delay;
    }

    @Override
    public void run() {
      String threadName = Thread.currentThread().getName();
      System.out.println(threadName+" processing....");
      ConcurrencyUtils.sleep(delay);
      phaser.arriveAndAwaitAdvance();
      System.out.println(threadName+" reached");
    }
  }
}
