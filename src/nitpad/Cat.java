package nitpad;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Cat
{
  public static void main(String[] args)
  {
    File f = new File(args[0]);
    System.out.println(f.getAbsolutePath());
    FileReader fr = null;
    try
    {
      if(!f.exists())
      {
        System.err.printf("File %s does not exist\n", f.getAbsolutePath());
      }
      fr = new FileReader(f);
      int ch;
      while ( (ch = fr.read()) != -1)
      {
        System.out.print((char)ch);
      }
      fr.close();
    }
    catch(FileNotFoundException ex)
    {
      System.err.printf("File %s could not be opened.\n", f.getAbsolutePath());
    }
    catch(IOException ex)
    {
      System.err.printf("Cannot read from file %s.\n", f.getAbsolutePath());
    }
    //characterwise reading
  }
}