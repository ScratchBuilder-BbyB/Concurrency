package sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {

  private final ReentrantLock lock = new ReentrantLock();
  private final Condition evenCondition = lock.newCondition();
  private final Condition oddCondition = lock.newCondition();
  private final Condition zeroCondition = lock.newCondition();

  private int n = 2;


  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero(IntConsumer printNumber) throws InterruptedException {
    boolean isEven = false;
    for (int i = 0; i < n; i++) {
      lock.lock();
      try {
        if (i != 0) {
          try {
            zeroCondition.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        printNumber.accept(0);
        if (isEven) {
          evenCondition.signal();
          isEven = false;
        } else {
          oddCondition.signal();
          isEven = true;
        }
      } finally {
        lock.unlock();
      }
    }
  }

  public void even(IntConsumer printNumber) throws InterruptedException {
    for (int i = 2; i <= n; i += 2) {
      lock.lock();
      try {
        evenCondition.await();
        printNumber.accept(i);
        zeroCondition.signal();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }
  }

  public void odd(IntConsumer printNumber) throws InterruptedException {
    for (int i = 1; i <= n; i += 2) {
      lock.lock();
      try {
        oddCondition.await();
        printNumber.accept(i);
        zeroCondition.signal();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }
  }
}
