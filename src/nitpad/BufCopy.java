package nitpad;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufCopy
{
  public static void main(String[] args)
  {
    File f = new File(args[0]);
    File recipient = new File(args[1]);
    System.out.println(f.getAbsolutePath());
    BufferedReader br = null;
    BufferedWriter bw = null;
    try
    {
      if(!f.exists())
      {
        System.err.printf("File %s does not exist\n", f.getAbsolutePath());
      }
      br = new BufferedReader(new FileReader(f));
      bw = new BufferedWriter(new FileWriter(recipient));
      String line;
      while ( (line = br.readLine()) != null)
      {
        bw.write(line + "\n");
      }
      br.close();
      bw.close();  //robert fulghum
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