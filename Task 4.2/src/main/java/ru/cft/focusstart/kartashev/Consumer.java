package ru.cft.focusstart.kartashev;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
    private static final int CONSUMPTION_TIME = 5000;
    private static AtomicInteger nextId = new AtomicInteger(0);
    private int id;

    Consumer() {
        id = nextId.getAndIncrement();
    }

    @Override
    public void run() {
        while (true) {
            if (!Stock.isEmpty.get()) {
                System.out.printf("Продукт с ID = %d взят со склада потребителем с ID = %d\n",
                        Stock.getProduct(this.id).getId(), this.id);
                try {
                    Thread.sleep(CONSUMPTION_TIME);
                } catch (InterruptedException e) {
                    System.out.printf("Потребитель с ID %d был прерван\n", id);
                }

            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.printf("Производитель с ID %d был прерван\n", id);
                }
            }
        }
    }
}
