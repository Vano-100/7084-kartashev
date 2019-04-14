package ru.cft.focusstart;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        int tableSize = 0;
        try {
            tableSize = readTableSize();
        }
        catch (InputMismatchException ex){
            System.out.println("Необходимо ввести целое число от 1 до 32");
        }
        if (tableSize != 0) {
            System.out.println(getMultiplicationTable(tableSize));
        }
    }

    private static String getMultiplicationTable(int tableSize) {
        String multiplicationTable = "";
        int cellSymbolsCount = String.valueOf(tableSize * tableSize).length() + 1;
        String format = "%" + cellSymbolsCount +"d";
        String linePart = "-";
        linePart = linePart.repeat(cellSymbolsCount);

        for (int i = 1; i < tableSize+1; i++) {
            String line = "";
            for (int j = 1; j < tableSize+1; j++) {
                multiplicationTable +=  String.format(format, i * j ) + "|";
                line += linePart + "+";
            }
            multiplicationTable += "\n" + line + "\n";
        }
        return multiplicationTable;
    }

    private static int readTableSize () throws InputMismatchException
    {
        int tableSize = 0;
        tableSize = new Scanner(System.in).nextInt();

        if (tableSize > 0 && tableSize < 33) {
            return tableSize;
        }
        else {
            throw new InputMismatchException();
        }
    }
}
