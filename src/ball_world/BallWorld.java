package ball_world;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList; 
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class BallWorld extends JFrame implements Runnable
{
	public BallWorldPanel mainPanel;
	public File currentFile;
	public BallWorld()
	{
		super("Ball World");
		mainPanel = new BallWorldPanel();
		currentFile = new File("drawings.dp");
	}
	public void run()
	{
		setSize(500,500);

		JMenuBar mbar = new JMenuBar();
		setJMenuBar(mbar);

		JMenu fileMenu = new JMenu("File");
		mbar.add(fileMenu);
		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		saveItem.addActionListener(new ActionListener(){
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
		JMenuItem openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		openItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					readFromCurrentFile();
					mainPanel.repaint();
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
		JMenuItem clearItem = new JMenuItem("Clear Balls");
		fileMenu.add(clearItem);
		clearItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				mainPanel.clearBalls();
				repaint();
			}
		});
		JMenuItem quitItem = new JMenuItem("Quit");
		fileMenu.add(quitItem);
		quitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0); //make a normal termination
			}
		});

		JMenu colorMenu = new JMenu("Color");
		mbar.add(colorMenu);
		ArrayList<ColorMenuItem> colorMenuItems = new ArrayList<ColorMenuItem>();
		colorMenuItems.add(new ColorMenuItem(Color.red, "Red"));
		colorMenuItems.add(new ColorMenuItem(Color.green, "Green"));
		colorMenuItems.add(new ColorMenuItem(Color.blue, "Blue"));
		for(ColorMenuItem c: colorMenuItems)
		{
			colorMenu.add(c);
		}
		colorMenu.add(new CustomColor("Choose Circle Color", mainPanel.circleColor));
		colorMenu.add(new RandomColor("Random Circle Color"));

		JMenu backgroundMenu = new JMenu("Background");
		mbar.add(backgroundMenu);
		ArrayList<BackgroundMenuItem> backgroundMenuItems = new ArrayList<BackgroundMenuItem>();
		backgroundMenuItems.add(new BackgroundMenuItem(Color.red, "Red"));
		backgroundMenuItems.add(new BackgroundMenuItem(Color.green, "Green"));
		backgroundMenuItems.add(new BackgroundMenuItem(Color.blue, "Blue"));
		for(BackgroundMenuItem c: backgroundMenuItems)
		{
			backgroundMenu.add(c);
		}
		backgroundMenu.add(new CustomColor("Choose Background Color", mainPanel.panelColor));
		backgroundMenu.add(new RandomColor("Random Background Color"));

		JMenu sizeMenu = new JMenu("Radius");
		mbar.add(sizeMenu);
		ArrayList<SizeMenuItem> sizeMenuItems = new ArrayList<SizeMenuItem>();
		sizeMenuItems.add(new SizeMenuItem(5, "5"));
		sizeMenuItems.add(new SizeMenuItem(10, "10"));
		sizeMenuItems.add(new SizeMenuItem(50, "50"));
		sizeMenuItems.add(new SizeMenuItem(100, "100"));
		for(SizeMenuItem c: sizeMenuItems)
		{
			sizeMenu.add(c);
		}
		JMenuItem customSizeMenuItem = new JMenuItem("Custom");
		sizeMenu.add(customSizeMenuItem);
		customSizeMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int out = 0;
				JOptionPane customSizePane = new JOptionPane();
				String customSize = customSizePane.showInputDialog("Please input a value:");
				try
				{
					out = Integer.parseInt(customSize);
				}
				catch(NumberFormatException ex)
				{
					out = mainPanel.getRadius();
				}
				finally
				{
					mainPanel.setRadius(out);
				}
			}
		});
		sizeMenu.add(new RandomNumber());

		getContentPane().add(mainPanel);
		setVisible(true);
	}

	public void saveToCurrentFile() throws IOException
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(currentFile));
		oos.writeObject(mainPanel.getPositions());
		oos.writeObject(mainPanel.getPanelColor());
		oos.writeObject(mainPanel.getCircleColor());
		oos.writeInt(mainPanel.getRadius());
		oos.writeObject(mainPanel.getBalls());
		oos.close();
	}

	@SuppressWarnings("unchecked")
	public void readFromCurrentFile() throws IOException, ClassNotFoundException
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(currentFile));
		mainPanel.setPositions((ArrayList<int[]>) ois.readObject());
		mainPanel.setPanelColor((Color) ois.readObject());
		mainPanel.setCircleColor((Color) ois.readObject());
		mainPanel.setRadius(ois.readInt());
		mainPanel.setBalls((ArrayList<Ball>) ois.readObject());
		repaint();
		ois.close();
	}

	public class BackgroundMenuItem extends JMenuItem
	{
		private final Color color;
		public BackgroundMenuItem(Color _color, String _colorName)
		{
			super(_colorName);
			color = _color;
			addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					mainPanel.setPanelColor(color);
					mainPanel.repaint();
				}
			});
		}
	}

	public class ColorMenuItem extends JMenuItem
	{
		private final Color color;
		public ColorMenuItem(Color _color, String _colorName)
		{
			super(_colorName);
			color = _color;
			addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					mainPanel.setCircleColor(color);
				}
			});
		}

	}

	public class SizeMenuItem extends JMenuItem
	{
		private final int radius;
		public SizeMenuItem(int _radius, String _radiusName)
		{
			super(_radiusName);
			radius = _radius;
			addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					mainPanel.setRadius(radius);
				}
			});
		}
	}

	public class CustomColor extends JMenuItem
	{
		private final Color color;
		private final String customName;
		public CustomColor(String _customName, Color _color) 
		{
			super("Custom");
			color = _color;
			customName = _customName;
			addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					Color newColor = JColorChooser.showDialog(mainPanel, customName, color);
					if(customName.contains("Circle"))
					{
						mainPanel.setCircleColor(newColor);
					}
					else
					{
						mainPanel.setPanelColor(newColor);
						mainPanel.repaint();
					}
				}
			});
		}
	}

	public class RandomColor extends JMenuItem
	{
		private final String customName;
		public RandomColor(String _customName) 
		{
			super("Random");
			customName = _customName;
			addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					ArrayList<Integer> randomRGB = new ArrayList<Integer>();
					for(int n = 0; n <= 2; n++)
					{
						randomRGB.add((int) Math.round(Math.random()*255));
					}
					Color randomColor = new Color(randomRGB.get(0), randomRGB.get(1), randomRGB.get(2), 255);
					if(customName.contains("Circle"))
					{
						mainPanel.setCircleColor(randomColor);
					}
					else
					{
						mainPanel.setPanelColor(randomColor);
						mainPanel.repaint();
					}
				}
			});
		}
	}
	public class RandomNumber extends JMenuItem
	{
		public RandomNumber()
		{
			super("Random");
			addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					mainPanel.setRadius((int) Math.round(Math.random()*500));
				}
			});
		}
	}

	public static void main(String[] args)
	{
		BallWorld bw = new BallWorld();
		javax.swing.SwingUtilities.invokeLater(bw);
	}
}