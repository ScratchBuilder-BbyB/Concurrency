import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyUtils {

  public static void sleep(int delay){
    try {
      Thread.sleep(delay * 1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
