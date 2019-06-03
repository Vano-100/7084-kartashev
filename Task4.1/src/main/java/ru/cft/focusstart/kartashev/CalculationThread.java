package ru.cft.focusstart.kartashev;

public class CalculationThread implements Runnable {
    private CalculationTask task;
    private long  result = 0;
    boolean isReady = false;

    CalculationThread(CalculationTask task) {
        this.task = task;
    }

    private long function(double number) {
        double result = 0;
        for (int i = 0; i < 15; i++) {
            result = Math.cbrt(Math.tan(Math.cbrt(((number % 2 * 100)/4)) +
                    Math.sqrt(number))%2 +
                    Math.hypot(number, number));
        }
        return (long)result;
    }

    @Override
    public void run() {
        for (int i = task.getStartNumber(); i < task.getEndNumber(); i++) {
            result += function(i);
        }
        task.setResult(result);
        isReady = true;
    }

    long getResult() {
        return task.getResult();
    }
}
