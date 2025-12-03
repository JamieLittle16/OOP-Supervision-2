class NinjaEmployee extends Employee implements INinja {

  private Ninja hiddenNinja = new Ninja();

  public void throwShuriken() {
    hiddenNinja.throwShuriken();
  }

  public void sneak() {
    hiddenNinja.sneak();
  }
}
