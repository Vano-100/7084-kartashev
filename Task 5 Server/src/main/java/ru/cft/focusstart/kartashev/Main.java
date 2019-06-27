package ru.cft.focusstart.kartashev;

public class Main {

    public static void main(String[] args) {

        try {
            new Server();
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка: " + e.getMessage());
        }
    }
}
