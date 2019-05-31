package ru.cft.focusstart.kartashev.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class RecordTable extends JFrame {

    RecordTable(Records records) {
        initFrame(records);
    }

    private void initFrame(Records records) {
        setTitle("Таблица рекордов");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        RecordTableModel tableModel = new RecordTableModel(records);
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
