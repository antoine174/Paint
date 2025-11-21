package models;

import lombok.Data;

@Data
public class Line extends Shape {

    private int[] points;

    public Line() {
        setClassName("line");
        this.points=new int[]{50, 50, 250, 50};
    }

    public Line(double x, double y, int[] points) {
        this();
        setX(x);
        setY(y);
        this.points=points;
    }
}