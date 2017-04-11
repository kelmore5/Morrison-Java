package threecolors.m12d07;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class ThreeColors extends JFrame implements Runnable
{
  public ThreeColors()
  {
    super("Three Colors");
  }
  public void run()
  {
    setSize(600,600);
    getContentPane().add(new ThreeColorsPanel());
    makeMenus();
    setVisible(true);
  }
  public void makeMenus()
  {
    JMenuBar mbar = new JMenuBar();
    setJMenuBar(mbar);
    JMenu fileMenu = new JMenu("File");
    mbar.add(fileMenu);
  }
  public static void main(String[] args)
  {
    ThreeColors tc = new ThreeColors();
    javax.swing.SwingUtilities.invokeLater(tc);
  }
}