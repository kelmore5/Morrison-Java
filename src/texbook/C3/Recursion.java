package texbook.C3;

import java.math.BigInteger;
public class Recursion
{
public BigInteger factorial(int n)
{
return n > 0 ?
factorial(n - 1).multiply(BigInteger.valueOf(n)):
BigInteger.valueOf(1);
}
}