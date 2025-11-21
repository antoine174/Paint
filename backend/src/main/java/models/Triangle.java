package models;

import lombok.Data;

@Data
public class Triangle extends Shape {

    private double sides=3;
    private double radius=60;
    public Triangle() {
        setClassName("triangle");
    }

    public Triangle(double x, double y, double radius) {
        this();
        setX(x);
        setY(y);
        this.radius=radius;
    }
}