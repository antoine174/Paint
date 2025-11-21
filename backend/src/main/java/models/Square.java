package models;

public class Square extends Shape {

    public double width;
    public double height;

    public Square() {
        this.className="square";
    }

    public Square(double x, double y, double size) {
        this();
        this.x=x;
        this.y=y;
        this.width=size;
        this.height=size;
    }
}