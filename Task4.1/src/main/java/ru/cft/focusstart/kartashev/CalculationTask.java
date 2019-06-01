package ru.cft.focusstart.kartashev;

class CalculationTask {
    private int startDigit;
    private int endDigit;
    private double result;

    CalculationTask(int startDigit, int endDigit) {
        this.startDigit = startDigit;
        this.endDigit = endDigit;
    }

    int getStartNumber() {
        return startDigit;
    }

    int getEndNumber() {
        return endDigit;
    }

    double getResult() {
        return result;
    }

    void setResult(double result) {
        this.result = result;
    }


}
