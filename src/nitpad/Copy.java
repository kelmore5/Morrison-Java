package nitpad;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Copy
{
  public static void main(String[] args)
  {
    File f = new File(args[0]);
    File recipient = new File(args[1]);
    System.out.println(f.getAbsolutePath());
    FileReader fr = null;
    FileWriter fw = null;
    try
    {
      if(!f.exists())
      {
        System.err.printf("File %s does not exist\n", f.getAbsolutePath());
      }
      fr = new FileReader(f);
      fw = new FileWriter(recipient);
      int ch;
      while ( (ch = fr.read()) != -1)
      {
        fw.write((char)ch);
      }
      fr.close();
      fw.close();  //robert fulghum
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