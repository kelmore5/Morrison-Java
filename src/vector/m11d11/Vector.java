package vector.m11d11;

public class Vector
{
  private double a;
  private double b;
  //Basic Constructor
  public Vector(double _a, double _b)
  {
    a = _a;
    b = _b;
    
  }
  // makes vectors print prettily instead of giving you Vector@351353
  public String toString()
  {
    if(b >= 0)
    {
      return "" + a + "i + " + b + "j"; 
    }
    return "" + a + "i - " + (-b) + "j";
  }
  public double magnitude()
  {
    return Math.sqrt(a*a + b*b);
  }
  //find unit vector in same direction as this
  public Vector direction()
  {
    double d = magnitude();
    return new Vector(a/d, b/d);
  }
  //angle with postive x direction
  public double argument()
  {
    return Math.acos(b/magnitude());
  }
}