package Q1;

abstract class Vehicle {
  int year;

  public void drive() {
    System.out.println("Vehicle is driving");
  }

  public abstract void refuel();
}

