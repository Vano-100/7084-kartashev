package ru.cft.focusstart.kartashev.gui;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RecordTableModel extends AbstractTableModel {
    private List<Record> recordList;

    RecordTableModel(Records records) {
        this.recordList = records.getRecordList();
    }

    @Override
    public int getRowCount() {
        return recordList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return recordList.get(rowIndex).getPlayerName();
            case 1:
                switch (recordList.get(rowIndex).getDifficulty()) {
                    case Beginner:
                        return "Новичек";
                    case Experienced:
                        return "Опытный";
                    case Professional:
                        return "Профессионал";
                }
            case 2:
                return recordList.get(rowIndex).getTime();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        String result = "";
        switch (column) {
            case 0:
                result = "Имя";
                break;
            case 1:
                result = "Сложность";
                break;
            case 2:
                result = "Время";
                break;
        }
        return result;
    }
}
