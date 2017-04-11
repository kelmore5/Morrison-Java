package threecolors.m12d08;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
    /*
     * This violates the 11th commandment - don't do duplicate code
     */
    JMenuItem redItem = new JMenuItem("Red");
    redItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        currentColor = Color.red;
        currentPanel.setColor(currentColor);
        repaint();
      }
    });
    
    JMenuItem greenItem = new JMenuItem("Green");
    greenItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        currentColor = Color.green;
        currentPanel.setColor(currentColor);
        repaint();
      }
    });
    
    JMenuItem blueItem = new JMenuItem("Blue");
    blueItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        currentColor = Color.blue;
        currentPanel.setColor(currentColor);
        repaint();
      }
    });
    
    JMenuItem blackItem = new JMenuItem("Black");
    blackItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        currentColor = Color.black;
        currentPanel.setColor(currentColor);
        repaint();
      }
    });
    
    colorMenu.add(redItem);
    colorMenu.add(greenItem);
    colorMenu.add(blueItem);
    colorMenu.add(blackItem);
    
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
  public static void main(String[] args)
  {
    ThreeColors tc = new ThreeColors();
    javax.swing.SwingUtilities.invokeLater(tc);
  }
}