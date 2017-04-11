package texbook.C3;

public class CommandLineDemo
{
public static void main(String[] args)
{
int num = args.length;
System.out.println("You entered " + num + " arguments.");
int count = 0;
for (String k: args)
{
System.out.println("args[" + count + "] = " + k);
count ++;
}
}
}