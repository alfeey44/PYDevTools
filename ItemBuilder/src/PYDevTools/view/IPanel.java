package PYDevTools.view;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public interface IPanel {
	JComponent makeTextPanel(String text);
	ImageIcon createImageIcon(String path);
}
