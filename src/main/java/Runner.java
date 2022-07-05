import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

  public static void main(String[] args) {

    FooBar fooBar = new FooBar(10);
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.submit(new Task(fooBar, false));
    executorService.submit(new Task(fooBar, true));
    executorService.shutdown();
  }

  private static class Task implements Runnable{
    private final FooBar fooBar;
    private final boolean callFoo;

    private Task(FooBar fooBar, boolean callFoo) {
      this.fooBar = fooBar;
      this.callFoo = callFoo;
    }

    @Override
    public void run() {
      if(callFoo){
        try {
          fooBar.foo(() -> System.out.println("foo"));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }else{
        try {
          fooBar.bar(() -> System.out.println("bar"));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
