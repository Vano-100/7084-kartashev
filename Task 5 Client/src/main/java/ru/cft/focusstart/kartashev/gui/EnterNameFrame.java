package ru.cft.focusstart.kartashev.gui;

import ru.cft.focusstart.kartashev.UICallback;

import javax.swing.*;
import java.awt.*;

public class EnterNameFrame extends JFrame {
    private JTextField nameTextField;
    private JTextField serverTextField;
    private UICallback callback;

    public EnterNameFrame(UICallback callback) {
        this.callback = callback;
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Panel topPanel = new Panel();
        topPanel.add(createEnterNameLabel(), BorderLayout.NORTH);
        topPanel.add(createEnterNameField(), BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        Panel botPanel = new Panel();
        botPanel.add(createEnterServerLabel(), BorderLayout.CENTER);
        botPanel.add(createEnterServerField(), BorderLayout.SOUTH);
        add(botPanel, BorderLayout.CENTER);
        add(createButton(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Введите имя и порт");
    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setText("Подключиться");
        button.addActionListener(e -> {
            if (!nameTextField.getText().isEmpty() && !serverTextField.getText().isEmpty()) {
                callback.onConnectionInfoEntered(nameTextField.getText(), serverTextField.getText());
            }
        });
        return button;
    }

    private JTextField createEnterServerField() {
        serverTextField = new JTextField(15);
        serverTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        serverTextField.setHorizontalAlignment(JTextField.CENTER);
        serverTextField.setToolTipText("Введите адрес сервера");
        serverTextField.setText("localhost:1111");
        return serverTextField;
    }

    private JLabel createEnterServerLabel() {
        JLabel label = new JLabel("Введите адрес сервера");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(300, 30));
        return label;
    }

    private JLabel createEnterNameLabel() {
        JLabel label = new JLabel("Введите имя");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(300, 30));
        return label;
    }

    private JTextField createEnterNameField() {
        nameTextField = new JTextField(15);
        nameTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        nameTextField.setHorizontalAlignment(JTextField.CENTER);
        nameTextField.setToolTipText("Введите имя");
        nameTextField.setDocument(new JTextFieldLimit(15));
        return nameTextField;
    }
}
