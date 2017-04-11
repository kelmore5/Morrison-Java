package texbook.C3;

public class ticketTaker
{
  public void ticketTaker(int age)
  {
if (age < 13)
{
System.out.println("You may only see G movies.");
}
else if (age < 17)
{
System.out.println("You may only see PG or G- movies.");
}
else if (age < 18)
{
System.out.println("You may only see R, PG or G movies.");
}
else
{
System.out.println("You may see any movie.");
                     }
}
}