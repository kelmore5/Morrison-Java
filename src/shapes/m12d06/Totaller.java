package shapes.m12d06;

import java.util.ArrayList;
public class Totaller
{
  public static void main(String[] args)
  {
    double sum = 0;
    ArrayList<IShape> foo = new ArrayList<IShape>();
    foo.add(new Circle(1));
    foo.add(new Rectangle(12,5));
    foo.add(new Square(10));
    for(IShape kyle: foo)
    {
      sum += kyle.area();
      System.out.println(kyle);
    }
    System.out.printf("The total area is %\n", sum);
  }
}