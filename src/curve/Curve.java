package curve;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.util.ArrayList;


public class Curve extends ArrayList<Point>
{
  final Color color;
  final int lineWidth;
  public Curve(Color _color, int _lineWidth)
  {
    super();
    color = _color;
    lineWidth = _lineWidth;
  }
  public void draw(Graphics g)
  {
    //Graphics 2D makes it possible to have 
    //nice smooth curves.
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(color);
    int n = size();
    for(int k = 0; k < n - 1; k++)
    {
      g2d.setStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
      g2d.drawLine(get(k).x, get(k).y, get(k+1).x, get(k+1).y);
    }
  }
}