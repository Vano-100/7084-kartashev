package ru.cft.focusstart.kartashev.printers;

import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements IPrinter {
    private String outputFileName;

    public FilePrinter(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    @Override
    public void print(String text) {
        try (FileWriter writer = new FileWriter(outputFileName, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
