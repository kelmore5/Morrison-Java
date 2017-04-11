package hughjass.m11d28;

import java.math.BigInteger;

public class HughJass
{
 public static BigInteger factorial(int n)
 {
  //computes n!
   if(n < 0)
   {
    throw new IllegalArgumentException();
   }
   //Plan:
   /*
    p = 1
    for(int k = 0; k <= n; k++)
    {
        p = p*k;
    }
    */
   BigInteger p = BigInteger.ONE;
   for(int k = 1; k <= n; k++)
   {
     //p = p.multiply(new BigInteger("" + k));
     p = p.multiply(BigInteger.valueOf(k));
     //BigInteger.valueOf is a static factory method
   }
   return p;
 }
}