package future;

import java.util.UUID;

public class Order {

  private final String ID = UUID.randomUUID().toString();
  private boolean orderFetched;
  private boolean orderEnriched;
  private boolean paymentProcessed;
  private boolean orderDispatched;
  private boolean emailSent;

  public boolean isOrderFetched() {
    return orderFetched;
  }

  public void setOrderFetched(boolean orderFetched) {
    this.orderFetched = orderFetched;
  }

  public boolean isOrderEnriched() {
    return orderEnriched;
  }

  public void setOrderEnriched(boolean orderEnriched) {
    this.orderEnriched = orderEnriched;
  }

  public boolean isPaymentProcessed() {
    return paymentProcessed;
  }

  public void setPaymentProcessed(boolean paymentProcessed) {
    this.paymentProcessed = paymentProcessed;
  }

  public boolean isOrderDispatched() {
    return orderDispatched;
  }

  public void setOrderDispatched(boolean orderDispatched) {
    this.orderDispatched = orderDispatched;
  }

  public boolean isEmailSent() {
    return emailSent;
  }

  public void setEmailSent(boolean emailSent) {
    this.emailSent = emailSent;
  }

  @Override
  public String toString() {
    return "Order{" +
        "ID='" + ID + '\'' +
        ", orderFetched=" + orderFetched +
        ", orderEnriched=" + orderEnriched +
        ", paymentProcessed=" + paymentProcessed +
        ", orderDispatched=" + orderDispatched +
        ", emailSent=" + emailSent +
        '}';
  }
}
