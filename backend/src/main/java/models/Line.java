package models;

public class Line extends Shape {

    public int[] points;

    public Line() {
        this.className="line";
        this.points=new int[]{50, 50, 250, 50};
    }

    public Line(double x, double y, int[] points) {
        this();
        this.x=x;
        this.y=y;
        this.points=points;
    }
}