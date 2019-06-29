package ru.cft.focusstart.kartashev;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

class Stock {

    private static final int CAPACITY = 3;
    private static ArrayBlockingQueue<Product> products = new ArrayBlockingQueue<>(CAPACITY, true);

    static synchronized boolean isFull() {
        return products.size() == CAPACITY;
    }

    static synchronized boolean isEmpty() {
        return products.size() == 0;
    }

    static void addProduct(Product product, int producerId) {
        try {
            products.put(product);
        } catch (InterruptedException e) {
            System.out.printf(new Date() + "| Производитель с ID = %d был прерван во время помещения продукта на склад\n", producerId);
        }
        System.out.printf(new Date() + "| Продукт с ID = %d помещен на склад производителем с ID = %d\n", product.getId(), producerId);
    }

    static Product getProduct(int consumerId) {
        Product product = null;
        try {
            product = products.take();
        } catch (InterruptedException e) {
            System.out.printf(new Date() + "| Потребитель с ID = %d был прерван во время получения продукта\n", consumerId);
        }
        if (product != null) {
            System.out.printf(new Date() + "| Продукт с ID = %d взят со склада потребителем с ID = %d\n", product.getId(), consumerId);
        }
        return product;
    }

}
