package shapes.m12d06;

public class Circle extends AShape implements IShape//allows to inherit area from class
{
  private double radius;
  public Circle(double _radius)
  {
    radius = _radius;
  }
  public double area()
  {
    return Math.PI*radius*radius;
  }
  public double perimeter()
  {
    return Math.PI*2*radius;
  }
  public double diameter()
  {
    return 2*radius;
  }
  public String toString()
  {
    return "Circle(" + radius + ")";
  }
}