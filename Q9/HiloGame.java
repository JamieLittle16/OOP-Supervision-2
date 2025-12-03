package Q9;

import java.util.Random;
import Q9.Difficulty;

class HiloGame {
  private int currentGuessCount;
  private final int targetNumber;
  private final Difficulty difficulty;
  private boolean isGameOver;

  public HiloGame(Difficulty difficulty) {
    this.difficulty = difficulty;
    this.currentGuessCount = 0;
    this.isGameOver = false;

    this.targetNumber = generateRandomNumber();
  }

  public GuessResult makeGuess(int guess) {
    if (isGameOver) {
      return GuessResult.GAME_OVER;
    }
    currentGuessCount++;

    if (guess == targetNumber) {
      return GuessResult.CORRECT;
    }
    if (currentGuessCount >= difficulty.getMaxGuesses()) {
      return GuessResult.GAME_OVER;
    }
    if (guess < targetNumber) {
      return GuessResult.GO_HIGHER;
    } else {
      return GuessResult.GO_LOWER;
    }
  }

  private int generateRandomNumber() {
    Random rand = new Random();
    return rand.nextInt(difficulty.getUpperRange()) + 1;
  }

  public int getTargetNumber() {
    return targetNumber;
  }

  public int getGuessesRemaining() {
    return difficulty.getMaxGuesses() - currentGuessCount;
  }
}
