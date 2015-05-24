/**
 * 
 */
package PYDevTools.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.*;

/**
 * @author Alfeey
 *
 */
@SuppressWarnings("serial")
public class ItemBuilder extends JFrame implements ActionListener {
	
	static JFrame IBFrame, settingsFrame;
	JTabbedPane tabbedPane;
	WeaponPanel wepPanel;
	ArmorPanel armorPanel;
	MiscPanel miscPanel;
	DbPanel dbPanel;
	static SettingsPanel settingsPanel = SettingsPanel.getInstance();
	ImageIcon weaponTabIcon, armorTabIcon, miscTabIcon, dbTabIcon;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem1;
	
	private ItemBuilder() {
		
		// Build menu
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_M);
		menuBar.add(menu);
		menuItem1 = new JMenuItem("Settings", new ImageIcon("src/icons/Cogwheel.png"));
		menuItem1.setMnemonic(KeyEvent.VK_S);
		menuItem1.addActionListener(this);
		menuItem1.setActionCommand("settings");
		menu.add(menuItem1);
		
		this.setJMenuBar(menuBar);
		
		// Tabbed Pane
		tabbedPane = new JTabbedPane();
		// Weapon Tab
		wepPanel = new WeaponPanel();
		weaponTabIcon = new ImageIcon("src/icons/thunderfury-icon.gif");
		tabbedPane.addTab("Weapon", weaponTabIcon, wepPanel, "Build Weapons");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_W);
		// Armor Tab
		armorPanel = new ArmorPanel();
		armorTabIcon = new ImageIcon("src/icons/WoWIcons/INV_Chest_Plate03.png");
		tabbedPane.addTab("Armor",  armorTabIcon, armorPanel, "Build Armor");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_A);
		// Misc Tab
		miscPanel = new MiscPanel();
		miscTabIcon = new ImageIcon("src/icons/WoWIcons/INV_Inscription_RunescrollOfFortitude_Blue.png");
		tabbedPane.addTab("Misc", miscTabIcon, miscPanel, "Build Misc Items");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_M);
		// DB Tab
		dbPanel = new DbPanel();
		dbTabIcon = new ImageIcon("src/icons/dbicon.jpg");
		tabbedPane.addTab("Database", dbTabIcon, dbPanel, "Search for Items in the Database");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_D);
		
		this.add(tabbedPane);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initializeFrames();
			}
		});
	}
	
	private static void initializeFrames() {
		// Main Frame
		IBFrame = new ItemBuilder();
		IBFrame.setTitle("PY ItemBuilder");
		IBFrame.setSize(1440, 800);
		IBFrame.setLocation(200, 100);
		IBFrame.setResizable(false);
		IBFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		IBFrame.setVisible(true);
		
		// Settings Frame
		settingsFrame = new JFrame();
		settingsFrame.add(settingsPanel);
		settingsFrame.setTitle("PY Settings");
		settingsFrame.setSize(600, 400);
		settingsFrame.setLocation(600, 300);
		settingsFrame.setResizable(false);
		settingsFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		settingsFrame.setVisible(false);
		settingsPanel.setSettingsFrame(settingsFrame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("settings")) {
			// Open Settings Frame
			settingsFrame.setVisible(true);
		}
	}
}
