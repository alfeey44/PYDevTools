/**
 * 
 */
package PYDevTools.view;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * @author alfeey44
 *
 */
public class MiscPanel implements IPanel{
	
	JComponent miscPanel;
	ImageIcon miscTabIcon;
	
	public MiscPanel(JTabbedPane tabbedPane) {
		miscPanel = makeTextPanel("Misc");
		miscTabIcon = createImageIcon("/icons/Inv_misc_questionmark.png");
		tabbedPane.addTab("Misc", miscTabIcon, miscPanel, "Build Misc Items");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
	}

	@Override
	public JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
	
	@Override
	/** Returns an ImageIcon, or null if the path was invalid. */
    public ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ItemBuilder.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
