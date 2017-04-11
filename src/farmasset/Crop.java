package farmasset;

public abstract class Crop extends FarmAsset
{
  private double acreage;
  public Crop(String _name, double _acreage)
  {
    super(_name);
    acreage = _acreage;
  }
}