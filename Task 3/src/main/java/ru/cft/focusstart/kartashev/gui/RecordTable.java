package ru.cft.focusstart.kartashev.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class RecordTable extends JFrame {

    private static List<Record> recordList = new ArrayList<>();

    RecordTable() {
        initFrame();
    }

    static void addRecordToList(String playerName, GameDifficulty difficulty, int time) {
        recordList.add(new Record(playerName, difficulty, time));
    }

    private void initFrame() {
        setTitle("Таблица рекордов");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        RecordTableModel tableModel = new RecordTableModel(recordList);
        JTable table = new JTable(tableModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        table.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }
}
