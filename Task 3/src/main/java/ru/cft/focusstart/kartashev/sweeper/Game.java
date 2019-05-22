package ru.cft.focusstart.kartashev.sweeper;

public class Game {
    private BombLayer bombLayer;
    private FlagLayer flagLayer;
    private GameStat state;

    public GameStat getState() {
        return state;
    }

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bombLayer = new BombLayer(bombs);
        flagLayer = new FlagLayer();
    }

    public void start() {
        bombLayer.start();
        flagLayer.start();
        state = GameStat.PLAYING;
    }

    public int getCountOfUnflagedBombs() {
        int count = bombLayer.getTotalBombs();
        return count - flagLayer.getCountOfFlagedBoxes();
    }

    public Box getBox(Coord coord) {
        if (flagLayer.get(coord) == Box.opened) {
            return bombLayer.get(coord);
        } else {
            return flagLayer.get(coord);
        }
    }

    public void pressLeftButton(Coord coord) {
        if (gameOver()) {
            return;
        }
        openBox(coord);
        checkWinner();
    }

    private void checkWinner() {
        if (state == GameStat.PLAYING) {
            if (flagLayer.getCountClosedBoxes() == bombLayer.getTotalBombs()) {
                state = GameStat.WINNER;
            }
        }
    }

    private void openBox(Coord coord) {
        switch (flagLayer.get(coord)) {
            case opened:
                setOpenedToClosedBoxesAroundNumber(coord);
                return;

            case flaged:
                return;

            case closed:
                switch (bombLayer.get(coord)) {
                    case zero:
                        openBoxesAround(coord);
                        return;
                    case bomb:
                        openBombs(coord);
                        return;
                    default:
                        flagLayer.setOpenedToBox(coord);
                }
        }
    }

    private void openBombs(Coord bombedCoord) {
        state = GameStat.BOMBED;
        flagLayer.setBombedToBox(bombedCoord);
        for (Coord coord : Ranges.getAllCoords()) {
            if (bombLayer.get(coord) == Box.bomb) {
                flagLayer.setOpenedToClosedBombBox(coord);
            } else {
                flagLayer.setNoBombToFlagedSafeBox(coord);
            }
        }
    }

    private void openBoxesAround(Coord coord) {
        flagLayer.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord)) {
            openBox(around);
        }
    }

    public void pressRightButton(Coord coord) {
        if (gameOver()) {
            return;
        }
        flagLayer.toggleFlagedToBox(coord);
    }

    private boolean gameOver() {
        if (state == GameStat.PLAYING) {
            return false;
        }
        return true;
    }


    private void setOpenedToClosedBoxesAroundNumber(Coord coord) {
        if (bombLayer.get(coord) != Box.bomb)
            if (flagLayer.getCountOfFlagedBoxesAround(coord) == bombLayer.get(coord).getNumber())
                for (Coord around : Ranges.getCoordsAround(coord))
                    if (flagLayer.get(around) == Box.closed)
                        openBox(around);
    }


}
