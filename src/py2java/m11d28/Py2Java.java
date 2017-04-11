package py2java.m11d28;

import java.math.BigInteger;

public class Py2Java
{
  int x;     //only a property of a particular instance of the class
  static int y;
  public static void main(String[] args)
  {
    //Illegal: static error System.out.println(x);
    Py2Java p2j = new Py2Java();
    System.out.println(p2j.x);
    for(String foo: args)
    {
      System.out.println(foo);
    }
    y = 5;
    System.out.println(y);
    /*
     * Python     import sys
     *            for k in sys.argv: print k
     */
    
    System.out.printf("BigInteger.ZERO = %s\n", BigInteger.ZERO);
    System.out.printf("BigInteger.ONE = %s\n", BigInteger.ONE);
    System.out.printf("BigInteger.TEN = %s\n", BigInteger.TEN);
    //Math m = new Math();
    final int elmore = 42;
  }
}

/*
 * 

Command Line Arguments


Arrays vs. ArrayLists

What is Static?

Two types of properties of a class:

Class Properties
These things are static
Big Integer has three static constants - ZERO, ONE, and TEN

Instance Properties (state variabls)



*/