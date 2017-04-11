package myframe.m12d05;

import javax.swing.JFrame;
import kelmore5.java.morrison.intro.circlepanel.m12d05.CirclePanel;

public class MyFrame extends JFrame implements Runnable
{
  public MyFrame()
  {
    super("Let's make Nellis turn blue.");
  }
  public void run()
  {
    setSize(400,400);
    getContentPane().add(new CirclePanel());
    setVisible(true);
  }
  public static void main(String[] args)
  {
    MyFrame mf = new MyFrame();
    javax.swing.SwingUtilities.invokeLater(mf);
  }
}