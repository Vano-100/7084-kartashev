package ru.cft.focusstart.kartashev;

import java.util.ArrayList;
import java.util.List;

class Calculator {

    private List<CalculationThread> calculationThreads = new ArrayList<>();
    private int countOfThreads;
    private int calculationPart;

    Calculator(int countOfThreads, int number) {
        this.countOfThreads = countOfThreads;
        calculationPart = number / countOfThreads;
        createThreads();
    }

    private void createThreads() {
        for (int i = 0; i < countOfThreads; i++) {
            CalculationThread calculationThread = new CalculationThread
                    (new CalculationTask(i * calculationPart, (i + 1) * calculationPart));
            Thread thread = new Thread(calculationThread, "Thread");
            calculationThreads.add(calculationThread);
            thread.start();
        }
    }

    private boolean isThreadsReady() {
        for (CalculationThread calculationThread : calculationThreads) {
            if (!calculationThread.isReady) {
                return false;
            }
        }
        return true;
    }

    long getTotalResult() throws InterruptedException {
        long result = 0;
        while (true) {
            if (isThreadsReady()) {
                for (CalculationThread thread : calculationThreads) {
                    result += thread.getResult();
                }
                return result;
            } else {
                Thread.sleep(1000);
            }
        }
    }
}
