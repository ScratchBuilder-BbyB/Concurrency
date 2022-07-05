package sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;

public class WaitNotifyConcept {



  public static void main(String[] args) throws InterruptedException {

    ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd();
    IntConsumer intConsumer = (a) -> {
      System.out.print(a);
    };
      Thread thread1 = new Thread(() -> {
        try {
          zeroEvenOdd.even(intConsumer);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });

    Thread thread2 = new Thread(() -> {
      try {
        zeroEvenOdd.odd(intConsumer);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread thread3 = new Thread(() -> {
      try {
        zeroEvenOdd.zero(intConsumer);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

      thread1.start();
      thread2.start();
      thread3.start();

      Thread.currentThread().join(1000);
//      System.out.println("exiting....");
  }

  public static void sleep(int delay){
    try {
      Thread.sleep(delay * 1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
