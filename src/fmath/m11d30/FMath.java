package fmath.m11d30;

import kelmore5.java.morrison.intro.bigfraction.m11d30.BigFraction;

public class FMath
{
  //Private constructor. Class-stration
 private FMath()
 {
   
 }
 public static BigFraction harmonic(int n)
 {
   if(n < 0)
     throw new IllegalArgumentException();
   BigFraction sum = new BigFraction();
   for(int k = 1; k <= n; k++)
   {
     sum = sum.add(new BigFraction(1,k));
   }
   return sum;
 }
 public BigFraction rationalSqrt(int radicand, int times)
 {
   BigFraction x = new BigFraction(radicand, 2);
   for(int k = 0; k < times; k++)
   {
       x = (x.multiply(x).add(new BigFraction(radicand, 1)).divide(x.multiply(new BigFraction(2,1))));
     }

     return x;
   }
}