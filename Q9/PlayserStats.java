package Q9;

class PlayerStats {
  private int playerId;
  private String username;
  private int wins;
  private int gamesPlayed;

  public PlayerStats(int playerId, String username, int wins, int gamesPlayed) {
    this.playerId = playerId;
    this.username = username;
    this.wins = wins;
    this.gamesPlayed = gamesPlayed;
  }

  public void addWin() {
    this.wins++;
    this.gamesPlayed++;
  }

  public void addLoss() {
    this.gamesPlayed++;
  }

  public int getPlayerId() {
    return playerId;
  }

  public String getUsername() {
    return username;
  }

  public int getWins() {
    return wins;
  }

  public int getGamesPlayed() {
    return gamesPlayed;
  }
}
