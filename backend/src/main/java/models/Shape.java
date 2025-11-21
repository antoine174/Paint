package models;

public abstract class Shape {

    public String className;
    public int id;
    public double x;
    public double y;
    public double scaleX = 1.0;
    public double scaleY = 1.0;    public double rotation;
    public boolean dragabble = true;
    public double strokeWidth;
    public String fill;
    public String stroke;

    public Shape() {
        // Default constructor
    }
}