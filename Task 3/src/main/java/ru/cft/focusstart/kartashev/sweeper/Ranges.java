package ru.cft.focusstart.kartashev.sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private Coord size;
    private ArrayList<Coord> allCoords;
    private Random random = new Random();

    public Coord getSize() {
        return size;
    }

    void setSize(Coord size) {
        this.size = size;
        allCoords = new ArrayList<>();
        for (int y = 0; y < size.y; y++)
            for (int x = 0; x < size.x; x++)
                allCoords.add(new Coord(x, y));
    }

    public ArrayList<Coord> getAllCoords() {
        return allCoords;
    }

    boolean inRange(Coord coord) {
        return coord.x >= 0 && coord.x < size.x &&
                coord.y >= 0 && coord.y < size.y;
    }

    Coord getRandomCoord() {
        return new Coord(random.nextInt(size.x), random.nextInt(size.y));
    }

    ArrayList<Coord> getCoordsAround(Coord coord) {
        Coord around;
        ArrayList<Coord> list = new ArrayList<>();
        for (int x = coord.x - 1; x <= coord.x + 1; x++)
            for (int y = coord.y - 1; y <= coord.y + 1; y++)
                if (inRange(around = new Coord(x, y)))
                    if (!around.equals(coord))
                        list.add(around);
        return list;
    }


}
