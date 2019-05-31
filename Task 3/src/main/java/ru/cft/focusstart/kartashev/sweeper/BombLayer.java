package ru.cft.focusstart.kartashev.sweeper;

class BombLayer {
    private Matrix bombMap;
    private int totalBombs;
    private Ranges ranges;

    BombLayer(int totalBombs, Ranges ranges) {
        this.totalBombs = totalBombs;
        this.ranges = ranges;
        fixBombsCount();
    }

    void start() {
        bombMap = new Matrix(Box.zero, ranges);
        for (int j = 0; j < totalBombs; j++)
            placeBomb();
    }

    Box get(Coord coord) {
        return bombMap.get(coord);
    }

    private void fixBombsCount() {
        int maxBombs = ranges.getSize().x * ranges.getSize().y / 3;
        if (totalBombs > maxBombs) {
            totalBombs = maxBombs;
        }
    }

    private void placeBomb() {
        while (true) {
            Coord coord = ranges.getRandomCoord();
            if (Box.bomb == bombMap.get(coord)) {
                continue;
            }
            bombMap.set(new Coord(coord.x, coord.y), Box.bomb);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb(Coord coord) {
        for (Coord around : ranges.getCoordsAround(coord)) {
            if (Box.bomb != bombMap.get(around)) {
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
            }
        }
    }

    int getTotalBombs() {
        return totalBombs;
    }
}
