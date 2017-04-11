package ball_world;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList; 
import java.util.Arrays;

public class BallWorldPanel extends JPanel implements MouseListener
{
  public Color panelColor;
  public int circleRadius;
  public ArrayList<Ball> balls;
  public ArrayList<int[]> positions;
  public Color circleColor;
  public BallWorldPanel()
  {
    positions = new ArrayList<int[]>();
    panelColor = Color.black;
    circleColor = Color.red;
    circleRadius = 100;
    balls = new ArrayList<Ball>();
    addMouseListener(this);
  }
  public void paintComponent(Graphics g)
  {
    g.setColor(panelColor);
    g.fillRect(0,0,getWidth(), getHeight());
    for(Ball c: balls)
    {
      c.draw(g);
    }
  }
  public void setBalls(ArrayList<Ball> b)
  {
    balls = b;
  }
  public void setPanelColor(Color c)
  {
    panelColor = c;
  }
  
  public void setCircleColor(Color c)
  {
    circleColor = c;
  }
  
  public void setPositions(ArrayList<int[]> p)
  {
    positions = p;
  }
  
  public ArrayList<Ball> getBalls()
  {
    return balls;
  }
  public ArrayList<int[]> getPositions()
  {
    return positions;
  }
  public Color getPanelColor()
  {
    return panelColor;
  }
  public Color getCircleColor()
  {
    return circleColor;
  }
  
  public int getRadius()
  {
    return circleRadius;
  }
  
  public void clearBalls()
  {
    balls.clear();
  }
  
  public void setRadius(int radius)
  {
    circleRadius = radius;
  }
  
  public void mouseExited(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
  public void mousePressed(MouseEvent e){}
  public void mouseClicked(MouseEvent e)
  {
    int[] point = new int[2];
    point[0] = e.getX()-circleRadius;
    point[1] = e.getY()-circleRadius;
    positions.add(point);
    balls.add(new Ball(circleColor, point, circleRadius));
    repaint();
  }
  
}