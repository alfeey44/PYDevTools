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
public class ArmorPanel implements IPanel{
	
	JComponent armorPanel;
	ImageIcon armorTabIcon;
	
	public ArmorPanel(JTabbedPane tabbedPane) {
		armorPanel = makeTextPanel("Armor");
		armorTabIcon = createImageIcon("/icons/Inv_helmet_25.png");
		tabbedPane.addTab("Armor",  armorTabIcon, armorPanel, "Build Armor");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
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
