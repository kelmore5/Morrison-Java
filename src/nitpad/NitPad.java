package nitpad;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class NitPad extends JFrame implements Runnable, DocumentListener
{
	JEditorPane jep;
	File currentFile;
	boolean currentFileSaved;
	public NitPad()
	{
		super("Nit Pad: New File *");
		jep = new JEditorPane();
		currentFile = null; //file is unsaved
		currentFileSaved = false;
		jep.getDocument().addDocumentListener(this);
		//currentFile = new File("NitPad.java");
		//setTitle(currentFile.getAbsolutePath());
	}

	public boolean isUnsaved()
	{
		return currentFile == null || currentFileSaved;
	}
	public void fixTitleBar()
	{
		String star = currentFileSaved? "": " *";
		if(currentFile == null)
			setTitle("NitPad: New File" + star);
		else
			setTitle("NitPad: " + currentFile.getAbsolutePath() + star);
	}
	/**
	 * precondition: current file must not be null
	 * postcondition: contents of jep are written to the currentFile.
	 */

	public void writeToCurrentFile() throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(currentFile));
		String text = jep.getText();
		bw.write(text);
		bw.close();
		currentFileSaved = true;
	}
	public boolean selectFile()
	{
		JFileChooser jfc = new JFileChooser();
		int returnValue = jfc.showOpenDialog(NitPad.this);
		if(returnValue == JFileChooser.APPROVE_OPTION)
		{
			currentFile = jfc.getSelectedFile();
			fixTitleBar();
		}
		return returnValue == JFileChooser.APPROVE_OPTION;
	}

	public void run()
	{
		setSize(500,500);
		makeMenus();
		getContentPane().add(jep);
		setVisible(true);
	}
	public void makeMenus()
	{
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		//file menu and associated menu items
		JMenu fileMenu = new JMenu("File");
		mb.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem openMenuItem = new JMenuItem("Open...");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem saveAsMenuItem = new JMenuItem("Save As...");
		JMenuItem closeMenuItem = new JMenuItem("Close");
		JMenuItem quitMenuItem = new JMenuItem("Quit");
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(closeMenuItem);
		fileMenu.add(quitMenuItem);
		//file menu listeners
		openMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//TODO: Protect user from his own stupidity
				//browse to the file
				if(selectFile())
				{
					try
					{
						BufferedReader br = new BufferedReader(new FileReader(currentFile));
						String line;
						StringBuffer sb = new StringBuffer();
						while( (line = br.readLine()) != null)
						{
							sb.append(line + "\n");
						}
						jep.setText(sb.toString());
						br.close();
						currentFileSaved = true;
						fixTitleBar();
					}
					catch(IOException ex)
					{
						ex.printStackTrace();
					}
					//place the contents in the JEditorPane
					//close the file
				}
			}
		});
		saveMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//TODO: Protect user from his own stupidity
				//browse to the file
				if(currentFile == null)
				{
					if(selectFile())
					{
						try
						{
							writeToCurrentFile();
							fixTitleBar();
							return;
						}
						catch(IOException ex)
						{
							System.err.printf("Could not write to %s\n", currentFile.getAbsolutePath());
						}
					}
				}
				else
				{
					try
					{
						writeToCurrentFile();
						fixTitleBar();
						return;
					}
					catch(IOException ex)
					{
						System.err.printf("Could not write to %s\n", currentFile.getAbsolutePath());
					}
				}

				//place the contents in the JEditorPane
				//close the file
			}

		});
		saveAsMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(currentFile == null)
				{
					if(selectFile())
					{
						try
						{
							writeToCurrentFile();
							fixTitleBar();
							return;
						}
						catch(IOException ex)
						{
							System.err.printf("Could not write to %s\n", currentFile.getAbsolutePath());
						}
					}
				}
				else
				{
					try
					{
						writeToCurrentFile();
						fixTitleBar();
						return;
					}
					catch(IOException ex)
					{
						System.err.printf("Could not write to %s\n", currentFile.getAbsolutePath());
					}
				}

				//TODO: Protect user from his own stupidity
				//browse to the file
				//place the contents in the JEditorPane
				//close the file
			}
		});
		quitMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				boolean shouldIWrite = false;
				if(currentFile == null)
					shouldIWrite = selectFile();//if returnValue is true, file is selected
				else if (!currentFileSaved)
				{
					shouldIWrite = true;
				}
				if(shouldIWrite)
				{
					try
					{
						writeToCurrentFile();
						System.exit(0);
					}
					catch(IOException ex)
					{
						System.err.printf("Could not write to %s\n", currentFile.getAbsolutePath());
					}
				}
				//TODO:  Protect user from his own stupidity
			}
		});

		closeMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				boolean shouldIWrite = false;
				if(currentFile == null)
					shouldIWrite = selectFile();//if returnValue is true, file is selected
				else if (!currentFileSaved)
				{
					shouldIWrite = true;
				}
				if(shouldIWrite)
				{
					try
					{
						writeToCurrentFile();
						jep.setText("");
						setTitle("NitPad: New File *");
					}
					catch(IOException ex)
					{
						System.err.printf("Could not write to %s\n", currentFile.getAbsolutePath());
					}
				}
			}
		});

		//edit menu and associated menu items
		JMenu editMenu = new JMenu("Edit");
		mb.add(editMenu);
		JMenuItem cutItem = new JMenuItem("Cut");
		cutItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				jep.cut();
			}
		});
		JMenuItem copyItem = new JMenuItem("Copy");
		copyItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				jep.copy();
			}
		});
		JMenuItem pasteItem = new JMenuItem("Paste");
		pasteItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				jep.paste();
			}
		});
		JMenuItem selectAllItem = new JMenuItem("Select All");
		selectAllItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				jep.selectAll();
			}
		});
		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(selectAllItem);
	}

	/***************Document Listener Methods****************/

	public void changedUpdate(DocumentEvent e)
	{
		currentFileSaved = false;
		fixTitleBar();
	}
	public void insertUpdate(DocumentEvent e)
	{
		currentFileSaved = false;
		fixTitleBar();
	}
	public void removeUpdate(DocumentEvent e)
	{
		currentFileSaved = false;
		fixTitleBar();
	}

	public static void main(String[] args)
	{
		NitPad np = new NitPad();
		javax.swing.SwingUtilities.invokeLater(np);
	}
}