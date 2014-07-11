/**
 * 
 */
package PYDevTools.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import PYDevTools.Spring.SpringUtilities;
import PYDevTools.utilities.ImageDrawingComponent;
import PYDevTools.utilities.ItemIconFinder;

/**
 * @author alfeey44
 *
 */
public class WeaponPanel extends JPanel implements FocusListener {
	
	// Panels
	private JSplitPane mainPane;
	private JScrollPane leftPane, rightPane;
	private JPanel leftPanel, rightPanel;
	private JLabel itemIconLabel, itemToolTipLabel;
	private ImageDrawingComponent itemIcon, itemToolTip;
	private String[] toolTipLabels = { "Item Name", "Item Level", "Binds On", "Unique", "Equip", "Type", 
										"Damage", "Delay", "DPS", "Stats", "Duribility", "Spell Equips",
										"Required Level", "Sell Price" };
	private int numTTLabels = toolTipLabels.length;
	private String[] labels = { "Name: ", "Description: ", "Display:", "Quality: ", "Equip: ", "Type: ",
								"Sheath: ", "Binds: ", "Delay: ", "Armor: ", "Block: ", "Required Level: ",
								"Item Level: ", "Unique: ", "Role: ", "Stats: ", "Resists: ", "Spells: ",
								"Sockets: " };
	private int numLabels = labels.length;
	private String[] stats = { "Stamina", "Strength", "Agility", "Intellect", "Spirit", "Spell Power", "Attack Power",
								"Hit Rating", "Crit Rating", "Haste Rating", "Armor Penetration", "Spell Penetration", 
								"Expertise", "Defense", "Dodge", "Parry", "Resilience", };
	private String[] qualities = { "Gray", "White", "Green", "Blue", "Purple", "Orange", "Heirloom" };
	private String[] equips = { "One Handed", "Main Handed", "Off Handed", "Two Handed", "Bow", "Gun", "Thrown", "CrossBow", "Wand" };
	private String[] types = { "1H Axe", "2H Axe", "Bow", "Gun", "1H Mace", "2H Mace", "Polearm", "1H Sword", "2H Sword",
								"Staff", "Fist", "Dagger", "Throwing", "Spear", "Crossbow", "Wand" };
	private String[] sheaths = { "One Handed", "Two Handed", "Staff", "Ranged", "Wand" };
	private String[] binds = { "None", "On Pick Up", "On Equip", "On Use" };
	private String[] roles = { "DPS", "Tank", "Healer" };
	private int displayIcon;
	
	private ItemIconFinder iconFinder = new ItemIconFinder();
	
	public WeaponPanel() {
		super(new SpringLayout());
		
		/////////////
		// Content //
		/////////////
		
		displayIcon = 0;
		
		// Left Panel
		leftPanel = new JPanel(new SpringLayout());
		leftPane = new JScrollPane(leftPanel);
		leftPane.setPreferredSize(new Dimension(350, 0));
		leftPane.getVerticalScrollBar().setUnitIncrement(16);
		for (int i = 0; i < numLabels; i++) {
			boolean isTextField = true;
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			leftPanel.add(l);
			// Non Text Fields
			switch(i) {
				case 2:
					JTextField textField = new JTextField(15);
					textField.addFocusListener(this);
					l.setLabelFor(textField);
					leftPanel.add(textField);
					isTextField = false;
					break;
				case 3:
					JComboBox<String> quality = new JComboBox<String>(qualities);
					quality.setSelectedIndex(0);
					leftPanel.add(quality);
					isTextField = false;
					break;
				case 4:
					JComboBox<String> equip = new JComboBox<String>(equips);
					equip.setSelectedIndex(0);
					leftPanel.add(equip);
					isTextField = false;
					break;
				case 5:
					JComboBox<String> type = new JComboBox<String>(types);
					type.setSelectedIndex(0);
					leftPanel.add(type);
					isTextField = false;
					break;
				case 6:
					JComboBox<String> sheath = new JComboBox<String>(sheaths);
					sheath.setSelectedIndex(0);
					leftPanel.add(sheath);
					isTextField = false;
					break;
				case 7:
					JComboBox<String> bind = new JComboBox<String>(binds);
					bind.setSelectedIndex(0);
					leftPanel.add(bind);
					isTextField = false;
					break;
				case 14:
					JComboBox<String> role = new JComboBox<String>(roles);
					role.setSelectedIndex(0);
					leftPanel.add(role);
					isTextField = false;
					break;
				case 15:
					JList<String> selectedStats = new JList<String>(stats);
					selectedStats.setSelectedIndex(0);
					leftPanel.add(selectedStats);
					isTextField = false;
					break;
			}
			// Default Text Fields
			if (isTextField) {
				JTextField textField = new JTextField(15);
				l.setLabelFor(textField);
				leftPanel.add(textField);
			}
		}

		SpringUtilities.makeCompactGrid(leftPanel, numLabels, 2, 5, 5, 20, 10);
		
		// Right Panel
		SpringLayout rightLayout = new SpringLayout();
		rightPanel = new JPanel(rightLayout);
		rightPane = new JScrollPane(rightPanel);
		// Item Icon
		try {
			itemIcon = new ImageDrawingComponent(new File("src/icons/Inv_misc_questionmark.png").toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		itemIcon.resize(80, 80);
		SpringLayout.Constraints iconCons = rightLayout.getConstraints(itemIcon);
		iconCons.setX(Spring.constant(120));
		iconCons.setY(Spring.constant(60));
		rightPanel.add(itemIcon);
		// Item ToolTip
		try {
			itemToolTip = new ImageDrawingComponent(new File("src/icons/tooltip.png").toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		itemToolTip.resize(400, 400);
		SpringLayout.Constraints toolTipCons = rightLayout.getConstraints(itemToolTip);
		toolTipCons.setX(Spring.constant(210));
		toolTipCons.setY(Spring.constant(60));
		rightPanel.add(itemToolTip);
		for (int i = 0; i < numTTLabels; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			l.setForeground(Color.WHITE);
			l.setLocation(10, (20*i) + 15);
			itemToolTip.add(l);
		}
		
		// Add Panels to Weapon Panel
		mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
		add(mainPane);
		SpringUtilities.makeCompactGrid(this, 1, 1, 6, 6, 6, 6);
	}

	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField displayId = (JTextField)e.getComponent();
		int displayIdText = Integer.parseInt(displayId.getText());
		String iconPath = "";
		if (!displayId.getText().isEmpty()) {
			iconPath = iconFinder.findIconByDisplayId(displayIdText);
			System.out.println(iconPath);
			itemIcon.setImage("src/icons/WoWIcons/" + iconPath + ".png");
		}
	}
}
