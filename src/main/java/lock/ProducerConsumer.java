package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {

  private static final int queueSize = 5;
  private static int currentSize = 0;
  private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
  private static ReentrantLock lock = new ReentrantLock();
  private static Condition consume = lock.newCondition();
  private static Condition produce = lock.newCondition();


  public static void main(String[] args) throws InterruptedException {
    new Thread(() -> {for(int i = 0; i < 10; i++){
      EXECUTOR_SERVICE.submit(() -> produce());
      ConcurrencyUtils.randomSleep();
    }}).start();


    new Thread(() -> {for(int i = 0; i < 10; i++){
      EXECUTOR_SERVICE.submit(() -> consume());
      ConcurrencyUtils.randomSleep();
    }}).start();

    System.out.println(EXECUTOR_SERVICE.awaitTermination(20, TimeUnit.SECONDS));
  }

  private static void produce(){
    try{
      lock.lock();
      while (currentSize == queueSize){
        await(produce);  // there are spurious wake-ups, hence putting in a loop
      }
      currentSize++;
      System.out.println("Producing..."+ currentSize);
      signal(consume);
    }finally {
      lock.unlock();
    }
  }

  private static void signal(Condition condition) {
    condition.signal();
  }

  private static void consume(){
    try{
      lock.lock();
      while (currentSize == 0){
        await(consume); // there are spurious wake-ups, hence putting in a loop
      }
      currentSize--;
      System.out.println("consuming..."+ currentSize);
      signal(produce);
    }finally {
      lock.unlock();
    }
  }

  private static void await(Condition condition) {
    try {
      condition.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
