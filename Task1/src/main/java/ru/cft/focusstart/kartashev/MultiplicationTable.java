package ru.cft.focusstart.kartashev;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiplicationTable {

    private static final int MIN_TABLE_SIZE = 1;
    private static final int MAX_TABLE_SIZE = 32;

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
        StringBuilder multiplicationTable = new StringBuilder();
        int cellSymbolsCount = String.valueOf(tableSize * tableSize).length();
        String format = "%" + cellSymbolsCount +"d";
        String lineSeparatorPart = "-".repeat(cellSymbolsCount);

        for (int i = 1; i < tableSize+1; i++) {
            StringBuilder lineSeparator = new StringBuilder();
            for (int j = 1; j < tableSize+1; j++) {
                if (j == tableSize){
                    multiplicationTable.append(String.format(format, i * j));
                    lineSeparator.append(lineSeparatorPart);
                } else {
                    multiplicationTable.append(String.format(format, i * j)).append("|");
                    lineSeparator.append(lineSeparatorPart).append("+");
                }
            }
            multiplicationTable.append("\n").append(lineSeparator).append("\n");
        }
        return multiplicationTable.toString();
    }

    private static int readTableSize() throws InputMismatchException {
        int tableSize;
        tableSize = new Scanner(System.in).nextInt();

        if (tableSize >= MIN_TABLE_SIZE && tableSize <= MAX_TABLE_SIZE) {
            return tableSize;
        }
        else {
            throw new InputMismatchException();
        }
    }
}
