package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lock.ConcurrencyUtils;

public class FutureRunner {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    OrderManager orderManager = new OrderManager();
    Future<Order> orderFuture = ConcurrencyUtils.EXECUTOR_SERVICE.submit(
        () -> orderManager.fetchOrder());
    Order order = orderFuture.get();

    Order finalOrder = order;
    orderFuture = ConcurrencyUtils.EXECUTOR_SERVICE.submit(
        () -> orderManager.enrichOrder(finalOrder));
     order = orderFuture.get();

    System.out.println("Main thread was blocking...Hence not scalable...");
    ConcurrencyUtils.EXECUTOR_SERVICE.shutdown();
  }
}
