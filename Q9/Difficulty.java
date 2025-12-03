package Q9;

public enum Difficulty {
  // Difficulty Levels
  EASY(10, 20), // 10 guesses, range 1-20
  MEDIUM(7, 50), // 7 guesses, range 1-50
  HARD(5, 100); // 5 guesses, range 1-100

  // Game settings
  private final int maxGuesses;
  private final int upperRange;

  Difficulty(int maxGuesses, int upperRange) {
    this.maxGuesses = maxGuesses;
    this.upperRange = upperRange;
  }

  public int getMaxGuesses() {
    return maxGuesses;
  }

  public int getUpperRange() {
    return upperRange;
  }
}
