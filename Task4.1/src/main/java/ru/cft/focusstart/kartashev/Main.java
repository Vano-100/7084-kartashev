package ru.cft.focusstart.kartashev;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int number = 10000000;
        double result = 0;
        Calculator calculator = new Calculator(4, number);
        System.out.println(calculator.getTotalResult());
//        for (int i = 0; i < number; i++) {
//            result += function(i);
//        }
//        System.out.println(result);
        System.out.println((System.currentTimeMillis() - startTime)/1000);
    }


    private static double function(double number) {
        double result = 0;
        for (int i = 0; i < 15; i++) {
            result = Math.cbrt(Math.tan(Math.cbrt(((number % 2 * 100)/4)) +
                    Math.sqrt(number))%2 +
                    Math.hypot(number, number));
            //10000000 чисел считает 32 секунды

        }
        return result;
    }

//1.8137032606520133E9 - 4 потока для 10000000
//1.813703260652071E9 - 1 поток для 10000000
}


