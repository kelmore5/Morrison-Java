package complex;

public final class Complex
{
  private double x;
  private double y;
  /**
   * This is the general purpose constructor that creats a Complex number 
   * from two doubles by initializing the state variables x and y.
   * @param _x specifies the real part 
   * @param _y specified the imaginary part
   */
  public Complex(double _x, double _y)
  {
    x = _x;
    y = _y;
  }
  /**
   * This promotes a double to a Complex by initializing the state variable x.
   * @param _x the double to be promoted
   */
  public Complex(double _x)
  {
    x = _x;
  }
  /*
   * This creates 0 + i*0, the default Complex number.
   */
  public Complex()
  {
    x = 0;
    y = 0;
  }
  /**
   * This represents a complex number as x + iy and handles
   * the case of a negative y gracefully.
   * 
   */
  public String toString()
  {
    if(y < 0)
    {
      return x + " - i*" + -y;
    }
    return x + " + i*" + y;
  }
  /**
   * returns true if imaginary and real parts match
   * @param that another complex number the Complex number is being compared to.
   */
  public boolean equals(Complex that)
  {
   if(x == that.x && y == that.y)
   {
     return true;
   }
   return false; 
  }
  /**
   * This implements complex addition
   * @param that a second complex number
   * @return this + that
   */
  public Complex add(Complex that)
  {
    return new Complex(x + that.x, y + that.y);
  }
  /**
   * This implements complex subtraction
   * @param that a second complex number
   * @return this - that
   */
  public Complex subtract(Complex that)
  {
    return new Complex(x - that.x, y - that.y);
  }
  /**
   * This implements complex multiplication
   * @param that a second complex number
   * @return this * that
   */
  public Complex multiply(Complex that)
  {
    return new Complex(x*that.x+y*that.y, x*that.y + y*that.x);
  }
  /**
   * This implements complex division
   * @param that a second complex number
   * @return this / that
   */
  public Complex divide(Complex that)
  {
    Complex factor = new Complex(that.x, -that.y);
    Complex num = this.multiply(factor);
    Complex denom = that.multiply(factor);
    return new Complex(num.x/denom.x, num.y/denom.x);
  }
  /**
   * This implements raising a complex to a power
   * @param n an integer that the Complex number is being raised to
   * @return this^n
   */
  public Complex power(int n)
  {
    Complex power = this;
    while(n > 1)
    {
      power = power.multiply(this);
      n -= 1;
    }
    return power;
  }
  /**
   * This gets the conjugate of a Complex number
   * @return new Complex that is the conjugate of the one entered
   */
  public Complex conjugate()
  {
    y = -y;
    return new Complex(x, y);
  }
  /**
   * This returns the absolute value of a Complex number
   * @return x*x + y*y
   */
  public double abs()
  {
    return x*x + y*y;
  }
  /*
   * This returns the real value of a Complex number
   * @return the real value, x
   */
  public double re()
  {
    return x;
  }
  /*
   * This returns the imaginary value of a Complex number
   * @return the imaginary value, y
   */
  public double im()
  {
    return y;
  }
  
}