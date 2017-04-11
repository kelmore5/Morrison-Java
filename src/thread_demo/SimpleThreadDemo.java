package thread_demo;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SimpleThreadDemo extends JFrame implements Runnable
{
  public SimpleThreadDemo()
  {
    super("Push the button!");
    
  }
  public void run()
  {
    setSize(100,150);
    JButton make = new JButton("Make!");
    make.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        Thread t = new Thread(new Countdown(10));
        t.start();
      }
    });
    getContentPane().add(make);
    setVisible(true);
  }
  public static void main(String[] args)
  {
    SimpleThreadDemo std = new SimpleThreadDemo();
    javax.swing.SwingUtilities.invokeLater(std);
  }
}