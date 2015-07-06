/**
 * 
 */
package PYDevTools.view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * @author Alfeey
 *
 */
@SuppressWarnings("serial")
public class ItemBuilder extends JFrame implements ActionListener {
	
	private static ItemBuilder instance = null;
	private static JFrame IBFrame, settingsFrame, serverConfigFrame;
	private JTabbedPane tabbedPane;
	private WeaponPanel wepPanel;
	private ArmorPanel armorPanel;
	private MiscPanel miscPanel;
	private DbPanel dbPanel;
	private static SettingsPanel settingsPanel = SettingsPanel.getInstance();
	private static ServerConfigPanel serverConfigPanel = ServerConfigPanel.getInstance();
	private ImageIcon weaponTabIcon, armorTabIcon, miscTabIcon, dbTabIcon;
	private JMenuBar menuBar;
	private JMenu settingsMenu, themesMenu;
	private JMenuItem settingsMenuItem, serverConfigurationsMenuItem;
	private JRadioButtonMenuItem defaultThemeMenuItem, webThemeMenuItem, darkThemeMenuItem;
	
	private ItemBuilder() {
		
		// Build menu
		menuBar = new JMenuBar();
		
		// Settings Menu
		settingsMenu = new JMenu("Settings");
		settingsMenu.setMnemonic(KeyEvent.VK_F1);
		menuBar.add(settingsMenu);
		
		settingsMenuItem = new JMenuItem("Database Settings", new ImageIcon("src/icons/Cogwheel.png"));
		settingsMenuItem.setMnemonic(KeyEvent.VK_S);
		settingsMenuItem.addActionListener(this);
		settingsMenuItem.setActionCommand("settings");
		settingsMenu.add(settingsMenuItem);
		
		serverConfigurationsMenuItem = new JMenuItem("Server Configs", new ImageIcon(""));
		serverConfigurationsMenuItem.setMnemonic(KeyEvent.VK_C);
		serverConfigurationsMenuItem.addActionListener(this);
		serverConfigurationsMenuItem.setActionCommand("serverconfigurations");
		settingsMenu.add(serverConfigurationsMenuItem);
		
		// Theme Menu
		themesMenu = new JMenu("Themes");
		themesMenu.setMnemonic(KeyEvent.VK_F2);
		menuBar.add(themesMenu);
		
		defaultThemeMenuItem = new JRadioButtonMenuItem("Default");
		defaultThemeMenuItem.setMnemonic(KeyEvent.VK_D);
		defaultThemeMenuItem.addActionListener(this);
		defaultThemeMenuItem.setActionCommand("defaultTheme");
		themesMenu.add(defaultThemeMenuItem);
		
		webThemeMenuItem = new JRadioButtonMenuItem("Web");
		webThemeMenuItem.setMnemonic(KeyEvent.VK_W);
		webThemeMenuItem.addActionListener(this);
		webThemeMenuItem.setActionCommand("webTheme");
		webThemeMenuItem.setSelected(true);
		themesMenu.add(webThemeMenuItem);
		
		darkThemeMenuItem = new JRadioButtonMenuItem("Dark");
		darkThemeMenuItem.setMnemonic(KeyEvent.VK_D);
		darkThemeMenuItem.addActionListener(this);
		darkThemeMenuItem.setActionCommand("darkTheme");
		themesMenu.add(darkThemeMenuItem);
		
		ButtonGroup themesGroup = new ButtonGroup();
		themesGroup.add(defaultThemeMenuItem);
		themesGroup.add(webThemeMenuItem);
		themesGroup.add(darkThemeMenuItem);
		
		setJMenuBar(menuBar);
		
		// Tabbed Pane
		tabbedPane = new JTabbedPane();
		// Weapon Tab
		wepPanel = WeaponPanel.getInstance();
		weaponTabIcon = new ImageIcon("src/icons/thunderfury-icon.gif");
		tabbedPane.addTab("Weapon", weaponTabIcon, wepPanel, "Build Weapons");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_W);
		// Armor Tab
		armorPanel = ArmorPanel.getInstance();
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
	
	public static ItemBuilder getInstance() {
		if (instance == null) {
			instance = new ItemBuilder();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
					initializeFrames();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
	private static void initializeFrames() {
		// Main Frame
		IBFrame = ItemBuilder.getInstance();
		IBFrame.setTitle("PY ItemBuilder");
		IBFrame.setSize(1440, 800);
		IBFrame.setLocation(200, 100);
		IBFrame.setResizable(false);
		IBFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon frameIcon = new ImageIcon("src/icons/window_icon.png");
		IBFrame.setIconImage(frameIcon.getImage());
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
		
		// Server Configuration Frame
		serverConfigFrame = new JFrame();
		serverConfigFrame.add(serverConfigPanel);
		serverConfigFrame.setTitle("PY Server Configurations");
		serverConfigFrame.setSize(1200, 700);
		serverConfigFrame.setLocation(300, 150);
		serverConfigFrame.setResizable(false);
		serverConfigFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		serverConfigFrame.setVisible(false);
		serverConfigPanel.setServerConfigFrame(serverConfigFrame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("settings")) {
			// Open Settings Frame
			settingsFrame.setVisible(true);
		} else if (e.getActionCommand().equals("serverconfigurations")) {
			// Open Server Configuration Frame
			serverConfigFrame.setVisible(true);
		} else if (e.getActionCommand().equals("defaultTheme")) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				SwingUtilities.updateComponentTreeUI(this);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else if (e.getActionCommand().equals("webTheme")) {
			try {
				UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
				SwingUtilities.updateComponentTreeUI(this);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else if (e.getActionCommand().equals("darkTheme")) {
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
				SwingUtilities.updateComponentTreeUI(this);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void goToWeaponTab() {
		tabbedPane.setSelectedIndex(0);
	}
	
	public void goToArmorTab() {
		tabbedPane.setSelectedIndex(1);
	}
	
	public JFrame getIBFrame() {
		return IBFrame;
	}
}
