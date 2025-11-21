package models;

public class Triangle extends Shape {

    public double sides=3;
    public double radius=60;
    public Triangle() {
        this.className="triangle";
    }

    public Triangle(double x, double y, double radius) {
        this();
        this.x=x;
        this.y=y;
        this.radius=radius;
    }
}