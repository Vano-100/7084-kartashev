package ru.cft.focusstart.kartashev;

public class Rectangle extends Shape {
    private ShapeType shapeType = ShapeType.Rectangle;
    private double width;
    private double height;

    Rectangle(double side1, double side2) {
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
        info += shapeType + "\nArea = " + getArea() + "\nPerimeter = " + getPerimeter() + "\nDiagonal = "
                + getDiagonalLength() + "\nWidth = " + width + "\nHeight = " + height;
        return info;
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
