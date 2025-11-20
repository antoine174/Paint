package models;

public abstract class Shape {
  
    public double strokeWidth;
    public String strokeColor;
    public int id;
    public String type;
    public double x;
    public double y;
    public double length;
    public double width;
    public String fill;

    public Shape(double x,double y) {
        this.strokeColor ="black";
        this.x=x;
        this.y=y;
    }


//    public abstract void move(double Offsetx,double Offsety);
//    public abstract void copy(Shape origin );
//    public abstract void delete(Shape shape );
//    public abstract void fill(Shape shape,String color );
//    public abstract void resize(Shape shape,double factor );
//    public abstract void changeStrokeColor(Shape shape,String color );
//    public abstract void changeStrokeWidth(Shape shape,double strokeWidth );
}