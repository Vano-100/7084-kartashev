package ru.cft.focusstart.kartashev;

import java.util.ArrayList;
import java.util.List;


class Calculator {
    private int countOfThreads;
    private int number;
    private double totalResult = 0;
    private List<CalculationTask> tasks = new ArrayList<>();
    private List<CalculationThread> calculationThreads = new ArrayList<>();
    private int calculationPart;

    Calculator(int countOfThreads, int number) {
        this.countOfThreads = countOfThreads;
        this.number = number;
        calculationPart = number / countOfThreads;
        createTasks();
        createThreads();
    }

    private void createThreads() {
        for (CalculationTask task : tasks) {
            CalculationThread calculationThread = new CalculationThread(task);
            Thread thread = new Thread(calculationThread,"MyThread");
            calculationThreads.add(calculationThread);
            thread.start();
        }
    }

    private void createTasks() {
        for (int i = 0; i < countOfThreads; i++) {
            CalculationTask task;
            task = new CalculationTask(i * calculationPart, (i + 1) * calculationPart);
            tasks.add(task);
        }
    }

    private boolean isThreadsReady() {
        for (CalculationThread thread : calculationThreads) {
            if (thread.isReady) {
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }

    double getTotalResult() throws InterruptedException {
        double result = 0;
        while (true) {
            if (isThreadsReady()) {
                for (CalculationTask task : tasks) {
                    result += task.getResult();
                }
                return result;
            }
            else {
                Thread.sleep(1000);
            }
        }
    }
}
