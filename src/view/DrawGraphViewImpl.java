package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import control.DrawGraphViewObserver;
import model.Constants;
import model.Digits;
import model.MathFunctions;
import model.Punctuation;
import model.Settings;
import model.Variables;

public class DrawGraphViewImpl implements DrawGraphView {
  private static final String INPUT_FRAME_NAME = "Mathematical Graphic Engine";
  private static final String GRAPH_FRAME_NAME = "Graph";
  private static final String NORTH_PANEL_TITLE = "Math Expression";
  private static final String WEST_PANEL_NAME = "Settings";
  private static final String INNER_NORTH_PANEL_NAME = "Variables";
  private static final String INNER_WEST_PANEL_NAME = "Functions";
  private static final String INNER_CENTER_PANEL_NAME = "Digits";
  private static final String INNER_EAST_PANEL_NAME = "Costants";
  private static final String MATH_EXPRESSION_NAME = "F=";
  private static final String CLEAR = "CLEAR";
  private static final String LOAD = "LOAD";
  private static final String SAVE = "SAVE";
  private static final String PLOT = "PLOT";
  private static final int MATH_EXPRESSION_LENGTH = 70;
  private static final int SETTINGS_LENGTH = 20;
  private static final int INNER_NORTH_PANEL_ROWS = 1;
  private static final int INNER_NORTH_PANEL_COLUMNS = 6;
  private static final int INNER_WEST_PANEL_ROWS = 6;
  private static final int INNER_WEST_PANEL_COLUMNS = 4;
  private static final int INNER_CENTER_PANEL_ROWS = 5;
  private static final int INNER_CENTER_PANEL_COLUMNS = 2;
  private static final int INNER_EAST_PANEL_ROWS = 6;
  private static final int INNER_EAST_PANEL_COLUMNS = 2;
  private static final int INNER_SOUTH_PANEL_ROWS = 1;
  private static final int INNER_SOUTH_PANEL_COLUMNS = 4;
  
  private DrawGraphViewObserver observer;
  private final MyFrame inputFrame = new MyFrame(INPUT_FRAME_NAME, new BorderLayout());
  private final MyFrame graphFrame = new MyFrame(GRAPH_FRAME_NAME, new BorderLayout());
  private final Set<JButton> inputButtons = new HashSet<>();
  
  public DrawGraphViewImpl() {
    //North Panel
    final JPanel pNorth = new JPanel(new FlowLayout());
    final JLabel lMathExpression = new JLabel(MATH_EXPRESSION_NAME);
    final JTextField tMathExpression = new JTextField(MATH_EXPRESSION_LENGTH);
    pNorth.setBorder(new TitledBorder(NORTH_PANEL_TITLE));
    pNorth.add(lMathExpression);
    pNorth.add(tMathExpression);
    //West Panel
    final JPanel pWest = new JPanel(new GridBagLayout());
    final GridBagConstraints gbcWest = new GridBagConstraints();
    pWest.setBorder(new TitledBorder(WEST_PANEL_NAME));
    gbcWest.gridy = 0;
    for (final String setting : Settings.names()) {
      final JLabel lSetting = new JLabel(setting);
      final JTextField tSetting = new JTextField(SETTINGS_LENGTH);
      pWest.add(lSetting, gbcWest);
      pWest.add(tSetting, gbcWest);
      gbcWest.gridy = gbcWest.gridy + 1;
    }
    //Center Panel
    final JPanel pCenter = new JPanel(new BorderLayout());
    //Inner North Panel
    final JPanel pInnerNorth = this.gridButtonsPanel(INNER_NORTH_PANEL_ROWS, INNER_NORTH_PANEL_COLUMNS, Variables.names());
    pInnerNorth.setBorder(new TitledBorder(INNER_NORTH_PANEL_NAME));
    //Inner West Panel
    final JPanel pInnerWest = this.gridButtonsPanel(INNER_WEST_PANEL_ROWS, INNER_WEST_PANEL_COLUMNS, MathFunctions.names());
    pInnerWest.setBorder(new TitledBorder(INNER_WEST_PANEL_NAME));
    //Inner Center Panel
    final JPanel pInnerCenter = this.gridButtonsPanel(INNER_CENTER_PANEL_ROWS, INNER_CENTER_PANEL_COLUMNS, Digits.names());
    pInnerCenter.setBorder(new TitledBorder(INNER_CENTER_PANEL_NAME));
    //Inner East Panel
    final JPanel pInnerEast = this.gridButtonsPanel(INNER_EAST_PANEL_ROWS, INNER_EAST_PANEL_COLUMNS, Constants.names());
    pInnerEast.setBorder(new TitledBorder(INNER_EAST_PANEL_NAME));
    //Inner South Panel
    final JPanel pInnerSouth = this.gridButtonsPanel(INNER_SOUTH_PANEL_ROWS, INNER_SOUTH_PANEL_COLUMNS, Punctuation.names());
    pCenter.add(pInnerNorth, BorderLayout.NORTH);
    pCenter.add(pInnerWest, BorderLayout.WEST);
    pCenter.add(pInnerCenter, BorderLayout.CENTER);
    pCenter.add(pInnerEast, BorderLayout.EAST);
    pCenter.add(pInnerSouth, BorderLayout.SOUTH);
    //East Panel
    final JPanel pEast = new JPanel(new FlowLayout());
    final JButton bClear = new JButton(CLEAR);
    pEast.add(bClear);
    //South Panel
    final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    final JButton bLoad = new JButton(LOAD);
    final JButton bSave = new JButton(SAVE);
    final JButton bPlot = new JButton(PLOT);   
    pSouth.add(bLoad);
    pSouth.add(bSave);
    pSouth.add(bPlot);
    this.inputFrame.getMainPanel().add(pNorth, BorderLayout.NORTH);
    this.inputFrame.getMainPanel().add(pWest, BorderLayout.WEST);
    this.inputFrame.getMainPanel().add(pCenter, BorderLayout.CENTER);
    this.inputFrame.getMainPanel().add(pEast, BorderLayout.EAST);
    this.inputFrame.getMainPanel().add(pSouth, BorderLayout.SOUTH);
    this.inputFrame.setResizable(true);
    this.inputFrame.pack();
    
    for(final JButton jb : this.inputButtons) {
      jb.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          tMathExpression.setText(tMathExpression.getText()+jb.getText());
        }
 
      });
    }
    
    bClear.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        tMathExpression.setText("");
      }
    });
    
    bLoad.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
      }
    });
    
    bSave.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
      }
    });
    
    bPlot.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        observer.newExpression(tMathExpression.getText());
      }
    });
  }
  
  @Override
  public void start() {
    this.inputFrame.setVisible(true);
  }
  
  @Override
  public void setObserver(DrawGraphViewObserver observer) {
    this.observer = observer;
  }
  
  @Override
  public void expressionIncorrect() {
    // TODO Auto-generated method stub
    
  }
  
  private JPanel gridButtonsPanel(int rows, int cols, Set<String> labels) {
    final Iterator<String> labelsIterator = labels.iterator();
    final JPanel panel = new JPanel(new GridBagLayout());
    final GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;    
    gbc.fill = GridBagConstraints.HORIZONTAL;
    for(int i=0; i<rows; i++) {
      for(int j=0; j<cols; j++) {
        if(labelsIterator.hasNext()) {
          final JButton jb = new JButton(labelsIterator.next());
          gbc.gridx = j;
          gbc.gridy = i;
          panel.add(jb, gbc);
          this.inputButtons.add(jb);
        }
        else {
          break;
        }
      }
    }
    return panel;
  }
  
}
