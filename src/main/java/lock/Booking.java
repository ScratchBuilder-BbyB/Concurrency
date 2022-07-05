package lock;


import java.util.concurrent.locks.ReentrantLock;

public class Booking {

  private static ReentrantLock lock = new ReentrantLock();

    public Book book(int retryCount, Book bookingDetails){
      System.out.println("Try count :: "+ Thread.currentThread().getName()+" :: "+ retryCount);
      if(retryCount == 0){
        System.out.println("Seat not booked by :: "+ Thread.currentThread().getName());
        return bookingDetails;
      }
      boolean lockAcquired = false;
      lockAcquired = lock.tryLock();
      if(lockAcquired){
        try{
          ConcurrencyUtils.randomSleep();
          System.out.println("Seat booked by "+ Thread.currentThread().getName());
          bookingDetails.setBooked(true);
          return bookingDetails;
        }finally {
          lock.unlock();
        }
      }else{
        System.out.println("Retrying after 5 sec.."+ Thread.currentThread().getName());
        ConcurrencyUtils.sleep(5);
        return book(retryCount - 1, bookingDetails);
      }
    }
}
