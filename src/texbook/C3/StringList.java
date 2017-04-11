package texbook.C3;

import java.util.ArrayList;
public class StringList
{
ArrayList<String> theList;
public StringList()
{
theList = new ArrayList<String>();
}
public boolean add(String newItem)
{
return theList.add(newItem);
}
public String get(int k)
{
return theList.get(k);
}
}