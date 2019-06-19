package ru.cft.focusstart.kartashev;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;



class Stock {
    static AtomicBoolean isFull = new AtomicBoolean(false);
    static AtomicBoolean isEmpty = new AtomicBoolean(true);
    private static final int CAPACITY = 10;
    private static ConcurrentLinkedQueue<Product> products = new ConcurrentLinkedQueue<>();

    static synchronized void addProduct(Product product, int id) {
        int size;
        if ((size = products.size()) < CAPACITY) {
            products.add(product);
            isEmpty.set(false);
            System.out.println("На складе " + (++size) + " продуктов");
        }
        if (size == CAPACITY) {
            if (isFull.get()) {
                System.out.printf("Производитель с ID = %d пытался добавить товар в переполненный склад", id);
            } else {
                isFull.set(true);
                System.out.println("Склад переполнен");
            }
        }
    }

    static synchronized Product getProduct(int id) {
        int size = products.size();
        if (size == 1) {
            isEmpty.set(true);
        }
        if (size == 0) {
            System.out.printf("Потребитель с ID = %d пытался получить товар с пустого склада\n", id);
        }
        isFull.set(false);
        return products.poll();
    }

}
