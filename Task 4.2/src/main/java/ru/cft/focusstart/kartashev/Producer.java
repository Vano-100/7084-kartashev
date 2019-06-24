package ru.cft.focusstart.kartashev;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private final int STOCK_OVERFLOW_WAITING_TIME = 10000;
    private static AtomicInteger nextId = new AtomicInteger(0);
    private int id;
    private boolean isWorking = true;


    Producer() {
        id = nextId.getAndIncrement();
    }

    @Override
    public void run() {
        while (true) {
            if (!Stock.isFull.get()) {
                if (!isWorking) {
                    isWorking = true;
                    System.out.printf(new Date() + "| Производитель с ID = %d возмобновляет работу\n", id);
                }
                Product product = new Product();
                System.out.printf(new Date() + "| Продукт с ID = %d выпущен производителем с ID = %d\n", product.getId(), id);
                Stock.addProduct(product, id);
                try {
                    Thread.sleep(Producers.PRODUCTION_TIME);
                } catch (InterruptedException e) {
                    System.out.printf(new Date() + "| Производитель с ID %d был прерван\n", id);
                }
            } else {
                try {
                    isWorking = false;
                    System.out.printf(new Date() + "| Производитель с ID = %d ожидает освобождение склада\n", id);                    Thread.sleep(STOCK_OVERFLOW_WAITING_TIME);

                } catch (InterruptedException e) {
                    System.out.printf(new Date() + "| Производитель с ID %d был прерван\n", id);
                }
            }
        }
    }
}
