package ru.cft.focusstart.kartashev;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;



class Stock {
    static AtomicBoolean isFull = new AtomicBoolean(false);
    static AtomicBoolean isEmpty = new AtomicBoolean(true);
    private static final int CAPACITY = 10;
    private static ConcurrentLinkedQueue<Product> products = new ConcurrentLinkedQueue<>();

    static synchronized void addProduct(Product product, int producerId) {
        int size;
        if ((size = products.size()) < CAPACITY) {
            products.add(product);
            System.out.printf(new Date() + "| Продукт с ID = %d помещен на склад производителем с ID = %d\n", product.getId(), producerId);
            isEmpty.set(false);
            System.out.println("На складе " + (++size) + " продуктов");
        }
        if (size == CAPACITY) {
            if (isFull.get()) {
                System.out.printf("Производитель с ID = %d пытался добавить товар в переполненный склад", producerId);
            } else {
                isFull.set(true);
                System.out.println("Склад переполнен");
            }
        }
    }

    static synchronized Product getProduct(int consumerId) {
        int size = products.size();
        if (size == 1) {
            isEmpty.set(true);
        }

        if (isFull.get()) {
            isFull.set(false);
        }
        Product product = products.poll();
        if (product != null) {
            System.out.printf(new Date() + "| Продукт с ID = %d взят со склада потребителем с ID = %d\n", product.getId(), consumerId);
        } else {
            System.out.printf(new Date() + "| Производитель с ID = %d пытался получить продукт с пустого склада", consumerId);
        }
        return product;
    }

}
