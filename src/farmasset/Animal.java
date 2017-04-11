package farmasset;

public abstract class Animal extends FarmAsset
{
  private String noise;
  private String meatName;
  public Animal(String _name, String _noise, String _meatName)
  {
    super(_name);
    noise = _noise;
    meatName = _meatName;
  }
  public String getNoise()
  {
    return noise;
  }
  public String getMeatName()
  {
    return meatName;
  }
}