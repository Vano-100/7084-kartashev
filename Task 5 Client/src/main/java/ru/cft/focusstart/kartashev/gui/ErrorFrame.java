package ru.cft.focusstart.kartashev.gui;

import javax.swing.*;
import java.awt.*;

public class ErrorFrame extends JFrame {
    private String message;


    public ErrorFrame(String message) {
        this.message = message;
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(message.length() * 10, 100));
        setResizable(false);
        setTitle("Ошибка");
        setLocationRelativeTo(null);
        JLabel infoLabel;
        add(infoLabel = new JLabel(message), BorderLayout.CENTER);
        infoLabel.setHorizontalAlignment(JTextField.CENTER);
        infoLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        JButton button;
        add(button = new JButton("OK"), BorderLayout.PAGE_END);
        button.addActionListener(e -> this.dispose());
        setVisible(true);
        pack();
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
    }
}
