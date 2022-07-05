package future;

import lock.ConcurrencyUtils;

public class OrderManager {

  private String getThreadName(){
    return Thread.currentThread().getName();
  }

  public Order fetchOrder(){
    System.out.println("Fetching order..!!"+" -- "+getThreadName());
    ConcurrencyUtils.randomSleep();
    Order order = new Order();
    order.setOrderFetched(true);
    System.out.println("Fetched..!!"+" -- "+getThreadName());
    return order;
  }

  public Order enrichOrder(Order order){
    System.out.println("Enriching order..!!"+" -- "+getThreadName());
    ConcurrencyUtils.randomSleep();
    order.setOrderEnriched(true);
    System.out.println("Enriched..!!"+" -- "+getThreadName());
    return order;
  }

  public Order processPaymentForOrder(Order order){
    System.out.println("Processing Payment..!!"+" -- "+getThreadName());
    ConcurrencyUtils.randomSleep();
    order.setPaymentProcessed(true);
    System.out.println("Payment processed..!!"+" -- "+getThreadName());
    return order;
  }

  public Order dispatchOrder(Order order){
    System.out.println("Dispatching order..!!"+" -- "+getThreadName());
    ConcurrencyUtils.randomSleep();
    order.setOrderDispatched(true);
    System.out.println("Dispatched..!!"+" -- "+getThreadName());
    return order;
  }

  public Order sendEmail(Order order){
    System.out.println("Sending email..!!"+" -- "+getThreadName());
    ConcurrencyUtils.randomSleep();
    order.setEmailSent(true);
    System.out.println("Email sent..!!"+" -- "+getThreadName());
    return null;
  }
}
