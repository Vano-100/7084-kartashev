package ru.cft.focusstart.kartashev.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ru.cft.focusstart.kartashev.sweeper.*;
import ru.cft.focusstart.kartashev.sweeper.Box;

public class SweeperMainFrame extends JFrame implements Callback {
    private Game game;
    private JPanel panel;
    private JLabel bombsCountLabel;
    private JLabel timerLabel;
    private Timer timer;
    private static int cols = 9;
    private static int rows = 9;
    private static int bombs = 10;
    private static GameDifficulty difficulty = GameDifficulty.Новичек;
    private final int IMAGE_SIZE = 30;
    private int currentTime;


    public static void main(String[] args) {
        new SweeperMainFrame();
    }

    private SweeperMainFrame() {
        game = new Game(cols, rows, bombs);
        game.start();
        setImages();
        initGamePanel();
        initBombsCountLabel();
        initTimerLabel();
        initInfoPanel();
        initMenu();
        startTimer();
        initFrame();
    }

    private void initMenu() {
        JMenu jMenu = new JMenu("Игра");
        JMenu levelOfDifficulty = new JMenu("Сложность");
        JMenuItem beginner = new JMenuItem("Новичек");
        JMenuItem skilled = new JMenuItem("Опытный");
        JMenuItem expert = new JMenuItem("Профессионал");
        JMenuItem records = new JMenuItem("Рекорды");
        beginner.addActionListener(e -> changeDifficulty(GameDifficulty.Новичек));
        skilled.addActionListener(e -> changeDifficulty(GameDifficulty.Опытный));
        expert.addActionListener(e -> changeDifficulty(GameDifficulty.Профессионал));
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
        new RecordTable();
    }

    private void changeDifficulty(GameDifficulty difficulty) {
        SweeperMainFrame.difficulty = difficulty;
        switch (difficulty) {
            case Новичек:
                cols = 9;
                rows = 9;
                bombs = 10;
                break;
            case Опытный:
                cols = 16;
                rows = 16;
                bombs = 40;
                break;
            case Профессионал:
                cols = 16;
                rows = 30;
                bombs = 99;
        }
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
        timerLabel.setPreferredSize(new Dimension((Ranges.getSize().x * IMAGE_SIZE) / 2 - 15, IMAGE_SIZE));
        timerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        timerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        timerLabel.setForeground(Color.BLACK);
    }

    private void initBombsCountLabel() {
        bombsCountLabel = new JLabel("Бомб : " + game.getCountOfUnflagedBombs());
        bombsCountLabel.setHorizontalAlignment(JLabel.LEFT);
        bombsCountLabel.setPreferredSize(new Dimension((Ranges.getSize().x * IMAGE_SIZE) / 2, IMAGE_SIZE));
        bombsCountLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        bombsCountLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        bombsCountLabel.setForeground(Color.BLACK);
        add(bombsCountLabel, BorderLayout.NORTH);
    }

    private void initGamePanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords())
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE,
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
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
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
        RecordTable.addRecordToList(name, difficulty, currentTime);
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
        setIconImage(getImage("icon"));
        setLocationRelativeTo(null);
        pack();
    }

    private void setImages() {
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
    }

    private Image getImage(String name) {
        String filename = "/img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
