package ru.cft.focusstart.kartashev;

public class ConsolePrinter implements IPrinter {
    @Override
    public void print(String text) {
        System.out.println(text);
    }
}
