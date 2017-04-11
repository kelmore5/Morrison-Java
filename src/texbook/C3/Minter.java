package texbook.C3;

public class Minter
{
private static int nextID;
final private int ID;
static
{
nextID = 1;
}
  public static void main(String[] args)
{
Minter m = new Minter();
System.out.println(m);
}
public Minter()
{
ID = nextID;
nextID++;
}
public String toString()
{
return "Minter, ID = " + ID;
}
}