package threecolors.m12d12;

import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ColorMenuItem extends JMenuItem
{
  private final Color color;
  //private String colorName;
  public ColorMenuItem(Color _color, String _colorName)
  {
    super(_colorName);
    color = _color;
    addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //set current color of ThreeColors to my color
        //set current panel's color to my color
        repaint();
      }
    });
  }
}