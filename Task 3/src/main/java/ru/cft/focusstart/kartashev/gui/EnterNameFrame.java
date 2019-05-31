package ru.cft.focusstart.kartashev.gui;

import javax.swing.*;
import java.awt.*;

class EnterNameFrame extends JFrame {
    private Callback callback;

    EnterNameFrame(Callback callback) {
        this.callback = callback;
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Вы победили!");
        setResizable(false);
        add(createLabel(), BorderLayout.NORTH);
        add(createTextField(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel() {
        JLabel label = new JLabel("Введите имя");
        label.setFont(Settings.getFont());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(300, 30));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setToolTipText("Введите имя");
        textField.addActionListener(e -> {
            if (textField.getText().length() == 0) {
                return;
            }
            callback.onWinnerNameEntered(textField.getText());
            setVisible(false);
            dispose();
        });
        return textField;
    }
}
