/**
 * 
 */
package PYDevTools.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import PYDevTools.db.structures.Item;
import PYDevTools.utilities.ImageDrawingComponent;
import PYDevTools.utilities.ItemIconFinder;
import PYDevTools.utilities.ItemToolTip;
import PYDevTools.utilities.MySQLAccess;
import PYDevTools.utilities.SpellFinder;

/**
 * @author alfeey44
 *
 */
public class WeaponPanel extends JPanel implements FocusListener, ActionListener {
	
	// Panels
	private JSplitPane mainPane;
	private JScrollPane leftPane, rightPane;
	private JPanel leftPanel, rightPanel;
	private JLabel itemIconLabel, itemToolTipLabel;
	private JButton createItem;
	private ImageIcon createItemImage = createImageIcon("../../icons/blacksmithing.png");
	private ImageDrawingComponent itemIcon, itemToolTip;
	private String[] labels = { "Name: ", "Description: ", "Display:", "Quality: ", "Equip: ", "subclass: ",
								"Sheath: ", "Binds: ", "Delay: ", "Min Damage: ", "Max Damage:", "Armor: ", 
								"Block: ", "Required Level: ","Item Level: ", "Unique: ", "Role: ", 
								"Stats: ", "Resists: ", "Spells: ", "Sockets: " };
	private int numLabels = labels.length;
	private JTextField name, desc, display, delay, mindamage, maxdamage, armor, block, reqlvl, ilvl, unique, spell, socket;
	private JComboBox<String> quality, equip, subclass, sheath, bind, role;
	private JList<String> selectedStats;
	private String[] stats = { "Stamina", "Strength", "Agility", "Intellect", "Spirit", "Spell Power", "Attack Power",
								"Hit Rating", "Crit Rating", "Haste Rating", "Armor Penetration", "Spell Penetration", 
								"Expertise", "Defense", "Dodge", "Parry", "Resilience", };
	private String[] qualities = { "Gray", "White", "Green", "Blue", "Purple", "Orange", "Heirloom" };
	private String[] equips = { "One Handed", "Main Handed", "Off Handed", "Two Handed", "Bow", "Gun", "Thrown", "CrossBow", "Wand" };
	private String[] subclasses = { "1H Axe", "2H Axe", "Bow", "Gun", "1H Mace", "2H Mace", "Polearm", "1H Sword", "2H Sword",
								"Staff", "Fist", "Dagger", "Throwing", "Spear", "Crossbow", "Wand" };
	private String[] sheaths = { "One Handed", "Two Handed", "Staff", "Shield", "Off Hand" };
	private String[] binds = { "None", "On Pick Up", "On Equip", "On Use" };
	private String[] roles = { "DPS", "Tank", "Healer" };
	private String[] resists = { "Fire", "Frost", "Shadow", "Holy", "Nature", "Arcane" };
	private JList<String> selectedResists;
	private JLabel invalidDisplayId;
	private Font italicFont = new Font("Arial", Font.ITALIC, 14);
	SpringLayout rightLayout;
	
	private ItemIconFinder iconFinder = ItemIconFinder.getInstance();
	private SpellFinder spellFinder = SpellFinder.getInstance();
	private Item item = new Item();
	private MySQLAccess db = new MySQLAccess();
	
