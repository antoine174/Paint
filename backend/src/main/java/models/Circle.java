package models;

import lombok.Data;

@Data
public class Circle extends Shape {

    private double radius;

    public Circle() {
        setClassName("circle");
        this.radius=100;
    }

    public Circle(double x, double y, double radius) {
        this();
        setX(x);
        setY(y);
        this.radius=radius;
    }
}