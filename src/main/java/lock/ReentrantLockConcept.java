package lock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReentrantLockConcept {

  private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    Booking booking = new Booking();
    Book detail1 = new Book("User1", false);
    Book detail2 = new Book("User2", false);
    Book detail3 = new Book("User3", false);
    Book detail4 = new Book("User4", false);

    Future<Book> bookFuture = EXECUTOR_SERVICE.submit(() -> booking.book(3, detail1));

    Book book = bookFuture.get();
    System.out.println(book);
    EXECUTOR_SERVICE.shutdown();

//    Thread thread1 = new Thread(() -> booking.book(3, detail1), "thread1");
//    Thread thread2 = new Thread(() -> booking.book(3, detail2), "thread2");
//    Thread thread3 = new Thread(() -> booking.book(3, detail3), "thread3");
//    Thread thread4 = new Thread(() -> booking.book(3, detail4), "thread4");
//
//    thread4.start();
//    thread2.start();
//    thread1.start();
//    thread3.start();
  }
}
