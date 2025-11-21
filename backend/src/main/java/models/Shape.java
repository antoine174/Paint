package models;

import lombok.Data;

@Data
public abstract class Shape {

    private String className;
    private double x;
    private double y;
    private double scaleX = 1.0;
    private double scaleY = 1.0;    public double rotation;
    private boolean dragabble = true;
    private double strokeWidth;
    private String fill;
    private String stroke;

    public Shape() {
    }
}