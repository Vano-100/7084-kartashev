package ru.cft.focusstart.kartashev;

public class Circle extends Shape {
    private ShapeType shapeType = ShapeType.Circle;
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String getInfo() {
        info += shapeType + "\nArea = " + getArea() + "\nPerimeter = " + getPerimeter() + "\nRadius = "
                + String.format("%.2f", radius) + "\nDiameter = " + String.format("%.2f", radius * 2);
        return info;
    }

    private String getArea() {
        double area = radius * radius * Math.PI;
        return (String.format("%.2f", area));
    }

    private String getPerimeter() {
        double perimeter = 2 * Math.PI * radius;
        return String.format("%.2f", perimeter);
    }

}
