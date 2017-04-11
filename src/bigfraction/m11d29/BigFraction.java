package bigfraction.m11d29;

import java.math.BigInteger;

public class BigFraction
{
  private BigInteger num;
  private BigInteger denom;
  public static final BigFraction ONE;
  public static final BigFraction ZERO;
  static //called on loading of class
  {
    ONE = new BigFraction(1,1);
    ZERO = new BigFraction(0,1);
  }
  public BigFraction(BigInteger _num, BigInteger _denom)
  {
    if(_denom.equals(BigInteger.ZERO))
    {
      throw new IllegalArgumentException();
    }
    if(_denom.compareTo(BigInteger.ZERO) < 0)
    {
      _num = _num.negate();
      _denom = _denom.negate();
    }
    BigInteger d = _num.gcd(_denom);
    num = _num.divide(d);
    denom = _denom.divide(d);
  }
  public BigFraction(int _num, int _denom)
  {
    this(BigInteger.valueOf(_num), BigInteger.valueOf(_denom));
  }
  public BigFraction()
  {
    this(0,1);
  }
  public String toString()
  {
    if(!denom.equals(BigInteger.ONE))
    {
      return "" + num + "/" + denom;
    }
    return "" + num;
  }
  public boolean equals(Object o)
  {
    if( !(o instanceof BigFraction));
    {
      return false;
    }
    //BigFraction f = (BigFraction) o;
    //return (denom.equals(f.denom)) && (num.equals(f.num));
  }
  public BigFraction add(BigFraction that)
  {
    BigInteger top = num.multiply(that.denom).add(denom.multiply(that.num));
    BigInteger bottom = denom.multiply(that.denom);
    return new BigFraction(top, bottom);
  }
  public BigFraction subtract(BigFraction that)
  {
    BigInteger top = num.multiply(that.denom).subtract(denom.multiply(that.num));
    BigInteger bottom = denom.multiply(that.denom);
    return new BigFraction(top, bottom);
  }
  public BigFraction multiply(BigFraction that)
  {
    BigInteger top = num.multiply(that.num);
    BigInteger bottom = denom.multiply(that.denom);
    return new BigFraction(top, bottom);
  }
  public BigFraction divide(BigFraction that)
  {
    BigInteger top = num.multiply(that.denom);
    BigInteger bottom = denom.multiply(that.num);
    return new BigFraction(top, bottom);
  }
  public BigFraction pow(int n)
  {
    if(n < 0)
    {
      return (new BigFraction(denom, num)).pow(-n);
    }
    return new BigFraction(num.pow(n), denom.pow(n));
  }
  public double toDouble()
  {
    String topString = "" + num;
    String bottomString = "" + denom;
    int exp = topString.length()-bottomString.length();
    String topDec = topString.charAt(0) + "." + topString.substring(1);
    String botDec = bottomString.charAt(0) + "." + bottomString.substring(1);
    double top = Double.parseDouble(topDec); //convert string with double in it to String
    double bot = Double.parseDouble(botDec);
    return top/bot*Math.pow(10,exp);
    //return 0.0; //method stub
    
    
  }
}