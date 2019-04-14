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
        int cellSymbolsCount = String.valueOf(tableSize * tableSize).length();
        String format = "%" + cellSymbolsCount +"d";
        String lineSeparatorPart = "-";
        lineSeparatorPart = lineSeparatorPart.repeat(cellSymbolsCount);

        for (int i = 1; i < tableSize+1; i++) {
            String lineSeparator = "";
            for (int j = 1; j < tableSize+1; j++) {
                multiplicationTable +=  String.format(format, i * j ) + "|";
                if (j == tableSize){
                    lineSeparator += lineSeparatorPart;
                    continue;
                }

                lineSeparator += lineSeparatorPart + "+";
            }
            multiplicationTable += "\n" + lineSeparator + "\n";
        }
        return multiplicationTable;
    }

    private static int readTableSize() throws InputMismatchException
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
