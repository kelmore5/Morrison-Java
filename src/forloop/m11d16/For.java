package forloop.m11d16;

public class For
{
  public static void main(String[] args)
  {
    //makes a java class directly executable
    for(int k = 0; k < 20; k++)
    {
      System.out.printf("<tr><td>%s</td><td>%s</td></tr>\n", k, k*k);
    }
    //same thing with while
    int l = 0;
    while(l < 20)
    {
      System.out.printf("<tr><td>%s</td><td>%s</td></tr>\n", l, l*l);
      l++; //kinda heirror prone, aint it?
    }
  }
}