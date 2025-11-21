package models;

import lombok.Data;

@Data
public class Ellipse extends Shape {

    private double radiusX;
    private double radiusY;

    public Ellipse() {
        setClassName("ellipse");
        this.radiusX=50;
        this.radiusY=50;
    }

    public Ellipse(double x, double y, double radiusX, double radiusY) {
        this();
        setX(x);
        setY(y);
        this.radiusX=radiusX;
        this.radiusY=radiusY;
    }
}