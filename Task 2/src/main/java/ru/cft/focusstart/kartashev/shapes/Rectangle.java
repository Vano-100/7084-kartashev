package ru.cft.focusstart.kartashev.shapes;

public class Rectangle extends Shape {
    @Override
    public ShapeType getShapeType() {
        return ShapeType.Прямоугольник;
    }

    private double width;
    private double height;

    public Rectangle(double side1, double side2) {
        if (side1 < side2) {
            width = side1;
            height = side2;
        } else {
            width = side2;
            height = side1;
        }
    }

    @Override
    public String getInfo() {
        return "Тип фигуры: " + getShapeType() + "\nПлощадь = " + getArea() + "\nПериметр = " + getPerimeter() + "\nДиагональ = "
                + getDiagonalLength() + "\nШирина = " + width + "\nДлина = " + height;
    }

    private String getArea() {
        double area = height * width;
        return (String.format("%.2f", area));
    }

    private String getPerimeter() {
        double perimeter = height + width;
        return (String.format("%.2f", perimeter));
    }

    private String getDiagonalLength() {
        double diagonal = Math.sqrt(height * height + width * width);
        return (String.format("%.2f", diagonal));
    }
}
