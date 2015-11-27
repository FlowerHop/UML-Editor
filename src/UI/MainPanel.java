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
	private final int MODES_NUM = 6;
	
	private final int MODE_SELECT = 0;
	private final int MODE_ASSOCIATION = 1;
	private final int MODE_GENERALIZATION = 2;
	private final int MODE_COMPOSITION = 3;
	private final int MODE_CLASS = 4;
	private final int MODE_USE_CASE = 5;
	
	private JButton[] _btns = new JButton[MODES_NUM];

    private CanvasArea _canvas;
    
	public MainPanel () {
		initComponents ();
		initLayoutConstraints ();
	}
	
	private void initComponents () {
	  _btns[MODE_SELECT] = new JButton ("Select");
	  _btns[MODE_ASSOCIATION] = new JButton ("Association Line");
	  _btns[MODE_GENERALIZATION] = new JButton ("Generalization Line");
	  _btns[MODE_COMPOSITION] = new JButton ("Composition Line");
	  _btns[MODE_CLASS] = new JButton ("Class");
	  _btns[MODE_USE_CASE] = new JButton ("Use Case");
	    
	  _canvas = new CanvasArea ();
	   
	  for (int i = 0; i < MODES_NUM; i++) {
		_btns[i].addActionListener (new UMLButtonListener ());
		_btns[i].setOpaque (true);
	  }
	}
	
	private void initLayoutConstraints () {
	  int btnStartX = 0;
	  int btnStartY = 0;
	  int canvasStartX = 1;
	  int canvasStartY = 0;
	
	  int btnGridWidth = 1;
	  int btnGridHeight = 1;
	  int canvasGridWidth = 10;
	  int canvasGridHeight = 10;
		
	  double btnWeightX = 0;
	  double btnWeightY = 1;
	  double canvasWeightX = 1;
	  double canvasWeightY = 1;
	 
      int defaultAnchor = GridBagConstraints.CENTER;
	  int defaultFill = GridBagConstraints.BOTH;
	  Insets defaultInsets = new Insets(0, 0, 0, 0);
	  int defaultIpadX = 0; 
	  int defaultIpadY = 0;
	  
	  GridBagLayout gridBagLayout = new GridBagLayout ();
		
	  GridBagConstraints bagSelect = new GridBagConstraints (btnStartX, btnStartY, 
			btnGridWidth, btnGridHeight, 
			btnWeightX, btnWeightY, 
			defaultAnchor, defaultFill, 
			defaultInsets, defaultIpadX, defaultIpadY);
	    

	  GridBagConstraints bagAssociation = new GridBagConstraints (btnStartX, btnStartY + 1, 
			btnGridWidth, btnGridHeight, 
			btnWeightX, btnWeightY, 
			defaultAnchor, defaultFill, 
			defaultInsets, defaultIpadX, defaultIpadY);
	    
	  GridBagConstraints bagGeneralization = new GridBagConstraints (btnStartX, btnStartY + 2, 
			btnGridWidth, btnGridHeight, 
			btnWeightX, btnWeightY, 
			defaultAnchor, defaultFill, 
			defaultInsets, defaultIpadX, defaultIpadY);
	    
	  GridBagConstraints bagComposition = new GridBagConstraints (btnStartX, btnStartY + 3, 
			btnGridWidth, btnGridHeight, 
			btnWeightX, btnWeightY, 
			defaultAnchor, defaultFill, 
			defaultInsets, defaultIpadX, defaultIpadY);
	    
	  GridBagConstraints bagClass = new GridBagConstraints (btnStartX, btnStartY + 4, 
			btnGridWidth, btnGridHeight, 
			btnWeightX, btnWeightY, 
			defaultAnchor, defaultFill, 
			defaultInsets, defaultIpadX, defaultIpadY);
	    
	  GridBagConstraints bagUseCase = new GridBagConstraints (btnStartX, btnStartY + 5, 
			btnGridWidth, btnGridHeight, 
			btnWeightX, btnWeightY, 
			defaultAnchor, defaultFill, 
			defaultInsets, defaultIpadX, defaultIpadY);
	    
	  GridBagConstraints bagCanvas = new GridBagConstraints (canvasStartX, canvasStartY,
	    	canvasGridWidth, canvasGridHeight, 
	    	canvasWeightX, canvasWeightY, 
	    	defaultAnchor, defaultFill, 
	    	defaultInsets, defaultIpadX, defaultIpadY);
	    
	  this.setLayout (gridBagLayout);
	    
	  this.add (_btns[MODE_SELECT], bagSelect);
	  this.add (_btns[MODE_ASSOCIATION], bagAssociation);
	  this.add (_btns[MODE_GENERALIZATION], bagGeneralization);
	  this.add (_btns[MODE_COMPOSITION], bagComposition);
	  this.add (_btns[MODE_CLASS], bagClass);
	  this.add (_btns[MODE_USE_CASE], bagUseCase);
	  this.add (_canvas, bagCanvas);
	}
    
    public void changeSelectedUMLObjectName (String name) {
      _canvas.getCurrentMode ().eidtName (name);
    }
    
    public void toGroup () {
    
    }
    
    public void toUnGroup () {
    	
    }
    
    class UMLButtonListener implements ActionListener {
    	private Mode[] _modes = new Mode[MODES_NUM];
    	
        public UMLButtonListener () {
          _modes[MODE_SELECT] = new SelectMode (_canvas);
      	  _modes[MODE_ASSOCIATION] = new AssociationMode (_canvas);
      	  _modes[MODE_GENERALIZATION] = new GeneralizationMode (_canvas);
      	  _modes[MODE_COMPOSITION] = new CompositionMode (_canvas);
      	  _modes[MODE_CLASS] = new ClassMode (_canvas);
      	  _modes[MODE_USE_CASE] = new UseCaseMode (_canvas);
        }
        
		@Override
		public void actionPerformed(ActionEvent e) {
		  int mode = -1;
		
          for (int i = 0; i < MODES_NUM; i++) {
	        if (e.getSource () == _btns[i]) {
		      mode = i;
		      _btns[i].setBackground (Color.BLACK);
		    } else _btns[i].setBackground (null);
		  }	
		    
		  _canvas.changeMode (_modes[mode]);
		}
    	
    }
}
