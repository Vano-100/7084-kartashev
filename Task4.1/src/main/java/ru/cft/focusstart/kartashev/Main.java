package ru.cft.focusstart.kartashev;

public class Main {
    private static final int COUNT_OF_THREADS = 4;
    private static final int NUMBER = 10000000;

    public static void main(String[] args) {
        try {
            calculateInOneThread();
            calculateInSeveralThreads();
        } catch (InterruptedException e) {
            System.err.println("Поток был прерван, работа программы не может быть продолжена");
        } catch (Exception e) {
            System.err.println("Неизвестная ошибка, работа программы не может быть продолжена");
        }
    }

    private static void calculateInOneThread() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        CalculationThread calculationThread = new CalculationThread(new CalculationTask(0, NUMBER));
        Thread thread = new Thread(calculationThread, "MonoThread");
        thread.start();
        thread.join();
        System.out.printf("Сумма вычислений 1 потока = %d\n", calculationThread.getResult());
        System.out.printf("Затраченное время = %d\n", (System.currentTimeMillis() - startTime) / 1000);
    }

    private static void calculateInSeveralThreads() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Calculator calculator = new Calculator(COUNT_OF_THREADS, NUMBER);
        System.out.printf("Сумма вычислений %d потоков = %d\n", COUNT_OF_THREADS, calculator.getTotalResult());
        System.out.printf("Затраченное время = %d секунд", (System.currentTimeMillis() - startTime) / 1000);
    }

}
