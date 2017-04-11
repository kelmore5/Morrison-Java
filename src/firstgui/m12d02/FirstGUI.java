package firstgui.m12d02;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstGUI implements Runnable //This just says there is a run method
{
  private int clickTimes;
  public FirstGUI()
  {
    clickTimes = 0;
  }
  //all gui classes will have this method
  public void run()
  {
    JFrame f = new JFrame("My First GUI");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //says what to do when close button is pushed
    f.setSize(400,400); //sets the size in pixels
    final JButton butt = new JButton("When life gives you lemons...");
    f.getContentPane().add(butt);
    butt.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("Make life take the lemons back!");
        clickTimes++;
        butt.setText("Make life take the lemons back! I've been clicked "+clickTimes+"!");
      }
    }); //Sad Santa
    f.setVisible(true); //shows the GUI
  }
  public static void main(String[] args)
  {
    FirstGUI fg = new FirstGUI(); //create instance
    javax.swing.SwingUtilities.invokeLater(fg); //peace in the event queue
  }
}