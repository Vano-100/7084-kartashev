package ru.cft.focusstart.kartashev.shapes;

public class Triangle extends Shape {

    private double AB;
    private double BC;
    private double CA;
    private double perimeter;

    @Override
    public ShapeType getShapeType() {
        return ShapeType.Треугольник;
    }

    public Triangle(double AB, double BC, double CA) {
        this.AB = AB;
        this.BC = BC;
        this.CA = CA;
        this.perimeter = AB + BC + CA;
    }

    @Override
    public String getInfo() {
        return "Тип фигуры: " + getShapeType() + "\nПлощадь = " + getArea() + "\nПериметр = " + String.format("%.2f", perimeter) + "\nУглы: \n"
                + getAngles();
    }

    private String getArea() {
        double semiPerimeter = perimeter / 2;
        double area = Math.sqrt(semiPerimeter * (semiPerimeter - AB) * (semiPerimeter - BC) * (semiPerimeter - CA));
        return String.format("%.2f", area);
    }

    private String getAngles() {
        String format = "; %.2f";
        return "AB = " + AB + String.format(format, calculateAngle(BC, CA, AB)) + "\nBC = " + BC + String.format(format, calculateAngle(AB, CA, BC))
                + "\nCA = " + CA + String.format(format, calculateAngle(AB, BC, CA));
    }

    private double calculateAngle(double side1, double side2, double currentSide) {
        return Math.acos((side1 * side1 + side2 * side2 - (currentSide * currentSide)) / (2 * side1 * side2)) * (180 / Math.PI);
    }
}
