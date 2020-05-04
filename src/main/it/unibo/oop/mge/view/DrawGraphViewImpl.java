package it.unibo.oop.mge.view;

import it.unibo.oop.mge.c3d.geometry.Segment2D;
import it.unibo.oop.mge.control.DrawGraphViewObserver;
import it.unibo.oop.mge.libraries.Constants;
import it.unibo.oop.mge.libraries.Digits;
import it.unibo.oop.mge.libraries.MathFunctions;
import it.unibo.oop.mge.libraries.Punctuation;
import it.unibo.oop.mge.libraries.Variables;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public final class DrawGraphViewImpl implements DrawGraphView {
    private static final String INPUT_FRAME_NAME = "Mathematical Graphic Engine";
    private static final String GRAPH_FRAME_NAME = "Graph";
    private static final String NORTH_PANEL_TITLE = "Math Expression";
    private static final String WEST_PANEL_NAME = "Settings";
    private static final String INNER_NORTH_PANEL_NAME = "Variables";
    private static final String INNER_WEST_PANEL_NAME = "Functions";
    private static final String INNER_CENTER_PANEL_NAME = "Digits";
    private static final String INNER_EAST_PANEL_NAME = "Costants";
    private static final String MATH_EXPRESSION_NAME = "F=";
    private static final String MAX_VALUE = "Max";
    private static final String MIN_VALUE = "Min";
    private static final String RATE = "Rate";
    private static final String CLEAR = "CLEAR";
    private static final String LOAD = "LOAD";
    private static final String SAVE = "SAVE";
    private static final String PLOT = "PLOT";
    private static final String ZOOM_IN = "+";
    private static final String ZOOM_OUT = "-";
    private static final String UP = "UP";
    private static final String LEFT = "LEFT";
    private static final String RIGHT = "RIGHT";
    private static final String DOWN = "DOWN";
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
    private static final int GRAPH_PANEL_SIZE = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
    private DrawGraphViewObserver observer;
    private final MyFrame inputFrame = new MyFrame(INPUT_FRAME_NAME, new BorderLayout());
    private final MyFrame graphFrame = new MyFrame(GRAPH_FRAME_NAME, new BorderLayout());
    private final Set<JButton> inputButtons = new HashSet<>();

    public DrawGraphViewImpl() {
        final JPanel pNorth = new JPanel(new FlowLayout());
        final JLabel lMathExpression = new JLabel(MATH_EXPRESSION_NAME);
        final JTextField tMathExpression = new JTextField(MATH_EXPRESSION_LENGTH);
        pNorth.setBorder(new TitledBorder(NORTH_PANEL_TITLE));
        pNorth.add(lMathExpression);
        pNorth.add(tMathExpression);
        final JPanel pWest = new JPanel(new GridBagLayout());
        final GridBagConstraints gbcWest = new GridBagConstraints();
        pWest.setBorder(new TitledBorder(WEST_PANEL_NAME));
        gbcWest.gridy = 0;
        final JLabel lMax = new JLabel(MAX_VALUE);
        final JTextField tMax = new JTextField(SETTINGS_LENGTH);
        pWest.add(lMax, gbcWest);
        pWest.add(tMax, gbcWest);
        gbcWest.gridy = gbcWest.gridy + 1;
        final JLabel lMin = new JLabel(MIN_VALUE);
        final JTextField tMin = new JTextField(SETTINGS_LENGTH);
        pWest.add(lMin, gbcWest);
        pWest.add(tMin, gbcWest);
        gbcWest.gridy = gbcWest.gridy + 1;
        final JLabel lRate = new JLabel(RATE);
        final JTextField tRate = new JTextField(SETTINGS_LENGTH);
        pWest.add(lRate, gbcWest);
        pWest.add(tRate, gbcWest);
        final JPanel pCenter = new JPanel(new BorderLayout());
        final JPanel pInnerNorth = this.gridButtonsPanel(INNER_NORTH_PANEL_ROWS, INNER_NORTH_PANEL_COLUMNS, Variables.names(), this.inputButtons);
        pInnerNorth.setBorder(new TitledBorder(INNER_NORTH_PANEL_NAME));
        final JPanel pInnerWest = this.gridButtonsPanel(INNER_WEST_PANEL_ROWS, INNER_WEST_PANEL_COLUMNS, MathFunctions.names(), this.inputButtons);
        pInnerWest.setBorder(new TitledBorder(INNER_WEST_PANEL_NAME));
        final JPanel pInnerCenter = this.gridButtonsPanel(INNER_CENTER_PANEL_ROWS, INNER_CENTER_PANEL_COLUMNS, Digits.names(), this.inputButtons);
        pInnerCenter.setBorder(new TitledBorder(INNER_CENTER_PANEL_NAME));
        final JPanel pInnerEast = this.gridButtonsPanel(INNER_EAST_PANEL_ROWS, INNER_EAST_PANEL_COLUMNS, Constants.names(), this.inputButtons);
        pInnerEast.setBorder(new TitledBorder(INNER_EAST_PANEL_NAME));
        final JPanel pInnerSouth = this.gridButtonsPanel(INNER_SOUTH_PANEL_ROWS, INNER_SOUTH_PANEL_COLUMNS, Punctuation.names(), this.inputButtons);
        pCenter.add(pInnerNorth, BorderLayout.NORTH);
        pCenter.add(pInnerWest, BorderLayout.WEST);
        pCenter.add(pInnerCenter, BorderLayout.CENTER);
        pCenter.add(pInnerEast, BorderLayout.EAST);
        pCenter.add(pInnerSouth, BorderLayout.SOUTH);
        final JPanel pEast = new JPanel(new FlowLayout());
        final JButton bClear = new JButton(CLEAR);
        pEast.add(bClear);
        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton bLoad = new JButton(LOAD);
        final JButton bSave = new JButton(SAVE);
        final JButton bPlot = new JButton(PLOT);
        pSouth.add(bLoad);
        pSouth.add(bSave);
        pSouth.add(bPlot);
        final JPanel pSouthGraph = new JPanel(new FlowLayout());
        final JButton bZoomIn = new JButton(ZOOM_IN);
        final JButton bZoomOut = new JButton(ZOOM_OUT);
        final JButton bUp = new JButton(UP);
        final JButton bLeft = new JButton(LEFT);
        final JButton bRight = new JButton(RIGHT);
        final JButton bDown = new JButton(DOWN);
        pSouthGraph.add(bZoomIn);
        pSouthGraph.add(bZoomOut);
        pSouthGraph.add(bUp);
        pSouthGraph.add(bLeft);
        pSouthGraph.add(bRight);
        pSouthGraph.add(bDown);
        this.inputFrame.getMainPanel().add(pNorth, BorderLayout.NORTH);
        this.inputFrame.getMainPanel().add(pWest, BorderLayout.WEST);
        this.inputFrame.getMainPanel().add(pCenter, BorderLayout.CENTER);
        this.inputFrame.getMainPanel().add(pEast, BorderLayout.EAST);
        this.inputFrame.getMainPanel().add(pSouth, BorderLayout.SOUTH);
        //this.graphFrame.getMainPanel().add(pSouthGraph, BorderLayout.EAST);
        this.inputFrame.pack();
        this.graphFrame.pack();
        for (final JButton jb : this.inputButtons) {
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    tMathExpression.setText(tMathExpression.getText() + jb.getText());
                }
            });
        }
        bClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                tMathExpression.setText("");
            }
        });
        bLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.load();
            }
        });
        bSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.save();
            }
        });
        bPlot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    observer.newGraph(tMathExpression.getText(), 
                            Double.parseDouble(tMax.getText()), Double.parseDouble(tMin.getText()), Double.parseDouble(tRate.getText()));
                    graphFrame.pack();
                    graphFrame.setVisible(true);
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(inputFrame, "Invalid settings...");
                }
            }
        });
        bZoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.zoomIn();
            }
        });
        bZoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.zoomOut();
            }
        });
        bUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.moveUp();
            }
        });
        bLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.moveLeft();
            }
        });
        bRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.moveRight();
            }
        });
        bDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.moveDown();
            }
        });
    }

    @Override
    public void start() {
        this.inputFrame.setVisible(true);
    }

    @Override
    public void setObserver(final DrawGraphViewObserver observer) {
        this.observer = observer;
    }

    @Override
    public void expressionIncorrect() {
        JOptionPane.showMessageDialog(this.inputFrame, "Enter a valid expression please...");
    }

    @Override
    public void plotGraph(final List<Segment2D> segments) {
        this.graphFrame.add(new PlotFunctionPanel(GRAPH_PANEL_SIZE, segments), BorderLayout.CENTER);
    }

    private JPanel gridButtonsPanel(final int rows, final int cols, final List<String> labels, final Set<JButton> buttons) {
        final Iterator<String> labelsIterator = labels.iterator();
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (labelsIterator.hasNext()) {
                    final JButton jb = new JButton(labelsIterator.next());
                    gbc.gridx = j;
                    gbc.gridy = i;
                    panel.add(jb, gbc);
                    buttons.add(jb);
                } else {
                    break;
                }
            }
        }
        return panel;
    }
}
