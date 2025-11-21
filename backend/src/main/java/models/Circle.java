package models;

public class Circle extends Shape {

    public double radius;

    public Circle() {
        this.className="circle";
        this.radius=100;
    }

    public Circle(double x, double y, double radius) {
        this();
        this.x=x;
        this.y=y;
        this.radius=radius;
    }
}