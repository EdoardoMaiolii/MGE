package view;

import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
  private static final long serialVersionUID = -1256994087403826079L;
  private final JPanel mainPanel;
  public MyFrame(final String title, final LayoutManager lm) {
      super(title);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.mainPanel = new JPanel(lm);
      this.getContentPane().add(this.mainPanel);
  }
  public final JPanel getMainPanel() {
      return this.mainPanel;
  }
}
