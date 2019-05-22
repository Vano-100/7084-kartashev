package ru.cft.focusstart.kartashev.sweeper;

class FlagLayer {
    private Matrix flagMap;
    private int countOfClosedBoxes;
    private int countOfFlagedBoxes;

    void start() {
        flagMap = new Matrix(Box.closed);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
        countOfFlagedBoxes = 0;
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    int getCountOfFlagedBoxes() {
        return countOfFlagedBoxes;
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.opened);
        countOfClosedBoxes--;
    }

    void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case flaged:
                setClosedToBox(coord);
                countOfFlagedBoxes--;
                break;
            case closed:
                setFlagedToBomb(coord);
                countOfFlagedBoxes++;
                break;
        }
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.closed);
    }

    private void setFlagedToBomb(Coord coord) {
        flagMap.set(coord, Box.flaged);
    }

    int getCountClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.bombed);
    }

    void setOpenedToClosedBombBox(Coord coord) {
        if (flagMap.get(coord) == Box.closed) {
            flagMap.set(coord, Box.opened);
        }
    }

    void setNoBombToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.flaged) {
            flagMap.set(coord, Box.nobomb);
        }
    }

    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord))
            if (flagMap.get(around) == Box.flaged) {
                count++;
            }
        return count;
    }


}
