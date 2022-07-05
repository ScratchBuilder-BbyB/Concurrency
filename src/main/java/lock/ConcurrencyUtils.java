package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class ConcurrencyUtils {

  public static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

  public static void sleep(int delay){
    try {
      Thread.sleep(delay * 1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static int randomSleep(){
    int sleepTime = (int) ((Math.random() * 10) % 5);
    try {
      Thread.sleep(sleepTime * 1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return sleepTime;
  }
}
