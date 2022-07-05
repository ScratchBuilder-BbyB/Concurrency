package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lock.ConcurrencyUtils;

public class CompletableFutureRunner {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    OrderManager orderManager = new OrderManager();
//    for(int i = 0; i < 10; i++){
    ExecutorService ioPool = Executors.newFixedThreadPool(10);
    ExecutorService cpuPool = Executors.newFixedThreadPool(4);
    CompletableFuture.supplyAsync(
              () -> orderManager.fetchOrder(), ioPool)
          .thenApplyAsync(order -> orderManager.enrichOrder(order), cpuPool)
          .thenApplyAsync(order -> orderManager.processPaymentForOrder(order), ioPool)
          .thenApplyAsync(order -> orderManager.dispatchOrder(order), cpuPool)
          .thenApplyAsync(order -> orderManager.sendEmail(order), cpuPool);
//    }

    System.out.println("Main thread is non blocking...Hence very scalable ...");
    Thread.currentThread().join(1000 * 100);
  }
}