	private float serverMultiplier;
	
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
					name.addFocusListener(this);
					l.setLabelFor(name);
					leftPanel.add(name);
					break;
				case 1:
					desc = new JTextField(15);
					desc.addFocusListener(this);
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
					quality.addActionListener(this);
					quality.setActionCommand("quality");
					l.setLabelFor(quality);
					leftPanel.add(quality);
					break;
				case 4:
					equip = new JComboBox<String>(equips);
					equip.setSelectedIndex(0);
					equip.addActionListener(this);
					equip.setActionCommand("equips");
					l.setLabelFor(equip);
					leftPanel.add(equip);
					break;
				case 5:
					subclass = new JComboBox<String>(subclasses);
					subclass.setSelectedIndex(0);
					subclass.addActionListener(this);
					subclass.setActionCommand("subclasss");
					l.setLabelFor(subclass);
					leftPanel.add(subclass);
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
					bind.addActionListener(this);
					bind.setActionCommand("binds");
					l.setLabelFor(bind);
					leftPanel.add(bind);
					break;
				case 8:
					delay = new JTextField(15);
					delay.addFocusListener(this);
					l.setLabelFor(delay);
					leftPanel.add(delay);
					break;
				case 9:
					mindamage = new JTextField(15);
					mindamage.addFocusListener(this);
					l.setLabelFor(mindamage);
					leftPanel.add(mindamage);
					break;
				case 10:
					maxdamage = new JTextField(15);
					maxdamage.addFocusListener(this);
					l.setLabelFor(maxdamage);
					leftPanel.add(maxdamage);
					break;
				case 11:
					armor = new JTextField(15);
					armor.addFocusListener(this);
					l.setLabelFor(armor);
					leftPanel.add(armor);
					break;
				case 12:
					block = new JTextField(15);
					block.addFocusListener(this);
					l.setLabelFor(block);
					leftPanel.add(block);
					break;
				case 13:
					reqlvl = new JTextField(15);
					reqlvl.addFocusListener(this);
					l.setLabelFor(reqlvl);
					leftPanel.add(reqlvl);
					break;
				case 14:
					ilvl = new JTextField(15);
					ilvl.addFocusListener(this);
					l.setLabelFor(ilvl);
					leftPanel.add(ilvl);
					break;
				case 15:
					unique = new JTextField(15);
					unique.addFocusListener(this);
					l.setLabelFor(unique);
					leftPanel.add(unique);
					break;
				case 16:
					role = new JComboBox<String>(roles);
					role.setSelectedIndex(0);
					l.setLabelFor(role);
					leftPanel.add(role);
					break;
				case 17:
					selectedStats = new JList<String>(stats);
					l.setLabelFor(selectedStats);
					leftPanel.add(selectedStats);
					break;
				case 18:
					selectedResists = new JList<String>(resists);
					l.setLabelFor(selectedResists);
					leftPanel.add(selectedResists);
					break;
				case 19:
					spell = new JTextField(15);
					spell.addFocusListener(this);
					l.setLabelFor(spell);
					leftPanel.add(spell);
					break;
				case 20:
					socket = new JTextField(15);
					socket.addFocusListener(this);
					l.setLabelFor(socket);
					leftPanel.add(socket);
					break;
			}
		}

		SpringUtilities.makeCompactGrid(leftPanel, numLabels, 2, 5, 5, 20, 10);
		
		// Right Panel
		rightLayout = new SpringLayout();
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
			itemToolTip = new ItemToolTip(item);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		SpringLayout.Constraints toolTipCons = rightLayout.getConstraints(itemToolTip);
		toolTipCons.setX(Spring.constant(210));
		toolTipCons.setY(Spring.constant(60));
		itemToolTip.setVisible(false);
		rightPanel.add(itemToolTip);
		
		invalidDisplayId = new JLabel("Display Id Invalid");
		invalidDisplayId.setFont(italicFont);
		invalidDisplayId.setForeground(Color.red);
		invalidDisplayId.setVisible(false);
		SpringLayout.Constraints invalidDisplayIdCons = rightLayout.getConstraints(invalidDisplayId);
		invalidDisplayIdCons.setX(Spring.constant(90));
		invalidDisplayIdCons.setY(Spring.constant(140));
		rightPanel.add(invalidDisplayId);
		
		createItem = new JButton("Craft", createItemImage);
		createItem.setActionCommand("craft");
		createItem.addActionListener(this);
		SpringLayout.Constraints craftCons = rightLayout.getConstraints(createItem);
		craftCons.setX(Spring.constant(900));
		craftCons.setY(Spring.constant(565));
		rightPanel.add(createItem);
		
		// Add Panels to Weapon Panel
		mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
		add(mainPane);
		SpringUtilities.makeCompactGrid(this, 1, 1, 6, 6, 6, 6);
	}

	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(createItem.getActionCommand())) {
			// Craft button clicked
			fillItem();
		}
	}
	
	/** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = WeaponPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /** Fills item class with fields from panel **/
    private void fillItem() {
    	// Crafting a weapon
    	item.setClass_(2);
    	
    	////////////////////////
    	
    	if (!display.getText().isEmpty()) {
			String iconPath = "";
			if (!display.getText().isEmpty()) {
				// Do it here in case id exists but just no icon available
				item.setDisplay(Integer.parseInt(display.getText()));
				int displayIdText = 0;
				try {
					displayIdText = Integer.parseInt(display.getText());
				} catch (NumberFormatException ex) {
					// Display Id invalid
					invalidDisplayId.setVisible(true);
					itemIcon.setImage("src/icons/Inv_misc_questionmark.png");
				}
				iconPath = iconFinder.findIconByDisplayId(displayIdText);
				if (iconPath != null && !iconPath.isEmpty()) {
					invalidDisplayId.setVisible(false);
					itemIcon.setImage("src/icons/WoWIcons/" + iconPath + ".png");
					System.out.println("Display Value: " + display.getText());
					itemIcon.repaint();
				} else {
					// Display Id invalid
					invalidDisplayId.setVisible(true);
					itemIcon.setImage("src/icons/Inv_misc_questionmark.png");
				}
			}
			
		}
    	
		if (!name.getText().isEmpty()) {
			item.setName(name.getText());
		}
		
		if (!desc.getText().isEmpty()) {
			item.setDescription(desc.getText());
		}
		
		if (!delay.getText().isEmpty()) {
			item.setDelay(Integer.parseInt(delay.getText()));
		}
		
		if (!mindamage.getText().isEmpty()) {
			item.setMinDamage(Integer.parseInt(mindamage.getText()));
		}
		
		if (!maxdamage.getText().isEmpty()) {
			item.setMaxDamage(Integer.parseInt(maxdamage.getText()));
		}
		
		if (!armor.getText().isEmpty()) {
			item.setArmor(Integer.parseInt(armor.getText()));
		}
		
		if (!block.getText().isEmpty()) {
			item.setBlock(Integer.parseInt(block.getText()));
		}
		
		if (!reqlvl.getText().isEmpty()) {
			item.setReqlvl(Integer.parseInt(reqlvl.getText()));
		}
		
		if (!ilvl.getText().isEmpty()) {
			item.setIlvl(Integer.parseInt(ilvl.getText()));
		}
		
		if (!unique.getText().isEmpty()) {
			item.setUnique(Integer.parseInt(unique.getText()));
		}
		
    	item.setQuality(quality.getSelectedIndex());
    	
    	switch (equip.getSelectedIndex()) {
    	case 0:
    		// One Handed
    		item.setInventoryType(13);
    		break;
    	case 1:
    		// Main Handed
    		item.setInventoryType(21);
    		break;
    	case 2:
    		// Off Handed
    		item.setInventoryType(22);
    		break;
    	case 3:
    		// Two Handed
    		item.setInventoryType(17);
    		break;
    	case 4:
    		// Bow
    		item.setInventoryType(15);
    		break;
    	case 5:
    		// Gun
    		item.setInventoryType(26);
    		break;
    	case 6:
    		// Thrown
    		item.setInventoryType(25);
    		break;
    	case 7:
    		// Crossbow
    		item.setInventoryType(15);
    		break;
    	case 8:
    		// Wands
    		item.setInventoryType(26);
    		break;
    	}
    	switch(subclass.getSelectedIndex()) {
    	case 0:
    		// 1H axe
    		item.setSubclass(0);
    		break;
    	case 1:
    		// 2H axe
    		item.setSubclass(1);
    		break;
    	case 2:
    		// Bow
    		item.setSubclass(2);
    		break;
    	case 3:
    		// Gun
    		item.setSubclass(3);
    		break;
    	case 4:
    		// 1H mace
    		item.setSubclass(4);
    		break;
    	case 5:
    		// 2H mace
    		item.setSubclass(5);
    		break;
    	case 6:
    		// Polearm
    		item.setSubclass(6);
    		break;
    	case 7:
    		// 1H sword
    		item.setSubclass(7);
    		break;
    	case 8:
    		// 2H sword
    		item.setSubclass(8);
    		break;
    	case 9:
    		// Staff
    		item.setSubclass(10);
    		break;
    	case 10:
    		// Fist
    		item.setSubclass(13);
    		break;
    	case 11:
    		// Dagger
    		item.setSubclass(15);
    		break;
    	case 12:
    		// Thrown
    		item.setSubclass(16);
    		break;
    	case 13:
    		// Spear
    		item.setSubclass(17);
    		break;
    	case 14:
    		// Crossbow
    		item.setSubclass(18);
    		break;
    	case 15:
    		// Wand
    		item.setSubclass(19);
    		break;
    	}
    	
    	switch (sheath.getSelectedIndex()) {
    	case 0:
    		// One Handed
    		item.setSheath(3);
    		break;
    	case 1:
    		// Two Handed
    		item.setSheath(1);
    		break;
    	case 2:
    		// Staff
    		item.setSheath(2);
    		break;
    	case 3:
    		// Shield
    		item.setSheath(4);
    		break;
    	case 4:
    		// Off Hand
    		item.setSheath(6);
    		break;
    	}
    	
    	item.setBinds(bind.getSelectedIndex());
    	
    	// Stats
    	int statsToCalc[] = selectedStats.getSelectedIndices();
    	
    	if (statsToCalc.length > 10) {
    		// print error message, can only have 10 stats
    	}
    	
    	for (int i = 0; i < statsToCalc.length && i < 10; i++) {
    		String statText = "";
    		// initialize true because their are less normal stats
    		boolean isEquipStat = true;
    		float statMultiplier = 1;
    		switch(statsToCalc[i]) {
    		case 0: //stamina
    			item.setStat_type(i, 7);
    			statMultiplier = 1.75f;
    			statText = " Stamina";
    			isEquipStat = false;
    			break;
    		case 1: //strength
    			item.setStat_type(i, 4);
    			statMultiplier = 1.45f;
    			statText = " Strength";
    			isEquipStat = false;
    			break;
    		case 2: //agility
    			item.setStat_type(i, 3);
    			statMultiplier = 1.26f;
    			statText = " Agility";
    			isEquipStat = false;
    			break;
			case 3: //intellect
				item.setStat_type(i, 5);
				statMultiplier = 1.49f;
				statText = " Intellect";
				isEquipStat = false;
			    break;
			case 4: //spirit
				item.setStat_type(i, 6);
				statMultiplier = 1.85f;
				statText = " Spirit";
				isEquipStat = false;
				break;
			case 5: //spell power
				item.setStat_type(i, 45);
				statMultiplier = 2.72f;
				statText = "Equip: Increases spell power by ";
				break;
			case 6: //attack power
				item.setStat_type(i, 38);
				statMultiplier = 2.61f;
				statText = "Equip: Increases attack power by ";
				break;
			case 7: //hitrating
				item.setStat_type(i, 31);
				statMultiplier = 0.65f;
				statText = "Equip: Improves hit rating by ";
				break;
			case 8: //critrating
				item.setStat_type(i, 32);
				statMultiplier = 0.75f;
				statText = "Equip: Improves critical strike rating by ";
				break;
			case 9: //hasterating
				item.setStat_type(i, 36);
				statMultiplier = 0.5f;
				statText = "Equip: Improves haste rating by ";
				break;
			case 10: //armorpen
				item.setStat_type(i, 44);
				statMultiplier = 0.85f;
				statText = "Equip: Improves armor penetration by ";
				break;
			case 11: //spellpen
				item.setStat_type(i, 47);
				statMultiplier = 0.85f;
				statText = "Equip: Improves spell penetration by ";
				break;
			case 12: //expertise
				item.setStat_type(i, 37);
				statMultiplier = 1.05f;
				statText = "Equip: Improves expertise rating by ";
				break;
			case 13: //defense
				item.setStat_type(i, 12);
				statMultiplier = 1.36f;
				statText = "Equip: Improves defense rating by ";
				break;
			case 14: //dodge
				item.setStat_type(i, 13);
				statMultiplier = 1.49f;
				statText = "Equip: Improves dodge rating by ";
				break;
			case 15: //parry
				item.setStat_type(i, 14);
				statMultiplier = 1.52f;
				statText = "Equip: Improves parry rating by ";
				break;
			case 16: //resilience
				item.setStat_type(i, 35);
				statMultiplier = 0.95f;
				statText = "Equip: Improves your resilience rating by ";
				break;
			default: //nothing
    			item.setStat_type(i, 0);
    			// No stats should be added to item
    			statText = "";
    			statMultiplier = 0;
    			break;
    		}
    		
    		float subclassMultiplier = 1;
    		
    		switch (item.getSubclass()) {
    		// One handers
    		case 0:
    		case 4:
    		case 7:
    		case 13:
    		case 15:
    			subclassMultiplier = 0.85f;
    			break;
    		// ranged
    		case 2:
    		case 3:
    		case 16:
    		case 17:
    		case 18:
    		case 19:
    			subclassMultiplier = 1.15f;
    			break;
    		// Two handers
    		case 1:
    		case 5:
    		case 6:
    		case 8:
    		case 10:
    			subclassMultiplier = 1.55f;
    			break;
    		default:
    			break;
    		}
    		// TODO: Make this serverMultiplier an option
    		serverMultiplier = 1;
    		// Do calculation for value based off of quality and ilvl and subclass
    		float statValue = serverMultiplier*subclassMultiplier*statMultiplier*item.getIlvl()*item.getQuality()/4;
    		item.setStat_value(i, (int)statValue);
    	}
    	
    	// resists
    	int restistToCalc[] = selectedResists.getSelectedIndices();
    	float resistValue;
    	
    	if (restistToCalc.length > 6) {
    		// print error message, can only have 10 stats
    	}
    	
    	for (int i = 0; i < restistToCalc.length && i < 6; i++) {
    		String resistText = "";
    		// initialize true because their are less normal stats
    		float statMultiplier = 1;
    		float subclassMultiplier = 1;
    		
    		switch (item.getSubclass()) {
    		// One handers
    		case 0:
    		case 4:
    		case 7:
    		case 13:
    		case 15:
    			subclassMultiplier = 0.85f;
    			break;
    		// ranged
    		case 2:
    		case 3:
    		case 16:
    		case 17:
    		case 18:
    		case 19:
    			subclassMultiplier = 1.15f;
    			break;
    		// Two handers
    		case 1:
    		case 5:
    		case 6:
    		case 8:
    		case 10:
    			subclassMultiplier = 1.55f;
    			break;
    		default:
    			break;
    		}
    		
    		// TODO: Make this serverMultiplier an option
    		serverMultiplier = 1;
    		// Do calculation for value based off of quality and ilvl and subclass
    		
    		switch(restistToCalc[i]) {
    		case 0: //fire
    			resistValue = subclassMultiplier*statMultiplier*item.getIlvl()*item.getQuality()/6;
    			resistText = "Equip: Increases fire resist by ";
    			item.setFire_resist((int)resistValue);
    			break;
    		case 1: //frost
    			resistValue = subclassMultiplier*statMultiplier*item.getIlvl()*item.getQuality()/6;
    			resistText = "Equip: Increases frost resist by ";
    			item.setFrost_resist((int)resistValue);
    			break;
    		case 2: //shadow
    			resistValue = subclassMultiplier*statMultiplier*item.getIlvl()*item.getQuality()/6;
    			resistText = "Equip: Increases shadow resist by ";
    			item.setShadow_resist((int)resistValue);
    			break;
			case 3: //holy
				resistValue = subclassMultiplier*statMultiplier*item.getIlvl()*item.getQuality()/6;
    			resistText = "Equip: Increases holy resist by ";
    			item.setHoly_resist((int)resistValue);
			    break;
			case 4: //nature
				resistValue = subclassMultiplier*statMultiplier*item.getIlvl()*item.getQuality()/6;
    			resistText = "Equip: Increases nature resist by ";
    			item.setNature_resist((int)resistValue);
				break;
			case 5: //arcane
				resistValue = subclassMultiplier*statMultiplier*item.getIlvl()*item.getQuality()/6;
    			resistText = "Equip: Increases nature resist by ";
    			item.setArcane_resist((int)resistValue);
				break;
			default: //nothing
    			// No resists should be added to item
    			break;
    		}
    		
    	}
    	   	
    	// Spells
    	if (!spell.getText().isEmpty()) {
    		String spellDesc = spellFinder.findSpellDescriptionById(Integer.parseInt(spell.getText()));
    		System.out.println("Spell Description: " + spellDesc);
    		String spellIcon = spellFinder.findSpellIconById(Integer.parseInt(spell.getText()));
    		System.out.println("Spell Icon: " + spellIcon);
    		item.setSpell_id(0, Integer.parseInt(spell.getText()));
    	}
    	
    	// Sockets
    	
    	//repaint
    	rightPanel.remove(itemToolTip);
    	try {
			itemToolTip = new ItemToolTip(item);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		SpringLayout.Constraints toolTipCons = rightLayout.getConstraints(itemToolTip);
		toolTipCons.setX(Spring.constant(210));
		toolTipCons.setY(Spring.constant(60));
		rightPanel.add(itemToolTip);
		
		rightPanel.repaint();
		rightPanel.revalidate();
    }
}
