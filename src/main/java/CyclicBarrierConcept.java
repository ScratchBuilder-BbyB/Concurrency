import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierConcept {

  public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    executorService.submit(new Task(cyclicBarrier, 0));
    executorService.submit(new Task(cyclicBarrier, 0));
    executorService.submit(new Task(cyclicBarrier, 5));

    ConcurrencyUtils.sleep(20);
    System.out.println("All reached....");

    executorService.shutdown();

  }

  private static class Task implements Runnable{

    private final CyclicBarrier cyclicBarrier;
    private final int delay;

    private Task(CyclicBarrier cyclicBarrier, int delay) {
      this.cyclicBarrier = cyclicBarrier;
      this.delay = delay;
    }

    @Override
    public void run() {
      String threadName = Thread.currentThread().getName();
      try {
        System.out.println(threadName+" processing....");
        ConcurrencyUtils.sleep(delay);
        cyclicBarrier.await();
        System.out.println(threadName+" reached");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }
}
