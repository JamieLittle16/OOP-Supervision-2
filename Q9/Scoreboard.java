package Q9;

import java.io.*;
import java.util.*;

class Scoreboard {
  private List<PlayerStats> statsList;
  private final String FILENAME;

  public Scoreboard() {
    this.statsList = new ArrayList<>();
    FILENAME = "scoreboard.csv";
    loadScores();
  }

  public Scoreboard(String fileName) {
    this.statsList = new ArrayList<>();
    FILENAME = fileName;
    loadScores();
  }

  public void updateScore(Player player, boolean isWin) {
    PlayerStats stats = findStatsById(player.getId());

    if (stats == null) {
      stats = new PlayerStats(player.getId(), player.getUsername(), 0, 0);

      statsList.add(stats);
    }

    if (isWin) {
      stats.addWin();
    } else {
      stats.addLoss();
    }
    saveScores();
  }

  public void displayTopScores() {
    statsList.sort((p1, p2) -> Integer.compare(p2.getWins(), p1.getWins()));

    System.out.println("\n============= HALL OF FAME =============");
    System.out.println(String.format("%-4s | %-15s | %-5s | %-5s", "ID", "Player", "Wins", "Played"));
    System.out.println("---------------------------------------");

    for (PlayerStats p : statsList) {
      System.out.println(String.format("%-4d | %-15s | %-5d | %-5d",
          p.getPlayerId(),
          p.getUsername(),
          p.getWins(),
          p.getGamesPlayed()));
    }
    System.out.println("========================================\n");
  }

  private PlayerStats findStatsById(int id) {
    for (PlayerStats ps : statsList) {
      if (ps.getPlayerId() == id) {
        return ps;
      }
    }
    return null;
  }

  private void saveScores() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
      for (PlayerStats ps : statsList) {
        // CSV Format: id,username,wins,total
        writer.println(ps.getPlayerId() + "," +
            ps.getUsername() + "," +
            ps.getWins() + "," +
            ps.getGamesPlayed());
      }
    } catch (IOException e) {
      System.err.println("Error saving scoreboard: " + e.getMessage());
    }
  }

  private void loadScores() {
    File file = new File(FILENAME);
    if (!file.exists())
      return;

    int highestIdFound = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
          int id = Integer.parseInt(parts[0]);
          String name = parts[1];
          int wins = Integer.parseInt(parts[2]);
          int total = Integer.parseInt(parts[3]);

          statsList.add(new PlayerStats(id, name, wins, total));

          if (id > highestIdFound) {
            highestIdFound = id;
          }
        }
      }
      Player.setNextId(highestIdFound);

    } catch (IOException e) {
      System.err.println("Error loading scoreboard.");
    }
  }
}
