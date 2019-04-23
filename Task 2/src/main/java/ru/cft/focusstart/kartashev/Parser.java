package ru.cft.focusstart.kartashev;

import java.io.*;

class Parser {
    private String inputFileName;
    private Shape shape;

    Parser(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    Shape getInputShape() {

        try {
            FileInputStream fstream = new FileInputStream(inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                switch (strLine) {
                    case "CIRCLE": {
                        strLine = br.readLine();
                        double radius = Double.parseDouble(strLine);
                        shape = new Circle(radius);
                        break;
                    }
                    case "RECTANGLE": {
                        strLine = br.readLine();
                        String[] sides = strLine.split(" ");
                        shape = new Rectangle(Double.parseDouble(sides[0]), Double.parseDouble(sides[1]));
                        break;
                    }
                    case "TRIANGLE": {
                        strLine = br.readLine();
                        String[] sides = strLine.split(" ");
                        shape = new Triangle(Double.parseDouble(sides[0]), Double.parseDouble(sides[1]), Double.parseDouble(sides[2]));
                        break;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Input file not found");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return shape;
    }
}
