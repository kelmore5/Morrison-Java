package thread_demo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class Countdown extends JFrame implements Runnable
{
  NumberPanel display;
  int number;
  static Random r;
  static
  {
    r = new Random();
  }
  public Countdown(int _number)
  {
    super("Countdown");
    number = _number;
    display = new NumberPanel(number);
  }
  public void run()
  {
    setSize(300,300);
    setLocation(r.nextInt(800), r.nextInt(800));
    getContentPane().add(display);
    setVisible(true);
    try
    {
      while(number > 0)
      {
        Thread.sleep(1000);
        number--;
        repaint();
      }
    }
    catch(Exception ex)
    {
      //quack
    }
    dispose();
  }
  public class NumberPanel extends JPanel
  {
    public NumberPanel(int _number)
    {
      super();
    }
    public void paintComponent(Graphics g)
    {
      g.setColor(Color.white);
      g.setFont(new Font("Serif", Font.BOLD, 144));
      g.fillRect(0,0,getWidth(),getHeight());
      g.setColor(Color.green);
      g.drawString("" + number, 150,150);
    }
  }
}