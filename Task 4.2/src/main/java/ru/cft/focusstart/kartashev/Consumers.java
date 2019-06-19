package ru.cft.focusstart.kartashev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Consumers {
    private final int CONSUMERS_QUANTITY = 3;
    private ExecutorService service = Executors.newFixedThreadPool(CONSUMERS_QUANTITY);


    void startConsumption() {
        for (int i = 0; i < CONSUMERS_QUANTITY; i++) {
            service.submit(new Consumer());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
