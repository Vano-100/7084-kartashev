package ru.cft.focusstart.kartashev.gui;

import ru.cft.focusstart.kartashev.sweeper.GameDifficulty;

class Record {
    private String playerName;
    private GameDifficulty difficulty;
    private int time;

    Record(String playerName, GameDifficulty difficulty, int time) {
        this.playerName = playerName;
        this.difficulty = difficulty;
        this.time = time;
    }

    String getPlayerName() {
        return playerName;
    }

    GameDifficulty getDifficulty() {
        return difficulty;
    }

    int getTime() {
        return time;
    }
}
