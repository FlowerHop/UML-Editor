import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MainPanel extends JPanel {
	private final int MODES_NUM = 6;
	private JButton _btnSelect;
    private JButton _btnAssociation;
    private JButton _btnGeneralization;
    private JButton _btnComposition;
    private JButton _btnClass;
    private JButton _btnUseCase;

    private CanvasArea _canvas;
    
    private Mode[] _modes = new Mode[MODES_NUM];
    
	public MainPanel () {
		initComponents ();
		initLayoutConstraints ();
	}
	
	private void initComponents () {
	  _btnSelect = new JButton ("Select");
	  _btnAssociation = new JButton ("Association Line");
	  _btnGeneralization = new JButton ("Generalization Line");
	  _btnComposition = new JButton ("Composition Line");
	  _btnClass = new JButton ("Class");
	  _btnUseCase = new JButton ("Use Case");
	    
	  _canvas = new CanvasArea ();
	  
	  _btnSelect.addActionListener (new ActionListener () {
		@Override
		public void actionPerformed (ActionEvent e) {
		  // TODO Auto-generated method stub
		  if (_modes[0] == null)
			_modes[0] = new SelectMode (_canvas);
			
		  _canvas.changeMode (_modes[0]);
		}
	  });
	  
	  _btnClass.addActionListener (new ActionListener () {
		@Override
		public void actionPerformed (ActionEvent e) {
		  // TODO Auto-generated method stub
		  if (_modes[4] == null) 
			_modes[4] = new ClassMode (_canvas);
			_canvas.changeMode (_modes[4]);
		}
	  });
	  
	  _btnUseCase.addActionListener (new ActionListener () {
		@Override
		public void actionPerformed (ActionEvent e) {
	   	  if (_modes[5] == null)
		    _modes[5] = new UseCaseMode (_canvas);
			_canvas.changeMode (_modes[5]);
		}
	  });
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
	    
	  this.add (_btnSelect, bagSelect);
	  this.add (_btnAssociation, bagAssociation);
	  this.add (_btnGeneralization, bagGeneralization);
	  this.add (_btnComposition, bagComposition);
	  this.add (_btnClass, bagClass);
	  this.add (_btnUseCase, bagUseCase);
	  this.add (_canvas, bagCanvas);
	}
}
