package curve;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class DrawFrame extends JFrame implements Runnable
{
  private ArrayList<Curve> drawing;
  private Color background;
  private Color nextCurveColor;
  private int nextCurveWidth;
  private Curve currentCurve;
  private File currentFile;
  DrawPanel dp;
  public DrawFrame()
  {
    drawing = new ArrayList<Curve>();
    background = Color.white;
    nextCurveColor = Color.black;
    nextCurveWidth = 1;
    currentCurve = null;//If I screw up, I get  NPE in my face.
    //TODO: connect current file to menus properly
    currentFile = new File("drawing.dp");
    dp = new DrawPanel();
  }
  public void run()
  {
    setSize(600,400);
    getContentPane().add(dp);
    makeMenus();
    setVisible(true);
  }
  private void makeMenus()
  {
    JMenuBar mbar = new JMenuBar();
    setJMenuBar(mbar);
    JMenu fileMenu = new JMenu("File");
    JMenu colorMenu = new JMenu("Color");
    JMenu backgroundMenu = new JMenu("Background");
    JMenu widthMenu = new JMenu("Width");
    mbar.add(fileMenu);
    JMenuItem openItem = new JMenuItem("Open...");
    JMenuItem saveItem = new JMenuItem("Save");
    JMenu saveAsSubMenu = new JMenu("Save As");
    JMenuItem savePNGItem = new JMenuItem("Save as PNG...");
    JMenuItem saveAsFileItem = new JMenuItem("Save As File...");
    JMenuItem clearItem = new JMenuItem("Clear");
    JMenuItem quitItem = new JMenuItem("Quit");
    saveAsSubMenu.add(savePNGItem);
    saveAsSubMenu.add(saveAsFileItem);
    fileMenu.add(openItem);
    fileMenu.add(saveAsSubMenu);
    fileMenu.add(clearItem);
    fileMenu.add(quitItem);
    openItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          readFromCurrentFile();
          DrawFrame.this.repaint();
        }
        catch(IOException ex)
        {
          System.err.printf("Could not open %s\n", currentFile.getAbsolutePath());
        }
        catch(ClassNotFoundException ex)
        {
          System.err.println("Programmer screwup...");
        }
      }
    });
    saveAsFileItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          saveToCurrentFile();
        }
        catch(IOException ex)
        {
          System.err.printf("Could not save to %s\n", currentFile.getAbsolutePath());
        }
      }
    });
    savePNGItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          dp.saveToPNG();
        }
        catch(IOException ex)
        {
          System.err.printf("Could not save to %s\n", currentFile.getAbsolutePath());
        }
      }
    });
    clearItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        drawing.clear();
        background = Color.white;
        repaint();
      }
    });
    quitItem.addActionListener(new ActionListener(){
      
      public void actionPerformed(ActionEvent e)
      {
        System.exit(0);
      }
      
    });
    mbar.add(colorMenu);
    colorMenu.add(new ColorMenuItem(Color.red, "red"));
    colorMenu.add(new ColorMenuItem(Color.green, "green"));
    colorMenu.add(new ColorMenuItem(Color.blue, "blue"));
    colorMenu.add(new ColorMenuItem(Color.yellow, "yellow"));
    colorMenu.add(new ColorMenuItem(Color.white, "white"));
    colorMenu.add(new ColorMenuItem(Color.black, "black"));
    mbar.add(backgroundMenu);
    backgroundMenu.add(new BackgroundColorMenuItem(Color.red, "red"));
    backgroundMenu.add(new BackgroundColorMenuItem(Color.green, "green"));
    backgroundMenu.add(new BackgroundColorMenuItem(Color.blue, "blue"));
    backgroundMenu.add(new BackgroundColorMenuItem(Color.yellow, "yellow"));
    backgroundMenu.add(new BackgroundColorMenuItem(Color.white, "white"));
    backgroundMenu.add(new BackgroundColorMenuItem(Color.black, "black"));
    mbar.add(widthMenu);
    widthMenu.add(new WidthMenuItem(1));
    widthMenu.add(new WidthMenuItem(3));
    widthMenu.add(new WidthMenuItem(5));
    widthMenu.add(new WidthMenuItem(10));
    widthMenu.add(new WidthMenuItem(20));
    widthMenu.add(new WidthMenuItem(30));
    widthMenu.add(new WidthMenuItem(50));
    widthMenu.add(new WidthMenuItem(250));
    widthMenu.add(new WidthMenuItem(500));
    widthMenu.add(new WidthMenuItem(1000));
    widthMenu.add(new WidthMenuItem(9001));
  }
  
  public void saveToCurrentFile() throws IOException
  {
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(currentFile));
    oos.writeObject(drawing);
    oos.writeObject(background);
    oos.writeObject(nextCurveColor);
    oos.writeInt(nextCurveWidth);
    oos.close();
  }
  
  @SuppressWarnings("unchecked")
  public void readFromCurrentFile() throws IOException, ClassNotFoundException
  {
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(currentFile));
    drawing = (ArrayList<Curve>) ois.readObject();
    background = (Color) ois.readObject();
    nextCurveColor = (Color) ois.readObject();
    nextCurveWidth = ois.readInt();
    repaint();
    ois.close();
  }
  
  public static void main(String[] args)
  {
    DrawFrame df = new DrawFrame();
    javax.swing.SwingUtilities.invokeLater(df);
    
  }
  /*****************ALIENS*******************/
  class DrawPanel extends JPanel implements MouseListener, MouseMotionListener
  {
    public DrawPanel()
    {
      super();
      addMouseListener(this);
      addMouseMotionListener(this);
    }
    public void paintComponent(Graphics g)
    {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(background);
      g2d.fillRect(0,0,getWidth(), getHeight());
      for(Curve c: drawing)
      {
        c.draw(g2d);
      }
    }
    public void saveToPNG() throws IOException
    {
      BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = bi.createGraphics();
      paint(g2d);
      try
      {
        ImageIO.write(bi, "png", new File("image.png"));
      }
      catch(IOException ex)
      {
        System.err.println("Die now, mutha!");
      }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e)
    {
      currentCurve = new Curve(nextCurveColor, nextCurveWidth);
      drawing.add(currentCurve);
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e)
    {
      
    }
    public void mouseMoved(MouseEvent e){}
    public void mouseDragged(MouseEvent e)
    {
      currentCurve.add(e.getPoint());
      repaint();
    }
    
  }
  public class WidthMenuItem extends JMenuItem implements ActionListener
  {
    public int width;
    public WidthMenuItem(int _width)
    {
      super("" + _width);
      width = _width;
      addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
    {
      nextCurveWidth = width;
    }
  }
  class ColorMenuItem extends JMenuItem implements ActionListener
  {
    Color color;
    public ColorMenuItem(Color _color, String colorName)
    {
      super(colorName);
      color = _color;
      addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
    {
      nextCurveColor = color;
    }
  }
  class BackgroundColorMenuItem extends ColorMenuItem
  {
    public BackgroundColorMenuItem(Color _color, String colorName)
    {
      super(_color, colorName);
    }
    public void actionPerformed(ActionEvent e)
    {
      background = color;
      DrawFrame.this.repaint();
    }
  }
}
