package farmasset;

public class Cow extends Animal
{
  public Cow(String _name)
  {
    super(_name, "Moo!", "beef");
  }
  public String toString()
  {
    return "Cow named " + getName();
  }
}