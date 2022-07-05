package lock;

public class Book {
  private String name;
  private boolean booked;

  public Book(String name, boolean booked) {
    this.name = name;
    this.booked = booked;
  }

  public Book() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isBooked() {
    return booked;
  }

  public void setBooked(boolean booked) {
    this.booked = booked;
  }

  @Override
  public String toString() {
    return "Book{" +
        "name='" + name + '\'' +
        ", booked=" + booked +
        '}';
  }
}
