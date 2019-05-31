package ru.cft.focusstart.kartashev.gui;

import ru.cft.focusstart.kartashev.sweeper.GameDifficulty;

import java.util.ArrayList;
import java.util.List;

class Records {
    private List<Record> recordList = new ArrayList<>();

    void addRecordToList(String playerName, GameDifficulty difficulty, int time) {
        recordList.add(new Record(playerName, difficulty, time));
    }

    List<Record> getRecordList() {
        return recordList;
    }
}
