/**
 * 
 */
package PYDevTools.view;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * @author Alfeey
 *
 */
@SuppressWarnings("serial")
public class ItemBuilder extends JFrame {
	
	JTabbedPane tabbedPane;
	WeaponPanel wepPanel;
	ArmorPanel armorPanel;
	MiscPanel miscPanel;
	ImageIcon weaponTabIcon, armorTabIcon, miscTabIcon;
	
	private ItemBuilder() {
		// Tabbed Pane
		tabbedPane = new JTabbedPane();
		// Weapon Tab
		wepPanel = new WeaponPanel();
		weaponTabIcon = new ImageIcon("src/icons/thunderfury-icon.gif");
		tabbedPane.addTab("Weapon", weaponTabIcon, wepPanel, "Build Weapons");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		// Armor Tab
		armorPanel = new ArmorPanel();
		armorTabIcon = new ImageIcon("src/icons/WoWIcons/INV_Chest_Plate03.png");
		tabbedPane.addTab("Armor",  armorTabIcon, armorPanel, "Build Armor");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		// Misc Tab
		miscPanel = new MiscPanel();
		miscTabIcon = new ImageIcon("src/icons/WoWIcons/INV_Inscription_RunescrollOfFortitude_Blue.png");
		tabbedPane.addTab("Misc", miscTabIcon, miscPanel, "Build Misc Items");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		
		this.add(tabbedPane);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initializeFrame();
			}
		});
	}
	
	private static void initializeFrame() {
		JFrame IBFrame = new ItemBuilder();
		IBFrame.setTitle("PY ItemBuilder");
		IBFrame.setSize(1440, 800);
		IBFrame.setLocation(200, 100);
		IBFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		IBFrame.setVisible(true);
	}

}
