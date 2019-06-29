package ru.cft.focusstart.kartashev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Producers {

    static final int PRODUCTION_TIME = 2000;
    private final int PRODUCERS_QUANTITY = 2;
    private ExecutorService service = Executors.newFixedThreadPool(PRODUCERS_QUANTITY);

    void startProduction() {
        for (int i = 0; i < PRODUCERS_QUANTITY; i++) {
            service.submit(new Producer());
        }
    }

}

