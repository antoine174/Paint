package models;

public class Rect extends Shape {

    public double width;
    public double height;

    public Rect() {
        this.className="rect";
        this.strokeWidth=2;
    }

    public Rect(double x, double y, double width, double height) {
        this();
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
}