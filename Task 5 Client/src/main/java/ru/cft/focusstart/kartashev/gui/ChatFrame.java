package ru.cft.focusstart.kartashev.gui;

import ru.cft.focusstart.kartashev.UICallback;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class ChatFrame extends JFrame {
    private static final int MAX_MESSAGE_LENGTH = 49;

    private UICallback callback;
    private JList jList1;
    private JTextField textField1;
    private DefaultListModel<String> messageListModel;
    private DefaultListModel<String> usersListModel;
    private JScrollPane scrollPane;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private Border lineBorder;

    public ChatFrame(UICallback callback) {
        this.callback = callback;
        initFrame();
    }

    private void initFrame() {
        lineBorder = BorderFactory.createLineBorder(new Color(150, 185, 190));

        addJPanel1();

        addJPanel2();

        addTextField();

        addButton();

        addChatField();

        addUsersListField();

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                callback.onApplicationExit();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        pack();

        setTitle("CHAT");

        setVisible(true);
    }

    private void addJPanel2() {
        jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(500, 40));
        jPanel2.setLayout(new BorderLayout());
        add(jPanel2, BorderLayout.PAGE_END);
    }

    private void addJPanel1() {
        jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(550, 400));
        jPanel1.setLayout(new BorderLayout());
        add(jPanel1, BorderLayout.LINE_START);
    }

    private void addUsersListField() {
        usersListModel = new DefaultListModel<>();
        usersListModel.addElement("Список гостей:");
        JList jList2 = new JList<>(usersListModel);
        jList2.setPreferredSize(new Dimension(150, 400));
        jList2.setFont(new Font("Franklin Gothic", Font.PLAIN, 14));
        jList2.setBorder(lineBorder);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) jList2.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(jList2, BorderLayout.LINE_END);
    }

    private void addChatField() {
        messageListModel = new DefaultListModel<>();
        scrollPane = new JScrollPane();
        jList1 = new JList<>(messageListModel);
        jList1.setBorder(lineBorder);
        jList1.setFont(new Font("Franklin Gothic", Font.PLAIN, 14));
        scrollPane.setPreferredSize(new Dimension(400, 400));
        scrollPane.setViewportView(jList1);
        scrollPane.getVerticalScrollBar().setBackground(new Color(70, 150, 190));
        scrollPane.getVerticalScrollBar().setForeground(Color.green);
        jPanel1.add(scrollPane, BorderLayout.LINE_START);
    }

    private void addTextField() {
        textField1 = new JTextField();
        textField1.setDocument(new JTextFieldLimit(300));
        textField1.setPreferredSize(new Dimension(400, 40));
        textField1.setFont(new Font("Franklin Gothic", Font.PLAIN, 16));

        textField1.addActionListener(e -> {
            callback.onMessageEntered(textField1.getText());
            textField1.setText("");
        });
        jPanel2.add(textField1, BorderLayout.WEST);
    }

    private void addButton() {
        JButton button1 = new JButton("Отправить");
        button1.setPreferredSize(new Dimension(150, 10));
        button1.addActionListener(e -> {
            callback.onMessageEntered(textField1.getText());
            textField1.setText("");
        });
        button1.setForeground(Color.WHITE);
        button1.setBackground(new Color(70, 150, 190));
        jPanel2.add(button1, BorderLayout.EAST);
    }

    public void addMessage(String message) {
        if (message.length() <= MAX_MESSAGE_LENGTH + 1) {
            messageListModel.addElement(message);
            jList1.revalidate();
            scrollToBot();
            repaint();
        } else {
            addLongMessage(splitMessage(message));
        }
    }

    private void addLongMessage(List<String> splitMessage) {
        for (String messagePart : splitMessage) {
            addMessage(messagePart);
        }
    }

    private List<String> splitMessage(String message) {
        List<String> splitMessage = new ArrayList<>();
        int startPosition = 0;
        String wordWrap;
        for (int i = MAX_MESSAGE_LENGTH; i < message.length(); i++) {
            wordWrap = "";
            if (i % MAX_MESSAGE_LENGTH == 0) {
                if (message.charAt(i) != ' ') {
                    wordWrap = "-";
                }
                splitMessage.add(message.substring(startPosition, i) + wordWrap);
                startPosition = i;
            }
            if (i == message.length() - 1) {
                splitMessage.add(message.substring(startPosition));
            }
        }
        return splitMessage;
    }

    private void addUserToList(String userName) {
        usersListModel.addElement(userName);
        repaint();
    }


    public void setUsersList(String[] userNames) {
        for (String userName : userNames) {
            addUserToList(userName);
        }
    }


    public void setRecentMessages(String[] recentMessages) {
        for (String message : recentMessages) {
            addMessage(message);
        }
        scrollToBot();
    }

    private void scrollToBot() {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        AdjustmentListener scroll = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(verticalBar.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(scroll);
    }
}
