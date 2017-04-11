package farmasset;

public abstract class FarmAsset
{
  private String name;
  public FarmAsset(String _name)
  {
    name = _name;
  }
  public String getName()
  {
    return name;
  }
}