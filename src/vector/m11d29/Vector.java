package vector.m11d29;

public class Vector
{
  //state variables
  private final double a;
  private final double b;
  //static constants
  
  public static final Vector ZERO;
  public static final Vector I;
  public static final Vector J;
  //this initializes any static state variables
  //this is executed once when the class is loaded
  static
  {
    ZERO = new Vector();
    I = new Vector(1,0);
    J = new Vector(0,1);
  }
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
  public double dot(Vector that)
  {
    return a*that.a + b*that.b;
  }
  public double angle(Vector that)
  {
    //return Math.acos(this.dot(that)/(this.magnitude()*that.magntude()));
    return 0;
  }
  public Vector add(Vector that)
  {
    return new Vector(a + that.a, b + that.b);
  }
  public Vector subtract(Vector that)
  {
    return new Vector(a - that.a, b - that.b);
  }
  public Vector scalerMultiply(double s)
  {
    return new Vector(a*s, b*s);
  }
  /*public void dilate(double s)
  {
    a *= s;
    b *= s;
  }
  */
}