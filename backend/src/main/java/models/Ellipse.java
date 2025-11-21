package models;

// Correcting diagram name "Eclipse" to standard "Ellipse"
public class Ellipse extends Shape {

    public double radiusX;
    public double radiusY;

    public Ellipse() {
        this.className="ellipse";
        this.radiusX=50;
        this.radiusY=50;
    }

    public Ellipse(double x, double y, double radiusX, double radiusY) {
        this();
        this.x=x;
        this.y=y;
        this.radiusX=radiusX;
        this.radiusY=radiusY;
    }
}