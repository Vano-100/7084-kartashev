package ru.cft.focusstart.kartashev;

import ru.cft.focusstart.kartashev.exceptions.IncorrectInputValueException;
import ru.cft.focusstart.kartashev.printers.ConsolePrinter;
import ru.cft.focusstart.kartashev.printers.FilePrinter;
import ru.cft.focusstart.kartashev.printers.IPrinter;
import ru.cft.focusstart.kartashev.shapes.Shape;

public class Main {
    public static void main(String[] args) {
        try {
            Parser parser = new Parser(args[0]);
            IPrinter printer;
            if (args.length == 1) {
                printer = new ConsolePrinter();
            } else if (args.length == 2) {
                printer = new FilePrinter(args[1]);
            } else {
                throw new IndexOutOfBoundsException();
            }
            try {
                Shape inputShape = parser.getInputShape();
                if (inputShape != null) {
                    printer.print(inputShape.getInfo());
                }
            } catch (IncorrectInputValueException e) {
                printer.print(e.getMessage());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Входные параметры - имя входного файла (обязательный параметр), " +
                    "имя выходного файла (необязательный параметр), передаются через агрументы командной строки.");
        }

    }
}
