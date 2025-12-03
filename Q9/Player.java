package Q9;

class Player {
  private static int nextId = 1;

  private int id;
  private String username;

  public Player(String username) {
    this.username = username;
    this.id = nextId;
    nextId++;
  }

  public String getUsername() {
    return username;
  }

  public int getId() {
    return id;
  }

  public static void setNextId(int highestId) {
    nextId = highestId + 1;
  }
}
