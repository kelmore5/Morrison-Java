package scope.m11d14;

public class  Scope
{
 public void run()
 {
   int n = 14;
   while(n > 0)
   {
     int k = n*n;
     System.out.println(k);
     n -= 2;
   }
   //System.out.printf("k = %s\n", k);
 }
}