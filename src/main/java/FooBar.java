import java.util.concurrent.Semaphore;

public class FooBar {
  private final int n;
  private boolean isFooPrinted;
  private boolean isBarPrinted = true;


  public FooBar(int n) {
    this.n = n;
  }

  public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      synchronized(this){
        while(!isBarPrinted){
          wait();
        }
        // printFoo.run() outputs "foo". Do not change or remove this line.
        printFoo.run();
        notify();
        isFooPrinted = true;
        isBarPrinted = false;
      }
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      synchronized(this){
        while(!isFooPrinted){
          wait();
        }
        // printBar.run() outputs "bar". Do not change or remove this line.
        printBar.run();
        notify();
        isFooPrinted = false;
        isBarPrinted = true;
      }

    }
  }
}
