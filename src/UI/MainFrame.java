package UI;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modes.SelectMode;

public class MainFrame extends JFrame {
	private MenuBar _menuBar = new MenuBar ();
	private Menu _file;
	private Menu _edit;
	private MenuItem _groupItem;
	private MenuItem _unGroupItem;
	private MenuItem _changeNameItem;
	private MainPanel _mainPanel;
	
    public MainFrame () {
        super ();
        
        addWindowListener (new WindowAdapter () {
            public void windowClosing (WindowEvent e) {
              System.exit (0);
            }
        });
        
        initMenuBar ();
        _mainPanel = new MainPanel ();
        
        
        this.setContentPane (_mainPanel);
        
        
        this.setSize (1000, 600);
        setVisible (true);
    }
    
    public void initMenuBar () {
      _menuBar = new MenuBar ();
        
      _file = new Menu ("File");
      _edit = new Menu ("Edit");
        
      _groupItem = new MenuItem ("Group");
      _unGroupItem = new MenuItem ("UnGroup");
      _changeNameItem = new MenuItem ("change object name");
      
      _groupItem.addActionListener (new ActionListener () {
		@Override
		public void actionPerformed (ActionEvent e) {
		  _mainPanel.toGroup ();
		}
      });
      
      _unGroupItem.addActionListener (new ActionListener () {
		@Override
		public void actionPerformed (ActionEvent e) {
		  _mainPanel.toUnGroup ();
		}
      });
      
      _changeNameItem.addActionListener (new ActionListener () {
		@Override
		public void actionPerformed (ActionEvent e) {
		  String updatedName = JOptionPane.showInputDialog (null, "Edit name");
			
		  if (updatedName != null) {
		    _mainPanel.changeSelectedUMLObjectName (updatedName);
	      }
		}
	  });
      
      _edit.add (_groupItem);
      _edit.add (_unGroupItem);
      _edit.add (_changeNameItem);      
        
      _menuBar.add (_file);
      _menuBar.add (_edit);
      this.setMenuBar (_menuBar);
    }
  }
