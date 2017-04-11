package mouseframe.m12d14;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MousePanel extends JPanel implements MouseListener
{
  Color color;
  public MousePanel()
  {
    color = Color.black;
    addMouseListener(this);
  }
  public void paintComponent(Graphics g)
  {
    g.setColor(color);
    g.fillRect(0,0,getWidth(), getHeight());
  }
  public void mouseExited(MouseEvent e)
  {
    color = Color.black;
    repaint();
  }
  public void mouseEntered(MouseEvent e)
  {
    color = Color.blue;
    repaint();
  }
  public void mouseReleased(MouseEvent e)
  {
    color = Color.green;
    repaint();
  }
  public void mousePressed(MouseEvent e)
  {
    color = Color.yellow;
    repaint();
  }
  public void mouseClicked(MouseEvent e)
  {
    System.out.printf("(%s, %s)\n", e.getX(), e.getY());
    color = Color.red;
    repaint();
  }
}