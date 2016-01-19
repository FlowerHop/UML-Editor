package UI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Modes.AssociationMode;
import Modes.ClassMode;
import Modes.CompositionMode;
import Modes.GeneralizationMode;
import Modes.Mode;
import Modes.SelectMode;
import Modes.UseCaseMode;

public class UMLEditor extends JFrame {
	private int FRAME_WIDTH = 1000;
	private int FRAME_HEIGHT = 600;
	
	private MenuBar _menuBar;
	private Menu _file;
	private Menu _edit;
	private MenuItem _groupItem;
	private MenuItem _unGroupItem;
	private MenuItem _changeNameItem;
	private UMLPanel _mainPanel;
	
    public UMLEditor () {
      super ();
        
      addWindowListener (new WindowAdapter () {
        public void windowClosing (WindowEvent e) {
          System.exit (0);
        }
      });
      
      initUMLPanel ();
      initMenuBar ();
        
      this.setSize (FRAME_WIDTH, FRAME_HEIGHT);
      setVisible (true);
    }
    
    private void initMenuBar () {
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

    private void initUMLPanel () {
      _mainPanel = new UMLPanel ();
      this.setContentPane (_mainPanel);
    }
}
