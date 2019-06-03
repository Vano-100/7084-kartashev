package ru.cft.focusstart.kartashev;

class CalculationTask {
    private int startNumber;
    private int endNumber;
    private long result;

    CalculationTask(int startNumber, int endNumber) {
        this.startNumber = startNumber;
        this.endNumber = endNumber;
    }

    int getStartNumber() {
        return startNumber;
    }

    int getEndNumber() {
        return endNumber;
    }

    void setResult(long result) {
        this.result = result;
    }

    long getResult() {
        return result;
    }

}
