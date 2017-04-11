package circlepanel.m12d05;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
public class CirclePanel extends JPanel
{
  public void paintComponent(Graphics g) 
  {
    g.setColor(new Color(0xFFFFFF));
    g.fillRect(0,0,getWidth(), getHeight());
    g.setColor(Color.RED);
    g.fillOval(getWidth()/2-50, getHeight()/2-50, 100,100); //the circle stays centered becaause whenever the screen is resized, this is called again and moves the circle
  }
}