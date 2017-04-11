package farmasset;

public abstract class Fowl extends Animal
{
  private String name;
  public Fowl(String _name, String _noise)
  {
    super(_name, _noise, _name);
  }
}