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
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JComponent;
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
	private JTextField name, desc, display, delay, armor, block, reqlvl, ilvl, unique, spell, socket;
	private JComboBox quality, equip, type, sheath, bind, role, resist;
	private JList<String> selectedStats;
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
	private String[] resists = { "Fire", "Frost", "Shadow", "Holy", "Nature", "Arcane" };
	
	private ItemIconFinder iconFinder = new ItemIconFinder();
	
	public WeaponPanel() {
		super(new SpringLayout());
		
		/////////////
		// Content //
		/////////////
		
		// Left Panel
		leftPanel = new JPanel(new SpringLayout());
		leftPane = new JScrollPane(leftPanel);
		leftPane.setPreferredSize(new Dimension(350, 0));
		leftPane.getVerticalScrollBar().setUnitIncrement(16);
		for (int i = 0; i < numLabels; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			leftPanel.add(l);
			switch(i) {
				case 0:
					name = new JTextField(15);
					l.setLabelFor(name);
					leftPanel.add(name);
					break;
				case 1:
					desc = new JTextField(15);
					l.setLabelFor(desc);
					leftPanel.add(desc);
					break;
				case 2:
					display = new JTextField(15);
					display.addFocusListener(this);
					l.setLabelFor(display);
					leftPanel.add(display);
					break;
				case 3:
					quality = new JComboBox<String>(qualities);
					quality.setSelectedIndex(0);
					l.setLabelFor(quality);
					leftPanel.add(quality);
					break;
				case 4:
					equip = new JComboBox<String>(equips);
					equip.setSelectedIndex(0);
					l.setLabelFor(equip);
					leftPanel.add(equip);
					break;
				case 5:
					type = new JComboBox<String>(types);
					type.setSelectedIndex(0);
					l.setLabelFor(type);
					leftPanel.add(type);
					break;
				case 6:
					sheath = new JComboBox<String>(sheaths);
					sheath.setSelectedIndex(0);
					l.setLabelFor(sheath);
					leftPanel.add(sheath);
					break;
				case 7:
					bind = new JComboBox<String>(binds);
					bind.setSelectedIndex(0);
					l.setLabelFor(bind);
					leftPanel.add(bind);
					break;
				case 8:
					delay = new JTextField(15);
					l.setLabelFor(delay);
					leftPanel.add(delay);
					break;
				case 9:
					armor = new JTextField(15);
					l.setLabelFor(armor);
					leftPanel.add(armor);
					break;
				case 10:
					block = new JTextField(15);
					l.setLabelFor(block);
					leftPanel.add(block);
					break;
				case 11:
					reqlvl = new JTextField(15);
					l.setLabelFor(reqlvl);
					leftPanel.add(reqlvl);
					break;
				case 12:
					ilvl = new JTextField(15);
					l.setLabelFor(ilvl);
					leftPanel.add(ilvl);
					break;
				case 13:
					unique = new JTextField(15);
					l.setLabelFor(unique);
					leftPanel.add(unique);
					break;
				case 14:
					role = new JComboBox<String>(roles);
					role.setSelectedIndex(0);
					l.setLabelFor(role);
					leftPanel.add(role);
					break;
				case 15:
					selectedStats = new JList<String>(stats);
					selectedStats.setSelectedIndex(0);
					l.setLabelFor(selectedStats);
					leftPanel.add(selectedStats);
					break;
				case 16:
					resist = new JComboBox<String>(resists);
					l.setLabelFor(resist);
					leftPanel.add(resist);
					break;
				case 17:
					spell = new JTextField(15);
					l.setLabelFor(spell);
					leftPanel.add(spell);
					break;
				case 18:
					socket = new JTextField(15);
					l.setLabelFor(socket);
					leftPanel.add(socket);
					break;
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
		String iconPath = "";
		if (!displayId.getText().isEmpty()) {
			int displayIdText = Integer.parseInt(displayId.getText());
			iconPath = iconFinder.findIconByDisplayId(displayIdText);
			if (!iconPath.isEmpty()) {
				itemIcon.setImage("src/icons/WoWIcons/" + iconPath + ".png");
				System.out.println("Display Value: " + display.getText());
			}
		}
	}
}
