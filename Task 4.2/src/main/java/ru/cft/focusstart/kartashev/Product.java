package ru.cft.focusstart.kartashev;

import java.util.concurrent.atomic.AtomicInteger;

class Product {
    private static AtomicInteger nextId = new AtomicInteger(0);
    private int id;

    Product() {
        id = nextId.getAndIncrement();
    }

    int getId() {
        return id;
    }
}
