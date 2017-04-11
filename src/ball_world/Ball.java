package ball_world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class Ball
{
  Color color;
  int[] point;
  int radius;
  
  public Ball(Color _color, int[] p, int _radius)
  {
    color = _color;
    point = p;
    radius = _radius;
  }
  
  public void draw(Graphics g)
  {
    g.setColor(color);
    g.fillOval(point[0], point[1], radius*2, radius*2);
  }
  
}