package models;

import lombok.Data;

@Data
public class Square extends Shape {

    private double width;
    private double height;

    public Square() {
        setClassName("square");
    }

    public Square(double x, double y, double size) {
        this();
        setX(x);
        setY(y);
        this.width=size;
        this.height=size;
    }
}