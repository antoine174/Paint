package models;

import lombok.Data;

@Data
public class Rect extends Shape {

    private double width;
    private double height;

    public Rect() {
        setClassName("rect");
        setStrokeWidth(2);
    }

    public Rect(double x, double y, double width, double height) {
        this();
        setX(x);
        setY(y);
        this.width=width;
        this.height=height;
    }
}