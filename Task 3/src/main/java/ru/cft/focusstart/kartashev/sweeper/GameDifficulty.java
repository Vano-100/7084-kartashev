package ru.cft.focusstart.kartashev.sweeper;

public enum GameDifficulty {

    Beginner(9, 9, 10),
    Experienced(16, 16, 40),
    Professional(16, 30, 99);

    private int cols;
    private int rows;
    private int bombs;

    GameDifficulty(int cols, int rows, int bombs) {

    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

}
