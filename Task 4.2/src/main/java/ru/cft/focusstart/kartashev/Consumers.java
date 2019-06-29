package ru.cft.focusstart.kartashev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Consumers {

    static final int CONSUMPTION_TIME = 3000;
    private final int CONSUMERS_QUANTITY = 5;
    private ExecutorService service = Executors.newFixedThreadPool(CONSUMERS_QUANTITY);

    void startConsumption() {
        for (int i = 0; i < CONSUMERS_QUANTITY; i++) {
            service.submit(new Consumer());
        }
    }
}
