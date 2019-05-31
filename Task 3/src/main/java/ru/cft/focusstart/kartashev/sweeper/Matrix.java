package ru.cft.focusstart.kartashev.sweeper;

class Matrix {
    private Box[][] matrix;
    private Ranges ranges;

    Matrix(Box defaultBox, Ranges ranges) {
        this.ranges = ranges;
        matrix = new Box[ranges.getSize().x][ranges.getSize().y];
        for (Coord coord : ranges.getAllCoords())
            matrix[coord.x][coord.y] = defaultBox;
    }

    Box get(Coord coord) {
        if (ranges.inRange(coord))
            return matrix[coord.x][coord.y];
        return null;
    }

    void set(Coord coord, Box box) {
        if (ranges.inRange(coord))
            matrix[coord.x][coord.y] = box;
    }

}
