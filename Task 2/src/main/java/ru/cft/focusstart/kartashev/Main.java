package ru.cft.focusstart.kartashev;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser(args[0]);
        IPrinter printer;
        Shape inputShape = parser.getInputShape();
        if (args.length == 1) {
            printer = new ConsolePrinter();
            printer.print(inputShape.getInfo());
        }
        if (args.length == 2) {
            printer = new FilePrinter(args[1]);
            printer.print(inputShape.getInfo());
        }
    }
}
