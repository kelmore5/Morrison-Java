package vector.m11d14;

public class Vector
{
  private double a;
  private double b;
  /**
   * This is the general purpose constructor for a vector.
   * @param _a this is the horizontal component
   * @param _b this is the vertical component
   */
  public Vector(double _a, double _b)
  {
    a = _a;
    b = _b;
    
  }
  /** 
   * This is a default constructor that makes a zero vector
   */
  public Vector()
  {
    this(0,0);
  }
  /**
   * makes vectors print prettily instead of giving you @ Vector@353225
   * @return a string representation of this vector
   */
  public String toString()
  {
    if(b >= 0)
    {
      return "" + a + "i + " + b + "j"; 
    }
    return "" + a + "i - " + (-b) + "j";
  }
  public boolean equals(Object that)
  {
    //species test
    if(! (that instanceof Vector))
    {
      return false;
    }//if it's not a vector, return false
    Vector v = (Vector) that;
    return (a == v.a) && (b == v.b);
  }
  /**
   * computes the length of this vector
   * @return the length of the verctor
   */
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
    double m = magnitude();
    if(b >= 0)
    {
    return Math.acos(b/m);
    }
    return -Math.acos(a/m);
  }
  public double angle(Vector that)
  {
    return 0; //TODO
  }
  public double dot(Vector that)
  {
    return a*that.a + b*that.b;
  }
}