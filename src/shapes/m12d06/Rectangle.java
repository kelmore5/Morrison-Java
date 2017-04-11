package shapes.m12d06;

public class Rectangle extends AShape implements IShape
{
  private double width;
  private double height;
  public Rectangle(double _width, double _height)
  {
    width = _width;
    height = _height;
  }
  public double area()
  {
    return width*height;
  }
  public double perimeter()
  {
    return 2*(width+height);
  }
  public double diameter()
  {
    return Math.hypot(width, height);
  }
  public String toString()
  {
    return "Rectangle(" + width + ", " + height + ")";
  }
}