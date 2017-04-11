package threecolors.m12d07;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class ColorPanel extends JPanel
{
  private Color color;
  public ColorPanel()
  {
    color = Color.black;
  }
  public void setColor(Color c)
  {
    color = c;
  }
  public void paintComponent(Graphics g)
  {
    g.setColor(color);
    g.fillRect(0,0,getWidth(),getHeight());
  }
}