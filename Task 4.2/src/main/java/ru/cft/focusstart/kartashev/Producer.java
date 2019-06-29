package ru.cft.focusstart.kartashev;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    private static AtomicInteger nextId = new AtomicInteger(0);
    private int id;
    private boolean isWorking = true;

    Producer() {
        id = nextId.getAndIncrement();
    }

    @Override
    public void run() {
        while (true) {
            if (!Stock.isFull()) {
                if (!isWorking) {
                    isWorking = true;
                    System.out.printf(new Date() + "| Производитель с ID = %d возобновляет работу\n", id);
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
                if (isWorking) {
                    System.out.printf(new Date() + "| Производитель с ID = %d ожидает освобождение склада\n", id);
                }
                isWorking = false;
            }
        }
    }
}
