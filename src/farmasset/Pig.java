package farmasset;

public class Pig extends Animal
{
  public Pig(String _name)
  {
    super(_name, "Reeeeet! Snort! Snuffle!", "pork");
  }
  public String toString()
  {
    return "Pig named " + getName();
  }
}