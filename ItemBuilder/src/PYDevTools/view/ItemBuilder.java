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
	
	private ItemBuilder() {
		tabbedPane = new JTabbedPane();
		wepPanel = new WeaponPanel(tabbedPane);
		armorPanel = new ArmorPanel(tabbedPane);
		miscPanel = new MiscPanel(tabbedPane);
		
		this.add(tabbedPane);
	}
	
	public static void main(String[] args) {
		initializeFrame();
		
	}
	
	private static void initializeFrame() {
		JFrame IBFrame = new ItemBuilder();
		IBFrame.setTitle("PY ItemBuilder");
		IBFrame.setSize(1280, 720);
		IBFrame.setLocation(200, 100);
		IBFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		IBFrame.setVisible(true);
	}

}
