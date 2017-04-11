package rpn_calculator; /***************************************************
 * 
 * HiPpotamus Calculator
 * Author: Kyle Elmore
 * Block: B
 * 
 ***************************************************
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.Stack;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;

public class HPC2 extends JFrame implements Runnable
{
  Stack<String> theStack;
  JTextField jtf;
  String fieldText;
  double result;
  Font f;
  HPC2()
  {
    super("HiPpotamus Calclator");
    theStack = new Stack<String>();
    jtf = new JTextField();
    jtf.setHorizontalAlignment(JTextField.RIGHT);
    fieldText = "0.0";
    jtf.setText(fieldText);
    jtf.setBackground(Color.black);
    jtf.setForeground(Color.red);
    result = 0;
    f = new Font("NixieOne", Font.PLAIN, 24);
  }
  
  public void run()
  {
    setSize(418,483);
    jtf.setFont(f);
    getContentPane().add(BorderLayout.NORTH, jtf);
    getContentPane().add(BorderLayout.EAST, makeOpPanel());
    getContentPane().add(BorderLayout.CENTER, makeNumberPanel());
    setVisible(true);
  }
  
  public static void main(String args[])
  {
    HPC2 hpc = new HPC2();
    javax.swing.SwingUtilities.invokeLater(hpc);
  }
  
  public JPanel makeNumberPanel()
  {
    JPanel numberPanel = new JPanel();
    GridBagLayout gbl = new GridBagLayout();
    numberPanel.setLayout(gbl);
    for(int k = 9; k >= 0; k--)
    {
      GridBagConstraints gbc = new GridBagConstraints();
      if(k == 7 | k == 4 | k == 1)
        gbc.gridwidth = GridBagConstraints.REMAINDER;
      NumberKey nk = new NumberKey("" + k);
      gbl.setConstraints(nk, gbc);
      numberPanel.add(nk);
    }
    numberPanel.add(new NumberKey("."));
    numberPanel.add(new NumberKey("-"));
    return numberPanel;//return JPanel with number pad
  }
  
  private double checkDouble(String s)
  {
    double d = 0;
    try
    {
      d = Double.parseDouble(s);
    }
    catch(Exception ex)
    {
      d = 0.0;
    }
    finally
    {
      return d;
    }
  }
  
  public JPanel makeOpPanel()
  {
    JPanel opPanel = new JPanel(new GridLayout(7,1));
    opPanel.add(new AddKey("+"));
    opPanel.add(new SubtractKey("-"));
    opPanel.add(new MultiplyKey("*"));
    opPanel.add(new DivideKey("/"));
    opPanel.add(new EnterKey("Enter"));
    opPanel.add(new ClearKey("CE"));
    opPanel.add(new ClearKey("Clear"));
    return opPanel;
  }
  
  /************************ALIENS******************/
  
  abstract class Key extends JButton implements ActionListener
  {
    String label;
    Key(String _label)
    {
      super(_label);
      label = _label;
      setFont(f);
      setBackground(Color.black);
      setForeground(Color.white);
      addActionListener(this);
    }
    public String showLabel()
    {
      return label;
    }
  }
  class NumberKey extends Key
  {
    NumberKey(String _label)
    {
      super(_label);
      setPreferredSize(new Dimension(100,100));
    }
    public void actionPerformed(ActionEvent e)
    {
      if(fieldText == "0.0")
        fieldText = showLabel();
      else
        fieldText += showLabel();
      jtf.setText(fieldText);
    }
  }
  class ClearKey extends OpKey
  {
    String label;
    ClearKey(String _label)
    {
      super(_label);
      label = _label;
    }
    public void actionPerformed(ActionEvent e)
    {
      fieldText = "0.0";
      jtf.setText(fieldText);
      if(label == "Clear")
        theStack.clear();
    }
  }
  abstract class OpKey extends Key
  {
    OpKey(String _label)
    {
      super(_label);
      setPreferredSize(new Dimension(100,50));
    }
    public void actionPerformed(ActionEvent e)
    {
      System.out.println("Hello");
    }
    
    public void pushToStack(double _result)
    {
      theStack.clear();
      theStack.add("" + result);
      fieldText = "0.0";
      jtf.setText("" + result);
      result = 0;
    }
  }
  class EnterKey extends OpKey
  {
    EnterKey(String label)
    {
      super(label);
    }
    public void actionPerformed(ActionEvent e)
    {
      theStack.push(jtf.getText());
      fieldText = "0.0";
      jtf.setText(fieldText);
    }
  }
  class AddKey extends OpKey
  {
    AddKey(String label)
    {
      super(label);
    }
    public void actionPerformed(ActionEvent e)
    {
      theStack.push(jtf.getText());
      result = 0;
      for(String c: theStack)
        result += checkDouble(c);
      pushToStack(result);
    }
  }
  class SubtractKey extends OpKey
  {
    SubtractKey(String label)
    {
      super(label);
    }
    public void actionPerformed(ActionEvent e)
    {
      theStack.push(jtf.getText());
      result = 0;
      result = checkDouble(theStack.get(0));
      for(int k = 1; k <= theStack.size()-1; k++)
        result -= checkDouble(theStack.get(k));
      pushToStack(result);
    }
  }
  class MultiplyKey extends OpKey
  {
    MultiplyKey(String label)
    {
      super(label);
    }
    public void actionPerformed(ActionEvent e)
    {
      theStack.push(jtf.getText());
      result = 1;
      for(String c: theStack)
        result *= checkDouble(c);
      pushToStack(result);
    }
  }
  class DivideKey extends OpKey
  {
    DivideKey(String label)
    {
      super(label);
    }
    public void actionPerformed(ActionEvent e)
    {
      theStack.push(jtf.getText());
      result = 0;
      result = checkDouble(theStack.get(0));
      for(int k = 1; k <= theStack.size()-1; k++)
        result /= checkDouble(theStack.get(k));
      pushToStack(result);
    }
  }
}