package texbook.C3;

public class Stand
{
public String fruit(char c)
{
String out = "";
switch(c)
{
case 'a': case 'A':
out = "apple";
break;
case 'b': case 'B':
out = "blueberry";
break;
case 'c': case 'C':
out = "cherry";
break;
default:
out = "No fruit with this letter";
}
return out;
}
}