package ru.cft.focusstart.kartashev.gui;

import ru.cft.focusstart.kartashev.sweeper.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SweeperMainFrame extends JFrame implements Callback {

    private static GameDifficulty difficulty = GameDifficulty.Beginner;
    private final int IMAGE_SIZE = 30;
    private Game game;
    private JPanel panel;
    private JLabel bombsCountLabel;
    private JLabel timerLabel;
    private Timer timer;
    private int currentTime;
    private Records records = new Records();
    private Ranges ranges = new Ranges();

    private SweeperMainFrame() {
        game = new Game(difficulty, ranges);
        game.start();
        initGamePanel();
        initBombsCountLabel();
        initTimerLabel();
        initInfoPanel();
        initMenu();
        startTimer();
        initFrame();
    }

    public static void main(String[] args) {
        new SweeperMainFrame();
    }

    private void initMenu() {
        JMenu jMenu = new JMenu("Игра");
        JMenu levelOfDifficulty = new JMenu("Сложность");
        JMenuItem beginner = new JMenuItem("Новичек");
        JMenuItem skilled = new JMenuItem("Опытный");
        JMenuItem expert = new JMenuItem("Профессионал");
        JMenuItem records = new JMenuItem("Рекорды");
        beginner.addActionListener(e -> changeDifficulty(GameDifficulty.Beginner));
        skilled.addActionListener(e -> changeDifficulty(GameDifficulty.Experienced));
        expert.addActionListener(e -> changeDifficulty(GameDifficulty.Professional));
        records.addActionListener(e -> showRecordTable());
        levelOfDifficulty.add(beginner);
        levelOfDifficulty.add(skilled);
        levelOfDifficulty.add(expert);
        jMenu.add(levelOfDifficulty);
        jMenu.addSeparator();
        jMenu.add(records);
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        setJMenuBar(jMenuBar);
    }

    private void showRecordTable() {
        new RecordTable(records);
    }

    private void changeDifficulty(GameDifficulty difficulty) {
        SweeperMainFrame.difficulty = difficulty;
        setVisible(false);
        dispose();
        new SweeperMainFrame();
    }

    private void startTimer() {
        currentTime = 0;
        timer = new Timer(1000, e -> {
            currentTime++;
            timerLabel.setText("Время : " + currentTime);
        });
        timer.start();
    }

    private void initInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.add(bombsCountLabel);
        infoPanel.add(timerLabel);
        add(infoPanel, BorderLayout.NORTH);
    }

    private void initTimerLabel() {
        timerLabel = new JLabel("Время : 0");
        timerLabel.setHorizontalAlignment(JLabel.LEFT);
        timerLabel.setPreferredSize(new Dimension((ranges.getSize().x * IMAGE_SIZE) / 2 - 15, IMAGE_SIZE));
        timerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        timerLabel.setFont(Settings.getFont());
        timerLabel.setForeground(Color.BLACK);
    }

    private void initBombsCountLabel() {
        bombsCountLabel = new JLabel("Бомб : " + game.getCountOfUnflagedBombs());
        bombsCountLabel.setHorizontalAlignment(JLabel.LEFT);
        bombsCountLabel.setPreferredSize(new Dimension((ranges.getSize().x * IMAGE_SIZE) / 2, IMAGE_SIZE));
        bombsCountLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        bombsCountLabel.setFont(Settings.getFont());
        bombsCountLabel.setForeground(Color.BLACK);
        add(bombsCountLabel, BorderLayout.NORTH);
    }

    private void initGamePanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : ranges.getAllCoords())
                    g.drawImage(game.getBox(coord).imageIcon.getImage(), coord.x * IMAGE_SIZE,
                            coord.y * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE, this);
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(coord);
                    checkWinner();
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(coord);
                }
                if (e.getButton() == MouseEvent.BUTTON2) {
                    game.start();
                    currentTime = 0;
                    timer.start();
                }
                bombsCountLabel.setText("Бомб : " + game.getCountOfUnflagedBombs());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(ranges.getSize().x * IMAGE_SIZE, ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private void checkWinner() {
        if (game.getState() == GameStat.WINNER) {
            timer.stop();
            new EnterNameFrame(this);
            this.setVisible(false);
        }
    }

    @Override
    public void onWinnerNameEntered(String name) {
        records.addRecordToList(name, difficulty, currentTime);
        game.start();
        currentTime = 0;
        timer.start();
        this.setVisible(true);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Сапёр");
        setResizable(false);
        setVisible(true);
        setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
        setLocationRelativeTo(null);
        pack();
    }
}
