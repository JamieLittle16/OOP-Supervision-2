package Q4;

class Demo {
  public static void main(String[] args) {
    Vehicle[] vehicles = new Vehicle[] { new Car(), new Truck() };

    vehicles[0].drive();
    vehicles[1].drive();
  }
}
