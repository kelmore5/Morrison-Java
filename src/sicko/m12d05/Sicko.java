package sicko.m12d05;

public class Sicko
{
  final public int x;
  private static int y;
  static
  {
    y = 3;
  }
  public Sicko()
  {
    x = 2 + y;
  }
  public int foolish(int z)
  {
    return x*y*z;
  }
}