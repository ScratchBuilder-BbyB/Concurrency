package lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockConcept {

  private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  private static final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
  private static final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

  public static void main(String[] args) throws InterruptedException {
    int count = 10;
      while(count-- >= 0){
        new Thread(() -> readFromResource()).start();
        new Thread(() -> writeToResource()).start();
      }

      Thread.currentThread().join(1000 * 10);
  }

  private static void readFromResource(){
    try{
      readLock.lock();
      ConcurrencyUtils.randomSleep();
      System.out.println(Thread.currentThread().getName()+" reading....");
    }finally {
      readLock.unlock();
    }
  }

  private static void writeToResource(){
    try{
      writeLock.lock();
      ConcurrencyUtils.randomSleep();
      System.out.println(Thread.currentThread().getName()+" writing....");
    }finally {
      writeLock.unlock();
    }
  }
}
