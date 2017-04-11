package disarray.m11d18;

import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class DisArray
{
  public static void main(String[] args)
  {
    //you will see these in code. They are fixed size.
    int[] x = new int[10];
    for(int k = 0; k < x.length; k++)
    {
      System.out.printf("x[%s] = %s\n", k, x[k]);
    }
    boolean[] foo = new boolean[5];
    System.out.println(Arrays.toString(foo));
    System.out.println(foo);
    String[] names = new String[5];
    System.out.println(Arrays.toString(names));
    for(int k = 0; k < names.length; k++)
    {
      names[k] = JOptionPane.showInputDialog("Enter a name:  ");
    }
    System.out.println(Arrays.toString(names));
    for(String k: names)
    {
      System.out.println(k);
    }
    //Prefer this:
    ArrayList<String> w = new ArrayList<String>();
    w.add("mikkelson");
    w.add("elmore");
    w.add("kolena");
    w.add("gray");
    System.out.println(w);
    System.out.println(w.get(3));
  }
}