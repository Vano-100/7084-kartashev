package ru.cft.focusstart.kartashev;

public class CalculationThread implements Runnable {
    private CalculationTask task;
    private double  result = 0;
    boolean isReady = false;

    CalculationThread(CalculationTask task) {
        this.task = task;
    }

    private double function(double number) {
        double result = 0;
        for (int i = 0; i < 15; i++) {
            result = Math.cbrt(Math.tan(Math.cbrt(((number % 2 * 100)/4)) +
                    Math.sqrt(number))%2 +
                    Math.hypot(number, number));
            //10000000 чисел считает 32 секунды
        }
        return result;
    }

    @Override
    public void run() {
        for (int i = task.getStartNumber(); i < task.getEndNumber(); i++) {
            result += function(i);
        }
        task.setResult(result);
        isReady = true;
    }
}
