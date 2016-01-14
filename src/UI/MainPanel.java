 package UI;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Modes.AssociationMode;
import Modes.ClassMode;
import Modes.CompositionMode;
import Modes.GeneralizationMode;
import Modes.Mode;
import Modes.SelectMode;
import Modes.UseCaseMode;

public class MainPanel extends JPanel {
	private ButtonPanel _btnPanel;
	private CanvasArea _canvas;
    
	public MainPanel () {
      initComponents ();
	  initLayoutConstraints ();
	}
	
	private void initComponents () {
      _canvas = CanvasArea.getInstance ();
	  _btnPanel = new ButtonPanel ();
	}
	
	private void initLayoutConstraints () {
	  int btnPanelStartX = 0;
	  int btnPanelStartY = 0;
	  int canvasStartX = 1;
	  int canvasStartY = 0;
	
	  int btnPanelGridWidth = 1;
	  int btnPanelGridHeight = 1;
	  int canvasGridWidth = 10;
	  int canvasGridHeight = 10;
		
	  double btnPanelWeightX = 0;
	  double btnPanelWeightY = 1;
	  double canvasWeightX = 1;
	  double canvasWeightY = 1;
	 
      int defaultAnchor = GridBagConstraints.CENTER;
	  int defaultFill = GridBagConstraints.BOTH;
	  Insets defaultInsets = new Insets(0, 0, 0, 0);
	  int defaultIpadX = 0; 
	  int defaultIpadY = 0;
	  
	  GridBagLayout gridBagLayout = new GridBagLayout ();
	  
	  GridBagConstraints bagButtonPanel = new GridBagConstraints (btnPanelStartX, btnPanelStartY,
  	    	btnPanelGridWidth, btnPanelGridHeight, 
  	    	btnPanelWeightX, btnPanelWeightY, 
  	    	defaultAnchor, defaultFill, 
  	    	defaultInsets, defaultIpadX, defaultIpadY);
	  
	  GridBagConstraints bagCanvas = new GridBagConstraints (canvasStartX, canvasStartY,
	    	canvasGridWidth, canvasGridHeight, 
	    	canvasWeightX, canvasWeightY, 
	    	defaultAnchor, defaultFill, 
	    	defaultInsets, defaultIpadX, defaultIpadY);
	    
	  this.setLayout (gridBagLayout);
	  
	  this.add (_btnPanel, bagButtonPanel);
	  this.add (_canvas, bagCanvas);
	}
    
    public void changeSelectedUMLObjectName (String name) {
      Mode currentMode = _canvas.getCurrentMode ();
      
      if (currentMode == null) {
    	return;
      }
      
      currentMode.eidtName (name);
    }
    
    public void toGroup () {
      Mode currentMode = _canvas.getCurrentMode ();
        
      if (currentMode == null) {
      	return;
      }
        
      currentMode.toGroup ();
    }
    
    public void toUnGroup () {
      Mode currentMode = _canvas.getCurrentMode ();
        
      if (currentMode == null) {
      	return;
      }
        
      currentMode.toUnGroup ();
    }
    
    class UMLButton extends JButton {
        private Mode _mode;
        
        public UMLButton (String name, Mode mode) {
      	  super (name);
      	  _mode = mode;
      	  this.setOpaque (true);
        }
        
        public void setMode (CanvasArea canvas) {
          canvas.changeMode (_mode);
        }
    }
 
    class ButtonPanel extends JPanel {
    	private final int MODE_SELECT = 0;
    	private final int MODE_ASSOCIATION = 1;
    	private final int MODE_GENERALIZATION = 2;
    	private final int MODE_COMPOSITION = 3;
    	private final int MODE_CLASS = 4;
    	private final int MODE_USE_CASE = 5;
    	private final int MODES_NUM = 6;
    	
    	private UMLButton[] _umlBtns = new UMLButton[MODES_NUM];
    	
        public ButtonPanel () {
          initComponents ();
          initLayoutConstraints ();
        }
        
        public void initComponents () {
          _umlBtns[MODE_SELECT] = new UMLButton ("Select", new SelectMode (_canvas));
          _umlBtns[MODE_ASSOCIATION] = new UMLButton ("Association Line", new AssociationMode (_canvas));
          _umlBtns[MODE_GENERALIZATION] = new UMLButton ("Generalization Line", new GeneralizationMode (_canvas));
          _umlBtns[MODE_COMPOSITION] = new UMLButton ("Composition Line", new CompositionMode (_canvas));
          _umlBtns[MODE_CLASS] = new UMLButton ("Class", new ClassMode (_canvas));
          _umlBtns[MODE_USE_CASE] = new UMLButton ("Use Case", new UseCaseMode (_canvas));
          
          for (int i = 0; i < MODES_NUM; i++) {
        	_umlBtns[i].addActionListener (new ActionListener () {
			  @Override
			  public void actionPerformed (ActionEvent e) {
				int currentIndex = -1;
				
		        for (int i = 0; i < MODES_NUM; i++) {
			      if (e.getSource () == _umlBtns[i]) {
			    	currentIndex = i;
			    	_umlBtns[i].setBackground (Color.BLACK);
			      } else _umlBtns[i].setBackground (null);
				}	
				    
		        _umlBtns[currentIndex].setMode (_canvas);
			  }
        	});
          }
        }
        
        public void initLayoutConstraints () {
          int btnStartX = 0;
      	  int btnStartY = 0;
      	  int canvasStartX = 1;
      	  int canvasStartY = 0;
      	
      	  int btnGridWidth = 1;
      	  int btnGridHeight = 1;
      		
      	  double btnWeightX = 0;
      	  double btnWeightY = 1;
      	 
          int defaultAnchor = GridBagConstraints.CENTER;
      	  int defaultFill = GridBagConstraints.BOTH;
      	  Insets defaultInsets = new Insets(0, 0, 0, 0);
      	  int defaultIpadX = 0; 
      	  int defaultIpadY = 0;
      	  
      	  GridBagLayout gridBagLayout = new GridBagLayout ();
      	  this.setLayout (gridBagLayout);
      	
      	  GridBagConstraints[] bags = new GridBagConstraints[MODES_NUM];
      	  
      	  for (int i = 0; i < MODES_NUM; i++) {
      		bags[i] = new GridBagConstraints (btnStartX, btnStartY + i, 
          			btnGridWidth, btnGridHeight, 
          			btnWeightX, btnWeightY, 
          			defaultAnchor, defaultFill, 
          			defaultInsets, defaultIpadX, defaultIpadY);	
      		this.add (_umlBtns[i], bags[i]);
      	  }
        }
    }
}
