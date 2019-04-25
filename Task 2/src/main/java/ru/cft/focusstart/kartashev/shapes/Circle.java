package ru.cft.focusstart.kartashev.shapes;

public class Circle extends Shape {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.Круг;
    }

    @Override
    public String getInfo() {
        return "Тип фигуры: " + getShapeType() + "\nПлощадь = " + getArea() + "\nПериметр = " + getPerimeter() + "\nРадиус = "
                + String.format("%.2f", radius) + "\nДиаметр = " + String.format("%.2f", radius * 2);
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
