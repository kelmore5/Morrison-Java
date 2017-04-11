package scope.m11d15;

public class  Scope
{
  int a;
  int y;
  int z;
 public void run()
 {
   int n = 14;
   int k = 0;
   while(n > 0)
   {
     //int k = n*n;
     k = n*n;
     System.out.println(k);
     n -= 2;
     a += 5;
   }
   System.out.printf("k = %s\n", k);
   System.out.printf("a = %s, y = %s, z = %s\n", a, y, z);
 }
}