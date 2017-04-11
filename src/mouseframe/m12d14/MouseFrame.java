package mouseframe.m12d14;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MouseFrame extends JFrame implements Runnable
{
 MousePanel left;
 MousePanel right;
 
 public MouseFrame()
 {
   super("Mouse Event Demonstration");
   left = new MousePanel();
   right = new MousePanel();
 }
 
 public void run()
 {
   setSize(700, 450);
   JMenuBar mbar = new JMenuBar();
   setJMenuBar(mbar);
   JMenu fileMenu = new JMenu("File");
   mbar.add(fileMenu);
   JMenuItem quitItem = new JMenuItem("Quit");
   fileMenu.add(quitItem);
   quitItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        System.exit(0); //make a normal termination
      }
    });
   setLayout(new GridLayout(1,2));
   getContentPane().add(left);
   getContentPane().add(right);
   setVisible(true);
 }
 
 public static void main(String[] args)
 {
  MouseFrame mf = new MouseFrame();
  javax.swing.SwingUtilities.invokeLater(mf);
 }
}