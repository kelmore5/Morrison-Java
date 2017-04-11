package web_browser;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.CSS;
import javax.swing.text.html.StyleSheet;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import java.net.HttpURLConnection;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class WebBrowser extends JFrame 
{
  private WebBrowserTabs tabbedPane;
  public ArrayList<URL> history;
  public ArrayList<GetURL> bookmarks;
  public WebBrowserPane currentBrowserPane;
  
  public WebBrowser() throws IOException 
  {
    tabbedPane = new WebBrowserTabs();
    
    history = new ArrayList<URL>();
    bookmarks = new ArrayList<GetURL>();
    
    tabbedPane.createBrowserPane();
    getContentPane().add(tabbedPane);
    makeMenus();
  }
  
  private void makeMenus()
  {
    JMenu fileMenu = new JMenu("File");
    fileMenu.add(new NewTabAction());
    
    JMenuItem historyItem = new JMenuItem("History");
    historyItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        String historyHTML = "";
        for(URL c: history)
        {
          historyHTML += getDateTime() + "     " + c.toString() + "\n";
        }
        WebBrowserPane browserPane = new WebBrowserPane();
        browserPane.displayPage(historyHTML);
        WebToolBar toolBar = new WebToolBar(browserPane);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(toolBar, BorderLayout.NORTH);
        panel.add(new JScrollPane(browserPane), BorderLayout.CENTER);
        tabbedPane.addTab("History", panel);
      }
    });
    fileMenu.add(historyItem);
    
    fileMenu.addSeparator();
    fileMenu.add(new ExitAction());
    fileMenu.setMnemonic('F');
    
    final JMenu bookmarkMenu = new JMenu("Bookmarks"); //Kyle
    JMenuItem addBookmark = new JMenuItem("Add to Bookmarks");
    addBookmark.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        if(bookmarks.isEmpty())
        {
          bookmarks.add(tabbedPane.getCurrentURL());
          bookmarkMenu.add(new BookmarkMenuItem(tabbedPane.getCurrentURL()));
        }
        else
        {
          if(!checkBookmark(tabbedPane.getCurrentURL()))
          {
            bookmarks.add(tabbedPane.getCurrentURL());
            bookmarkMenu.add(new BookmarkMenuItem(tabbedPane.getCurrentURL()));
          }
        }
      }
    });
    bookmarkMenu.add(addBookmark);
    bookmarkMenu.addSeparator();
    
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(fileMenu);
    menuBar.add(bookmarkMenu);
    setJMenuBar(menuBar);
    
  }
  
  
  
  class WebBrowserTabs extends JTabbedPane
  {
    WebBrowserPane browserPane;
    WebBrowserTabs()
    {
      super();
      browserPane = new WebBrowserPane();
      addChangeListener(new ChangeListener() {
        // This method is called whenever the selected tab changes
        public void stateChanged(ChangeEvent e) 
        {
          currentBrowserPane = (WebBrowserPane)(((JScrollPane)((JPanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex())).getComponent(1)).getViewport().getView());
        }
      });
    }
    
    public void createBrowserPane()
    {
      browserPane = new WebBrowserPane();
      WebToolBar toolBar = new WebToolBar(browserPane);
      JPanel panel = new JPanel(new BorderLayout());
      panel.add(toolBar, BorderLayout.NORTH);
      panel.add(new JScrollPane(browserPane), BorderLayout.CENTER);
      GetURL gurl = new GetURL("http://www.google.com");
      this.addTab(gurl.getTitle(), panel);
      this.setSelectedIndex(this.getTabCount()-1);
      browserPane.goToURL(gurl);
    }
    
    public GetURL getCurrentURL()
    {
      return currentBrowserPane.getURL();
    }
    
    public void displayPage(GetURL gurl)
    {
      currentBrowserPane.displayPage(gurl);
    }
    
    public void setTitle(GetURL gurl)
    {
      this.setTitleAt(tabbedPane.getSelectedIndex(), gurl.getTitle());
    }
    
  }
  
  class WebBrowserPane extends JEditorPane
  {
    private int historyIndex;
    public GetURL currentURL;
    
    public WebBrowserPane() 
    {
      setEditable(false);
    }
    
    public void goToURL(GetURL gurl) 
    {
      displayPage(gurl);
      history.add(gurl.getURL());
      historyIndex = history.size() - 1;
    }
    
    
    public GetURL forward() 
    {
      historyIndex++;
      if (historyIndex >= history.size())
        historyIndex = history.size() - 1;
      
      GetURL url = new GetURL(history.get(historyIndex).toString());
      displayPage(url);
      
      return url;
    }
    
    public GetURL back() 
    {
      historyIndex--;
      
      if (historyIndex < 0)
        historyIndex = 0;
      
      GetURL url = new GetURL(history.get(historyIndex).toString());
      displayPage(url);
      
      return url;
    }
    
    private void displayPage(GetURL pageURL) 
    {
      try 
      {
        setPage(pageURL.getURL());
        tabbedPane.setTitle(pageURL);
        currentURL = pageURL;
      } 
      catch (IOException ioException) 
      {
        ioException.printStackTrace();
      }
    }
    
    private void displayPage(String errorMessage)
    {
      setText(errorMessage);
    }
    public GetURL getURL()
    {
      return currentURL;
    }
  }
  
  class WebToolBar extends JToolBar implements HyperlinkListener 
  {
    private WebBrowserPane webBrowserPane;
    private JButton backButton;
    private JButton forwardButton;
    private JButton goButton;
    private JTextField urlTextField;
    
    public WebToolBar(WebBrowserPane browser) 
    {
      super("Web Navigation");
      
      // register for HyperlinkEvents
      webBrowserPane = browser;
      webBrowserPane.addHyperlinkListener(this);
      
      urlTextField = new JTextField(25);
      urlTextField.addActionListener(new ActionListener(){
        // navigate webBrowser to user-entered URL
        public void actionPerformed(ActionEvent e) 
        {
          GetURL gurl = new GetURL(urlTextField.getText(), e);
          goURL(gurl);
        }
      });
      
      backButton = new JButton("back");
      backButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) 
        {
          GetURL url = webBrowserPane.back();
          urlTextField.setText(url.getURL().toString());
        }
      });
      
      forwardButton = new JButton("forward");
      forwardButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) 
        {
          GetURL url = webBrowserPane.forward();
          urlTextField.setText(url.getURL().toString());
        }
      });
      
      goButton = new JButton("Go");
      goButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
          GetURL gurl = new GetURL(urlTextField.getText(), e);
          goURL(gurl);
        }
      });
      
      
      add(backButton);
      add(forwardButton);
      add(urlTextField);
      add(goButton);
    }
    
    public String getURLString()
    {
      return urlTextField.getText();
    }
    
    public void hyperlinkUpdate(HyperlinkEvent event) 
    {
      if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) 
      {
        GetURL gurl = new GetURL(event.getURL().toString());
        webBrowserPane.goToURL(gurl);
        urlTextField.setText(gurl.getURL().toString());
      }
    }
    
    private void goURL(GetURL gurl)
    {
      if(gurl.getURL() == null)
      {
        webBrowserPane.displayPage("Cannot find site: " + urlTextField.getText());
        tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), "Error!");
      }
      else
        webBrowserPane.goToURL(gurl);
    }
  }
  
  public class GetURL
  {
    URL url;
    String urlString;
    ActionEvent e;
    String html;
    GetURL(String _urlString, ActionEvent _e)
    {
      e = _e;
      urlString = _urlString;
      url = checkURL();
      if(!(url == null))
        html = getHTML(urlString);
    }
    
    GetURL(String _urlString)
    {
      e = null;
      urlString = _urlString;
      url = checkURL();
      if(!(url == null))
        html = getHTML(urlString);
    }
    
    private String getHTML(String urlToRead) 
    {
      String html = ""; // A long string containing all the HTML
      try 
      {
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";
        while ((line = rd.readLine()) != null) 
        {
          html += line;
        }
        rd.close();
      } 
      catch (Exception e) 
      {
        e.printStackTrace();
      }
      return html;
    }
    
    private String getTitle()
    {
      String title = "";
      if(html.contains("<title>") && html.contains("</title>"))
        title = html.substring((html.indexOf("<title>")+7), (html.indexOf("</title>")));
      else
        title = url.toString();
      return title;
    }
    
    private URL checkURL()
    {
      if(!urlString.startsWith("http://"))
        urlString = "http://" + urlString;
      try 
      {
        if(!urlString.substring(7,11).equals("www."))
        {
          urlString = urlString.substring(7);
          urlString = "http://www." + urlString;
        }
        if(!urlString.substring(12).contains("."))
          urlString = urlString.substring(11);
        url = new URL(urlString);
      }
      catch(MalformedURLException ex) 
      {
        url = null;
      }
      catch(StringIndexOutOfBoundsException ex)
      {
        url = null;
      }
      return url;
    }
    
    public URL getURL()
    {
      return url;
    }
    public String getHTML()
    {
      return html;
    }
  }
  
  private class NewTabAction extends AbstractAction 
  {
    
    public NewTabAction() 
    {
      putValue(Action.NAME, "New Browser Tab");
      putValue(Action.SHORT_DESCRIPTION, "Create New Web Browser Tab");
      putValue(Action.MNEMONIC_KEY, new Integer('N'));
    }
    
    public void actionPerformed(ActionEvent event) 
    {
      tabbedPane.createBrowserPane();
    }
  }
  
  private class ExitAction extends AbstractAction 
  {
    public ExitAction() 
    {
      putValue(Action.NAME, "Exit");
      putValue(Action.SHORT_DESCRIPTION, "Exit Application");
      putValue(Action.MNEMONIC_KEY, new Integer('x'));
    }
    public void actionPerformed(ActionEvent event) 
    {
      System.exit(0);
    }
  }
  public class GetConnection extends HttpURLConnection
  {
    GetConnection(URL _url)
    {
      super(_url);
    }
    public boolean usingProxy(){return false;}
    public void disconnect(){}
    public void connect(){}
  }
  
  private String getDateTime()
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    return dateFormat.format(date);
  }
  
  public class BookmarkMenuItem extends JMenuItem //Kyle
  {
    GetURL gurl;
    BookmarkMenuItem(GetURL _gurl)
    {
      super(_gurl.getTitle());
      gurl = _gurl;
      addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
          tabbedPane.displayPage(gurl);
        }
      });
    }
  }
  
  public Boolean checkBookmark(GetURL grl) //Kyle
  {
    Boolean bool = false;
    for(GetURL gurl: bookmarks)
    {
      if(gurl.getURL().toString().equals(grl.getURL().toString()))
      {
        bool = true;
      }
    }
    return bool;
  }
  
  public static void main(String args[]) throws IOException 
  {
    WebBrowser browser = new WebBrowser();
    browser.setDefaultCloseOperation(EXIT_ON_CLOSE);
    browser.setSize(640, 480);
    browser.setVisible(true);
  }
}