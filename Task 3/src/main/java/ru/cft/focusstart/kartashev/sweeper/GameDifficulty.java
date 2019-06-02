package ru.cft.focusstart.kartashev.sweeper;

public enum GameDifficulty {

    Beginner(9, 9, 10),
    Experienced(16, 16, 40),
    Professional(16, 30, 99);

    int cols;
    int rows;
    int bombs;

    GameDifficulty(int cols, int rows, int bombs) {
        this.cols = cols;
        this.rows = rows;
        this.bombs = bombs;
    }
}
