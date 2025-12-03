package Q9;

import java.util.Scanner;

class GameLauncher {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Scoreboard scoreboard = new Scoreboard();

    System.out.println("HiLo Guessing Game");

    boolean keepPlaying = true;
    while (keepPlaying) {
      System.out.println("Enter your username:");
      String username = scanner.next();
      Player player = new Player(username);

      Difficulty difficulty = selectDifficulty(scanner);

      HiloGame game = new HiloGame(difficulty);
      System.out.println("\nGame Started! Guess numbers between 1 and " + difficulty.getUpperRange());
      System.out.println("You have " + difficulty.getMaxGuesses() + " guesses.");

      boolean gameRunning = true;
      while (gameRunning) {
        System.out.println("Enter your guess: ");

        while (!scanner.hasNextInt()) {
          System.out.println("Not a number, try again");
          scanner.next();
        }
        int guess = scanner.nextInt();

        GuessResult result = game.makeGuess(guess);

        switch (result) {
          case GO_HIGHER:
            System.out.println(" -> Go Higher!");
            System.out.println("   (Guesses left: " + game.getGuessesRemaining() + ")");
            break;

          case GO_LOWER:
            System.out.println(" -> Go Lower!");
            System.out.println("   (Guesses left: " + game.getGuessesRemaining() + ")");
            break;

          case CORRECT:
            System.out.println("\n CORRECT! You won!");
            scoreboard.updateScore(player, true); // Record Win
            gameRunning = false; // Exit loop
            break;

          case GAME_OVER:
            System.out.println("\n GAME OVER! You ran out of guesses.");
            System.out.println("The number was: " + game.getTargetNumber());
            scoreboard.updateScore(player, false); // Record Loss
            gameRunning = false; // Exit loop
            break;
        }
      }
      scoreboard.displayTopScores();

      System.out.println("Do you want to play again? (Y/N)");
      String answer = scanner.next();
      if (!answer.equalsIgnoreCase("Y")) {
        keepPlaying = false;
      }
    }
    System.out.println("Thanks for playing!");
    scanner.close();
  }

  private static Difficulty selectDifficulty(Scanner scanner) {
    System.out.println("\nSelect Difficulty:");
    System.out.println("1. Easy   (10 guesses, Range 1-20)");
    System.out.println("2. Medium (7 guesses,  Range 1-50)");
    System.out.println("3. Hard   (5 guesses,  Range 1-100)");
    System.out.print("Choice: ");

    int choice = 0;
    if (scanner.hasNextInt()) {
      choice = scanner.nextInt();
    } else {
      scanner.next(); // clear bad input
    }

    switch (choice) {
      case 1:
        return Difficulty.EASY;
      case 2:
        return Difficulty.MEDIUM;
      case 3:
        return Difficulty.HARD;
      default:
        System.out.println("Invalid choice. Defaulting to Medium.");
        return Difficulty.MEDIUM;
    }
  }
}
