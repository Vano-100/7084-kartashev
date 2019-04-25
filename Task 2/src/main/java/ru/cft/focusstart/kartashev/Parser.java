package ru.cft.focusstart.kartashev;

import ru.cft.focusstart.kartashev.exceptions.IncorrectInputValueException;
import ru.cft.focusstart.kartashev.shapes.Circle;
import ru.cft.focusstart.kartashev.shapes.Rectangle;
import ru.cft.focusstart.kartashev.shapes.Shape;
import ru.cft.focusstart.kartashev.shapes.Triangle;

import java.io.*;

class Parser {
    private String inputFileName;
    private Shape shape;

    Parser(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    Shape getInputShape() throws IncorrectInputValueException {


        try (FileInputStream fstream = new FileInputStream(inputFileName)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                switch (strLine) {
                    case "CIRCLE": {
                        strLine = br.readLine();
                        double radius = Double.parseDouble(strLine);
                        if (radius <= 0) {
                            throw new IncorrectInputValueException("Радиус должен быть положительным числом");
                        }
                        shape = new Circle(radius);
                        break;
                    }
                    case "RECTANGLE": {
                        strLine = br.readLine();
                        String[] sides = strLine.split(" ");
                        if (sides.length != 2) {
                            throw new IncorrectInputValueException("Необходимо указать две стороны прямоугольника через пробел");
                        }
                        double side1 = Double.parseDouble(sides[0]);
                        double side2 = Double.parseDouble(sides[1]);
                        if (side1 <= 0 || side2 <= 0) {
                            throw new IncorrectInputValueException("Стороны прямоугольника должны быть положительными");
                        }
                        shape = new Rectangle(side1, side2);
                        break;
                    }
                    case "TRIANGLE": {
                        strLine = br.readLine();
                        String[] sides = strLine.split(" ");
                        if (sides.length != 3) {
                            throw new IncorrectInputValueException("Необходимо указать три стороны треугольника через пробел");
                        }
                        double side1 = Double.parseDouble(sides[0]);
                        double side2 = Double.parseDouble(sides[1]);
                        double side3 = Double.parseDouble(sides[2]);
                        if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
                            throw new IncorrectInputValueException("Стороны треугольника должны быть положительными");
                        }
                        if (side1 > side2 + side3 || side2 > side1 + side3 || side3 > side1 + side2) {
                            throw new IncorrectInputValueException("Введенный треугольник вырожденный.\n" +
                                    "Сумма длин любых двух сторон треугольника должна быть больше длины третьей стороны");
                        }
                        shape = new Triangle(side1, side2, side3);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Входной файл не обнаружен.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            throw new IncorrectInputValueException("Параметры фигур необходимо указывать в формате \"3\" или \"3.3\"");
        }
        return shape;
    }
}
