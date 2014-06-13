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
public class WeaponPanel implements IPanel{
	
	JComponent weaponPanel;
	ImageIcon weaponTabIcon;
	
	public WeaponPanel(JTabbedPane tabbedPane) {
		weaponPanel = makeTextPanel("Weapon");
		weaponTabIcon = createImageIcon("/icons/thunderfury-icon.gif");
		tabbedPane.addTab("Weapon", weaponTabIcon, weaponPanel, "Build Weapons");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
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
