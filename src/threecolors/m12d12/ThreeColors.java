package threecolors.m12d12;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList; 

public class ThreeColors extends JFrame implements Runnable
{
  Color currentColor;
  ColorPanel currentPanel;
  ThreeColorsPanel tcp;
  public ThreeColors()
  {
    super("Three Colors");
    tcp = new ThreeColorsPanel();
    currentColor = Color.black;
    currentPanel = tcp.getMiddle();
  }
  public void run()
  {
    setSize(600,600);
    getContentPane().add(tcp);
    makeMenus();
    setVisible(true);
  }
  public void makeMenus()
  {
    JMenuBar mbar = new JMenuBar();
    setJMenuBar(mbar);
    
    JMenu fileMenu = new JMenu("File");
    mbar.add(fileMenu);
    
    JMenuItem quitItem = new JMenuItem("Quit");
    quitItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        System.exit(0); //make a normal termination
      }
    });
    fileMenu.add(quitItem);
    
    JMenu colorMenu = new JMenu("Color");
    mbar.add(colorMenu);
    
    ArrayList<ColorMenuItem> colorMenuItems = new ArrayList<ColorMenuItem>();
    colorMenuItems.add(new ColorMenuItem(Color.red, "Red"));
    colorMenuItems.add(new ColorMenuItem(Color.green, "Green"));
    colorMenuItems.add(new ColorMenuItem(Color.blue, "Blue"));
    colorMenuItems.add(new ColorMenuItem(Color.black, "Black"));
    colorMenuItems.add(new ColorMenuItem(new Color(0xFFFF00), "Yellow"));
    
    /*ColorMenuItem redItem = new ColorMenuItem(Color.red, "Red");
    ColorMenuItem greenItem = new ColorMenuItem(Color.green, "Green");
    ColorMenuItem blueItem = new ColorMenuItem(Color.blue, "Blue");
    ColorMenuItem blackItem = new ColorMenuItem(Color.black, "Black");
    
    colorMenu.add(redItem);
    colorMenu.add(greenItem);
    colorMenu.add(blueItem);
    colorMenu.add(blackItem);*/
    
    //this can be etched in stone
    for(ColorMenuItem c: colorMenuItems)
    {
      colorMenu.add(c);
    }
    
    JMenu positionMenu = new JMenu("Position");
    mbar.add(positionMenu);
    
    JMenuItem leftItem = new JMenuItem("Left");
    positionMenu.add(leftItem);
    JMenuItem middleItem = new JMenuItem("Middle");
    positionMenu.add(middleItem); 
    JMenuItem rightItem = new JMenuItem("Right");
    positionMenu.add(rightItem);
    
    leftItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //tell app current panel is left panel
        currentPanel = tcp.getLeft();
      }
    });
    
    middleItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        currentPanel = tcp.getMiddle();
      }
    });
    
    rightItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        currentPanel = tcp.getRight();
      }
    });
  }
  /*********************aliens..............*****************/
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
          currentColor = color;
          //set current panel's color to my color
          currentPanel.setColor(color);
          //ThreeColors.this.repaint();
          currentPanel.repaint();
        }
      });
    }
  }
  public static void main(String[] args)
  {
    ThreeColors tc = new ThreeColors();
    javax.swing.SwingUtilities.invokeLater(tc);
  }
}