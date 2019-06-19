package ru.cft.focusstart.kartashev;


public class Main {
    public static void main(String[] args) {
        Producers producers = new Producers();
        Consumers consumers = new Consumers();
        producers.startProduction();
        consumers.startConsumption();
    }
}
