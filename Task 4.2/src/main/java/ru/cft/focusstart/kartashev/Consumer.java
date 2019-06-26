package ru.cft.focusstart.kartashev;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
    private static final int CONSUMPTION_TIME = 3000;
    private static AtomicInteger nextId = new AtomicInteger(0);
    private int id;
    private boolean isConsuming = true;

    Consumer() {
        id = nextId.getAndIncrement();
    }

    @Override
    public void run() {
        while (true) {
            if (!Stock.isEmpty.get()) {
                if (!isConsuming) {
                    isConsuming = true;
                    System.out.printf(new Date() + "| Потребитель с ID = %d возобновляет работу\n", id);
                }
                System.out.printf(new Date() + "| Продукт с ID = %d потреблен потребителем с ID = %d\n",
                        Stock.getProduct(this.id).getId(), this.id);
                try {
                    Thread.sleep(CONSUMPTION_TIME);
                } catch (InterruptedException e) {
                    System.out.printf(new Date() + "| Потребитель с ID %d был прерван\n", id);
                }

            } else {
                try {
                    if (isConsuming) {
                        System.out.printf(new Date() + "| Потребитель с ID = %d ожидает пополнения склада\n", id);
                    }
                    isConsuming = false;
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.printf(new Date() + "| Потребитель с ID %d был прерван\n", id);
                }
            }
        }
    }
}
