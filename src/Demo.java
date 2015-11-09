import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Demo extends JFrame {

  public Demo() {
    super();

    GridBagLayout layout = new GridBagLayout();
    this.getContentPane().setLayout(layout);
    
    GridBagConstraints bagSelect = new GridBagConstraints();
    bagSelect.gridx = 0;
    bagSelect.gridy = 0;
    bagSelect.gridwidth = 1;
    bagSelect.gridheight = 1;
    bagSelect.weightx = 1;
    bagSelect.weighty = 1;
    bagSelect.fill = GridBagConstraints.BOTH;
    bagSelect.anchor = GridBagConstraints.CENTER;

    GridBagConstraints bagAssociation = new GridBagConstraints();
    bagAssociation.gridx = 0;
    bagAssociation.gridy = 1;
    bagAssociation.gridwidth = 1;
    bagAssociation.gridheight = 1;
    bagAssociation.weightx = 1;
    bagAssociation.weighty = 1;
    bagAssociation.fill = GridBagConstraints.BOTH;
    bagAssociation.anchor = GridBagConstraints.CENTER;
    
    GridBagConstraints bagGeneralization = new GridBagConstraints();
    bagGeneralization.gridx = 0;
    bagGeneralization.gridy = 2;
    bagGeneralization.gridwidth = 1;
    bagGeneralization.gridheight = 1;
    bagGeneralization.weightx = 1;
    bagGeneralization.weighty = 1;
    bagGeneralization.fill = GridBagConstraints.BOTH;
    bagGeneralization.anchor = GridBagConstraints.CENTER;
    
    GridBagConstraints bagComposition = new GridBagConstraints();
    bagComposition.gridx = 0;
    bagComposition.gridy = 3;
    bagComposition.gridwidth = 1;
    bagComposition.gridheight = 1;
    bagComposition.weightx = 1;
    bagComposition.weighty = 1;
    bagComposition.fill = GridBagConstraints.BOTH;
    bagComposition.anchor = GridBagConstraints.CENTER;
    
    GridBagConstraints bagClass = new GridBagConstraints();
    bagClass.gridx = 0;
    bagClass.gridy = 4;
    bagClass.gridwidth = 1;
    bagClass.gridheight = 1;
    bagClass.weightx = 1;
    bagClass.weighty = 1;
    bagClass.fill = GridBagConstraints.BOTH;
    bagClass.anchor = GridBagConstraints.CENTER;
    
    GridBagConstraints bagUseCase = new GridBagConstraints();
    bagUseCase.gridx = 0;
    bagUseCase.gridy = 5;
    bagUseCase.gridwidth = 1;
    bagUseCase.gridheight = 1;
    bagUseCase.weightx = 1;
    bagUseCase.weighty = 1;
    bagUseCase.fill = GridBagConstraints.BOTH;
    bagUseCase.anchor = GridBagConstraints.CENTER;
    
    GridBagConstraints bagCanvas = new GridBagConstraints();
    bagCanvas.gridx = 1;
    bagCanvas.gridy = 0;
    bagCanvas.gridwidth = 10;
    bagCanvas.gridheight = 10;
    bagCanvas.weightx = 10;
    bagCanvas.weighty = 10;
    bagCanvas.fill = GridBagConstraints.BOTH;
    bagCanvas.anchor = GridBagConstraints.CENTER;
    
    JButton btnSelect = new JButton("Select");
    JButton btnAssociation = new JButton("Association Line");
    JButton btnGeneralization = new JButton("Generalization Line");
    JButton btnComposition = new JButton("Composition Line");
    JButton btnClass = new JButton("Class");
    JButton btnUseCase = new JButton("Use Case");
    
    CanvasArea canvas = new CanvasArea();
    
    
    
    
    
    this.getContentPane().add(btnSelect, bagSelect);
    this.getContentPane().add(btnAssociation, bagAssociation);
    this.getContentPane().add(btnGeneralization, bagGeneralization);
    this.getContentPane().add(btnComposition, bagComposition);
    this.getContentPane().add(btnClass, bagClass);
    this.getContentPane().add(btnUseCase, bagUseCase);
    this.getContentPane().add(canvas, bagCanvas);
    
   
    // it doesn't matter
//    JPanel panel = new JPanel();
//    panel.setLayout(new GridLayout(1, 2));
//    panel.add(new JLabel("x,y: ", JLabel.RIGHT));
//    location = new JLabel("");
//    panel.add(location);
//
//    container.add(panel, BorderLayout.SOUTH);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    
    this.setSize(1000, 600);
    setVisible(true);
  }

    public static void main(String arg[]) {
    new Demo();
  }

  
}