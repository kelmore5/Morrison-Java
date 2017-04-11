package colorshower.m01d06;

import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorShower extends JFrame implements Runnable
{
  private Color color;
  private JTextField jtf;
  public ColorShower()
  {
    super("Color Shower");
    color = Color.black;
    jtf = new JTextField();
    jtf.setText("0x000000");
  }
  private int processHexString(String str)
  {
    int out = 0;
    if(str.startsWith("0x"))//there is a 0x at the beginning
    {
      str = str.substring(2);
      //get rid of 0x
    }
    try
    {
      out = Integer.parseInt(str, 16);
    }
    catch(NumberFormatException e)
    {
      jtf.setText("ERROR");
    }
    finally
    {
    return out;//this code executes whether an exception occurs or not
    }
  }
  public void run()
  {
    setSize(500,500);
    getContentPane().add(BorderLayout.NORTH, jtf);
    getContentPane().add(BorderLayout.CENTER, new ColorPanel());
    jtf.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //get the text string from the field
        String hex = jtf.getText();
        System.out.println(hex);
        color = new Color(processHexString(hex));
        String displayString = "0x" + hex.toUpperCase();
        jtf.setText(displayString);
        //set the color to the righ color
        //repaint
        repaint();
      }
    });
    setVisible(true);
  }
  class ColorPanel extends JPanel
  {
    public void paintComponent(Graphics g)
    {
      g.setColor(color);
      g.fillRect(0,0,getWidth(), getHeight());
    }
  }
  public static void main(String args[])
  {
    ColorShower cs = new ColorShower();
    javax.swing.SwingUtilities.invokeLater(cs);
  }
}