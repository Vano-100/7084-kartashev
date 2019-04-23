package ru.cft.focusstart.kartashev;

public class Triangle extends Shape {

    private double AB;
    private double BC;
    private double CA;
    private double perimeter;
    private ShapeType shapeType = ShapeType.Triangle;

    Triangle(double AB, double BC, double CA) {
        this.AB = AB;
        this.BC = BC;
        this.CA = CA;
        this.perimeter = AB + BC + CA;
    }

    @Override
    public String getInfo() {
        String format = "%.2f";
        info += shapeType + "\nArea = " + getArea() + "\nPerimeter = " + String.format(format, perimeter) + "\nAngles: \n"
                + getAngles();
        return info;
    }

    private String getArea() {
        double p = perimeter / 2;
        double area = Math.sqrt(p * (p - AB) * (p - BC) * (p - CA));
        return String.format("%.2f", area);
    }

    private String getAngles() {
        String result;
        if (AB < BC + CA && BC < AB + CA && CA < AB + BC) {
            double alpha = Math.acos((BC * BC + CA * CA - (AB * AB)) / (2 * BC * CA)) * (180 / Math.PI);
            double beta = Math.acos((AB * AB + CA * CA - (BC * BC)) / (2 * AB * CA)) * (180 / Math.PI);
            double gamma = Math.acos((AB * AB + BC * BC - (CA * CA)) / (2 * AB * BC)) * (180 / Math.PI);
            String format = "; %.2f";
            result = "AB = " + AB + String.format(format, alpha) + "\nBC = " + BC + String.format(format, beta)
                    + "\nCA = " + CA + String.format(format, gamma);

        } else {
            result = "Input triangle is degenerate";
        }
        return result;
    }
}
