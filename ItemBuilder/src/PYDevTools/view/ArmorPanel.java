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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
@SuppressWarnings("serial")
public class ArmorPanel extends JPanel implements FocusListener, ActionListener, KeyListener {
	
	private static ArmorPanel instance = null;
	// Panels
	private JSplitPane mainPane;
	private JScrollPane leftPane, rightPane;
	private JPanel leftPanel, rightPanel;
	private JLabel itemIconLabel, itemToolTipLabel;
	private JButton createItem, insertItem;
	private ImageIcon anvilImageIcon;
	private ImageDrawingComponent itemIcon, itemToolTip;
	private String[] labels = { "Name: ", "Description: ", "Display:", "Quality: ", "Equip: ", "subclass: ", 
								"Binds: ", "Required Level: ","Item Level: ", "Unique: ", "Role: ", 
								"Stats: ", "Resists: ", "Spell 1: ", "Spell 1 Trigger: ", "Spell 2: ", "Spell 2 Trigger: ",
								"Spell 3: ", "Spell 3 Trigger: ", "Spell 4: ", "Spell 4 Trigger: ", "Spell 5: ", "Spell 5 Trigger: ", 
								"Sockets: " };
	private int numLabels = labels.length;
	private JTextField name, desc, display, armor, block, reqlvl, 
					   ilvl, unique, spell1, spell2, spell3, spell4, spell5, socket;
	private JComboBox<String> quality, equip, subclass, bind, role, spelltrigger1, spelltrigger2,
	   						  spelltrigger3, spelltrigger4, spelltrigger5;
	private JList<String> selectedStats;
	private String[] stats = {  "Agility", "Stamina", "Strength", "Intellect", "Spirit", "Spell Power", "Attack Power",
								"Hit Rating", "Crit Rating", "Haste Rating", "Armor Penetration", "Spell Penetration", 
								"Expertise", "Defense", "Dodge", "Parry", "Block", "Resilience", };
	private String[] qualities = { "Gray", "White", "Green", "Blue", "Purple", "Orange", "Heirloom" };
	private String[] equips = { "One Handed", "Main Handed", "Off Handed", "Two Handed", "Bow", "Gun", "Thrown", "CrossBow", "Wand" };
	private String[] subclasses = { "1H Axe", "2H Axe", "Bow", "Gun", "1H Mace", "2H Mace", "Polearm", "1H Sword", "2H Sword",
								"Staff", "Fist", "Dagger", "Throwing", "Spear", "Crossbow", "Wand" };
	private String[] binds = { "None", "On Pick Up", "On Equip", "On Use" };
	private String[] roles = { "DPS", "Tank", "Healer" };
	private String[] resists = { "Fire", "Frost", "Shadow", "Holy", "Nature", "Arcane" };
	private String[] spelltriggers = { "Use", "On Equip", "Chance on Hit" };
	private JList<String> selectedResists;
	private JLabel invalidDisplayId;
	private Font italicFont = new Font("Arial", Font.ITALIC, 14);
	SpringLayout rightLayout;
	
	private ItemIconFinder iconFinder = ItemIconFinder.getInstance();
	private SpellFinder spellFinder = SpellFinder.getInstance();
	private Item item = new Item();
	private MySQLAccess db = new MySQLAccess();
	private ServerConfigPanel serverConfigPanel = ServerConfigPanel.getInstance();
	
	public static ArmorPanel getInstance() {
		if (instance == null) {
			instance = new ArmorPanel();
		}
		return instance;
	}
	
	public ArmorPanel() {
		super(new SpringLayout());
		
		anvilImageIcon = new ImageIcon("src/icons/anvil_small.jpg");
		
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
					name.addKeyListener(this);
					l.setLabelFor(name);
					leftPanel.add(name);
					break;
				case 1:
					desc = new JTextField(15);
					desc.addKeyListener(this);
					l.setLabelFor(desc);
					leftPanel.add(desc);
					break;
				case 2:
					display = new JTextField(15);
					display.addKeyListener(this);
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
					subclass = new JComboBox<String>(subclasses);
					subclass.setSelectedIndex(0);
					l.setLabelFor(subclass);
					leftPanel.add(subclass);
					break;
				case 6:
					bind = new JComboBox<String>(binds);
					bind.setSelectedIndex(0);
					l.setLabelFor(bind);
					leftPanel.add(bind);
					break;
				case 7:
					reqlvl = new JTextField(15);
					reqlvl.addKeyListener(this);
					l.setLabelFor(reqlvl);
					leftPanel.add(reqlvl);
					break;
				case 8:
					ilvl = new JTextField(15);
					ilvl.addKeyListener(this);
					l.setLabelFor(ilvl);
					leftPanel.add(ilvl);
					break;
				case 9:
					unique = new JTextField(15);
					unique.addKeyListener(this);
					l.setLabelFor(unique);
					leftPanel.add(unique);
					break;
				case 10:
					role = new JComboBox<String>(roles);
					role.setSelectedIndex(0);
					l.setLabelFor(role);
					leftPanel.add(role);
					break;
				case 11:
					selectedStats = new JList<String>(stats);
					l.setLabelFor(selectedStats);
					leftPanel.add(selectedStats);
					break;
				case 12:
					selectedResists = new JList<String>(resists);
					l.setLabelFor(selectedResists);
					leftPanel.add(selectedResists);
					break;
				case 13:
					spell1 = new JTextField(15);
					spell1.addKeyListener(this);
					l.setLabelFor(spell1);
					leftPanel.add(spell1);
					break;
				case 14:
					spelltrigger1 = new JComboBox<String>(spelltriggers);
					spelltrigger1.setSelectedIndex(0);
					l.setLabelFor(spelltrigger1);
					leftPanel.add(spelltrigger1);
					break;
				case 15:
					spell2 = new JTextField(15);
					spell2.addKeyListener(this);
					l.setLabelFor(spell2);
					leftPanel.add(spell2);
					break;
				case 16:
					spelltrigger2 = new JComboBox<String>(spelltriggers);
					spelltrigger2.setSelectedIndex(0);
					l.setLabelFor(spelltrigger2);
					leftPanel.add(spelltrigger2);
					break;
				case 17:
					spell3 = new JTextField(15);
					spell3.addKeyListener(this);
					l.setLabelFor(spell3);
					leftPanel.add(spell3);
					break;
				case 18:
					spelltrigger3 = new JComboBox<String>(spelltriggers);
					spelltrigger3.setSelectedIndex(0);
					l.setLabelFor(spelltrigger3);
					leftPanel.add(spelltrigger3);
					break;
				case 19:
					spell4 = new JTextField(15);
					spell4.addKeyListener(this);
					l.setLabelFor(spell4);
					leftPanel.add(spell4);
					break;
				case 20:
					spelltrigger4 = new JComboBox<String>(spelltriggers);
					spelltrigger4.setSelectedIndex(0);
					l.setLabelFor(spelltrigger4);
					leftPanel.add(spelltrigger4);
					break;
				case 21:
					spell5 = new JTextField(15);
					spell5.addKeyListener(this);
					l.setLabelFor(spell5);
					leftPanel.add(spell5);
					break;
				case 22:
					spelltrigger5 = new JComboBox<String>(spelltriggers);
					spelltrigger5.setSelectedIndex(0);
					l.setLabelFor(spelltrigger5);
					leftPanel.add(spelltrigger5);
					break;
				case 23:
					socket = new JTextField(15);
					socket.addKeyListener(this);
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
		
		createItem = new JButton("Craft", anvilImageIcon);
		createItem.setActionCommand("craft");
		createItem.addActionListener(this);
		SpringLayout.Constraints craftCons = rightLayout.getConstraints(createItem);
		craftCons.setX(Spring.constant(900));
		craftCons.setY(Spring.constant(550));
		rightPanel.add(createItem);
		
		insertItem = new JButton("Insert Into DB");
		insertItem.setActionCommand("insert");
		insertItem.addActionListener(this);
		SpringLayout.Constraints insertCons = rightLayout.getConstraints(insertItem);
		insertCons.setX(Spring.constant(900));
		insertCons.setY(Spring.constant(600));
		rightPanel.add(insertItem);
		
		mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
		add(mainPane);
		SpringUtilities.makeCompactGrid(this, 1, 1, 6, 6, 6, 6);
	}
	
	/** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ArmorPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /** Fills item class with fields from panel **/
    private void fillItem() {
    	item = new Item();
    	int entry = Integer.parseInt(SettingsPanel.getInstance().getNextEntryID());
    	int blockCounter = 0;
    	while (db.isUsedEntry(entry)) {
    		entry++;
    		blockCounter++;
    		// in case it is stuck in a large block, it will run somewhat faster
    		if (blockCounter == 3) {
    			entry += 10;
    			blockCounter = 1;
    		}
    	}
    	item.setEntry(entry);
    	
    	// Crafting a armor
    	item.setClass_(4);
    	
    	////////////////////////
    	
		if (!display.getText().isEmpty()) {
			String iconPath = "";
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
				itemIcon.resize(80, 80);
				itemIcon.repaint();
			} else {
				// Display Id invalid
				invalidDisplayId.setVisible(true);
				itemIcon.setImage("src/icons/Inv_misc_questionmark.png");
			}
		}
    	
		if (!name.getText().isEmpty()) {
			item.setName(name.getText());
		}
		
		if (!desc.getText().isEmpty()) {
			item.setDescription(desc.getText());
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
    	
    	item.setBinds(bind.getSelectedIndex());
    	
    	// Stats
    	int statsToCalc[] = selectedStats.getSelectedIndices();
    	
    	item.setStatsCount(statsToCalc.length);
    	
    	if (statsToCalc.length > 10) {
    		// print error message, can only have 10 stats
    	}
    	
    	for (int i = 0; i < statsToCalc.length && i < 10; i++) {
    		String statText = "";
    		// initialize true because their are fewer normal stats
    		boolean isEquipStat = true;
    		float statMultiplier = 1;
    		switch(statsToCalc[i]) {
    		case 0: //agility
    			item.setStat_type(i, 3);
    			statMultiplier = 1.26f;
    			statText = " Agility";
    			isEquipStat = false;
    			break;
    		case 1: //stamina
    			item.setStat_type(i, 7);
    			statMultiplier = 1.75f;
    			statText = " Stamina";
    			isEquipStat = false;
    			break;
    		case 2: //strength
    			item.setStat_type(i, 4);
    			statMultiplier = 1.45f;
    			statText = " Strength";
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
    		
    		double subclassMultiplier = 1;
    		
    		switch (item.getSubclass()) {
    		// One handers
    		case 0:
    		case 4:
    		case 7:
    		case 13:
    		case 15:
    			subclassMultiplier = Double.parseDouble(serverConfigPanel.getOneHandStatMultiplier());
    			break;
    		// ranged
    		case 2:
    		case 3:
    		case 16:
    		case 17:
    		case 18:
    		case 19:
    			subclassMultiplier = Double.parseDouble(serverConfigPanel.getRangedStatMultiplier());
    			break;
    		// Two handers
    		case 1:
    		case 5:
    		case 6:
    		case 8:
    		case 10:
    			subclassMultiplier = Double.parseDouble(serverConfigPanel.getTwoHandStatMultiplier());
    			break;
    		default:
    			break;
    		}
    		// TODO: Make this serverMultiplier an option
    		double serverArmorMultiplier = Double.parseDouble(serverConfigPanel.getArmorStatMultiplier());
    		// Do calculation for value based off of quality and ilvl and subclass
    		double statValue = serverArmorMultiplier*subclassMultiplier*statMultiplier*item.getIlvl()*item.getQuality()/4;
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
    		
    		double serverArmorMultiplier = Double.parseDouble(serverConfigPanel.getArmorStatMultiplier());
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
    	if (!spell1.getText().isEmpty()) {
    		int spellindex = 0;
    		String spellDesc = spellFinder.findSpellDescriptionById(Integer.parseInt(spell1.getText()));
    		String spellIcon = spellFinder.findSpellIconById(Integer.parseInt(spell1.getText()));
    		item.setSpell_id(spellindex, Integer.parseInt(spell1.getText()));
    		item.setSpell_trigger(spellindex, spelltrigger1.getSelectedIndex());
    	}
    	if (!spell2.getText().isEmpty()) {
    		int spellindex = 1;
    		String spellDesc = spellFinder.findSpellDescriptionById(Integer.parseInt(spell2.getText()));
    		String spellIcon = spellFinder.findSpellIconById(Integer.parseInt(spell2.getText()));
    		item.setSpell_id(1, Integer.parseInt(spell2.getText()));
    		item.setSpell_trigger(spellindex, spelltrigger2.getSelectedIndex());
    	}
    	if (!spell3.getText().isEmpty()) {
    		int spellindex = 2;
    		String spellDesc = spellFinder.findSpellDescriptionById(Integer.parseInt(spell3.getText()));
    		String spellIcon = spellFinder.findSpellIconById(Integer.parseInt(spell3.getText()));
    		item.setSpell_id(2, Integer.parseInt(spell3.getText()));
    		item.setSpell_trigger(spellindex, spelltrigger3.getSelectedIndex());
    	}
    	if (!spell4.getText().isEmpty()) {
    		int spellindex = 3;
    		String spellDesc = spellFinder.findSpellDescriptionById(Integer.parseInt(spell4.getText()));
    		String spellIcon = spellFinder.findSpellIconById(Integer.parseInt(spell4.getText()));
    		item.setSpell_id(3, Integer.parseInt(spell4.getText()));
    		item.setSpell_trigger(spellindex, spelltrigger4.getSelectedIndex());
    	}
    	if (!spell5.getText().isEmpty()) {
    		int spellindex = 4;
    		String spellDesc = spellFinder.findSpellDescriptionById(Integer.parseInt(spell5.getText()));
    		String spellIcon = spellFinder.findSpellIconById(Integer.parseInt(spell5.getText()));
    		item.setSpell_id(4, Integer.parseInt(spell5.getText()));
    		item.setSpell_trigger(spellindex, spelltrigger5.getSelectedIndex());
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
    
    public void setCurrentItem(Item item) {
    	this.item = item;
    	if (item != null)
    		fillCurrentItemFields();
    }
    
    public void fillCurrentItemFields() {
    	// clear fields
    	clearFields();
    	
    	// Start filling fields from item
    	name.setText(item.getName());
    	desc.setText(item.getDescription());
    	display.setText(Integer.toString(item.getDisplay()));
    	quality.setSelectedIndex(item.getQuality());
    	
    	switch (item.getInventoryType()) {
    	case 13:
    		equip.setSelectedIndex(0);
    		break;
    	case 21:
    		equip.setSelectedIndex(1);
    		break;
    	case 22:
    		equip.setSelectedIndex(2);
    		break;
    	case 17:
    		equip.setSelectedIndex(3);
    		break;
    	case 15:
    		equip.setSelectedIndex(4);
    		break;
    	case 26:
    		if (item.getSubclass() == 3)
    			equip.setSelectedIndex(5);
    		else if (item.getSubclass() == 18)
    			equip.setSelectedIndex(7);
    		else if (item.getSubclass() == 19)
    			equip.setSelectedIndex(8);
    		break;
    	case 25:
    		equip.setSelectedIndex(6);
    		break;
    	default:
    		System.err.println("Unknown item equip");
    		break;
    	}
    	
    	switch(item.getSubclass()) {
    	case 0:
    		subclass.setSelectedIndex(0);
    		break;
    	case 1:
    		subclass.setSelectedIndex(1);
    		break;
    	case 2:
    		subclass.setSelectedIndex(2);
    		break;
    	case 3:
    		subclass.setSelectedIndex(3);
    		break;
    	case 4:
    		subclass.setSelectedIndex(4);
    		break;
    	case 5:
    		subclass.setSelectedIndex(5);
    		break;
    	case 6:
    		subclass.setSelectedIndex(6);
    		break;
    	case 7:
    		subclass.setSelectedIndex(7);
    		break;
    	case 8:
    		subclass.setSelectedIndex(8);
    		break;
    	case 10:
    		subclass.setSelectedIndex(9);
    		break;
    	case 13:
    		subclass.setSelectedIndex(10);
    		break;
    	case 15:
    		subclass.setSelectedIndex(11);
    		break;
    	case 16:
    		subclass.setSelectedIndex(12);
    		break;
    	case 17:
    		subclass.setSelectedIndex(13);
    		break;
    	case 18:
    		subclass.setSelectedIndex(14);
    		break;
    	case 19:
    		subclass.setSelectedIndex(15);
    		break;
    	default:
    		System.err.println("Unknown Item Subclass");
    		break;
    	}
    	
    	bind.setSelectedIndex(item.getBinds());
    	if (item.getReqlvl() != 0)
    		reqlvl.setText(Integer.toString(item.getReqlvl()));
    	if (item.getIlvl() != 0)
    		ilvl.setText(Integer.toString(item.getIlvl()));
    	if (item.getUnique() != 0)
    		unique.setText(Integer.toString(item.getUnique()));
    	
    	int[] selectedStatIndices = new int[item.getStatsCount()];
    	int selectedSCounter = 0;
    	for (int i = 0; i < item.getStatsCount(); i++) {
    		if (item.getStat_value(i) != 0) {
    			switch(item.getStat_type(i)) {
    			case 3:
    				selectedStatIndices[selectedSCounter] = 0;
    				selectedSCounter++;
    				break;
    			case 4:
    				selectedStatIndices[selectedSCounter] = 2;
    				selectedSCounter++;
    				break;
    			case 5:
    				selectedStatIndices[selectedSCounter] = 3;
    				selectedSCounter++;
    				break;
    			case 6:
    				selectedStatIndices[selectedSCounter] = 4;
    				selectedSCounter++;
    				break;
    			case 7:
    				selectedStatIndices[selectedSCounter] = 1;
    				selectedSCounter++;
    				break;
    			case 12:
    				selectedStatIndices[selectedSCounter] = 13;
    				selectedSCounter++;
    				break;
    			case 13:
    				selectedStatIndices[selectedSCounter] = 14;
    				selectedSCounter++;
    				break;
    			case 14:
    				selectedStatIndices[selectedSCounter] = 15;
    				selectedSCounter++;
    				break;
    			case 15:
    				selectedStatIndices[selectedSCounter] = 16;
    				selectedSCounter++;
    				break;
    			case 31:
    				selectedStatIndices[selectedSCounter] = 7;
    				selectedSCounter++;
    				break;
    			case 32:
    				selectedStatIndices[selectedSCounter] = 8;
    				selectedSCounter++;
    				break;
    			case 35:
    				selectedStatIndices[selectedSCounter] = 17;
    				selectedSCounter++;
    				break;
    			case 36:
    				selectedStatIndices[selectedSCounter] = 9;
    				selectedSCounter++;
    				break;
    			case 37:
    				selectedStatIndices[selectedSCounter] = 12;
    				selectedSCounter++;
    				break;
    			case 38:
    				selectedStatIndices[selectedSCounter] = 6;
    				selectedSCounter++;
    				break;
    			case 44:
    				selectedStatIndices[selectedSCounter] = 10;
    				selectedSCounter++;
    				break;
    			case 45:
    				selectedStatIndices[selectedSCounter] = 5;
    				selectedSCounter++;
    				break;
    			case 47:
    				selectedStatIndices[selectedSCounter] = 11;
    				selectedSCounter++;
    				break;
    			default:
    				System.err.println("Stat that could not be selected was loaded to armor panel.");
    				// show error
    				break;
    			}
    		}
    	}
    	selectedStats.setSelectedIndices(selectedStatIndices);
    	
    	int[] selectedResistsIndices = new int[6];
    	int selectedRCounter = 0;
    	if (item.getFire_resist() != 0) {
    		selectedResistsIndices[selectedRCounter] = 0;
    		selectedRCounter++;
    	}
    	
    	if (item.getFrost_resist() != 0) {
    		selectedResistsIndices[selectedRCounter] = 1;
    		selectedRCounter++;
    	}
    	
    	if (item.getShadow_resist() != 0) {
    		selectedResistsIndices[selectedRCounter] = 2;
    		selectedRCounter++;
    	}	
    	
    	if (item.getHoly_resist() != 0) {
    		selectedResistsIndices[selectedRCounter] = 3;
    		selectedRCounter++;
    	}
    	
    	if (item.getNature_resist() != 0) {
    		selectedResistsIndices[selectedRCounter] = 4;
    		selectedRCounter++;
    	}
    	
    	if (item.getArcane_resist() != 0) {
    		selectedResistsIndices[selectedRCounter] = 5;
    		selectedRCounter++;
    	}
    	int[] newSelectedResistsIndices = new int[selectedRCounter];
    	for (int i = 0; i < selectedRCounter; i++) {
    		newSelectedResistsIndices[i] = selectedResistsIndices[i];
    	}
    	
    	selectedResists.setSelectedIndices(newSelectedResistsIndices);
    	
    	if (item.getSpell_id(0) != 0) {
    		spell1.setText(Integer.toString(item.getSpell_id(0)));
    		spelltrigger1.setSelectedIndex(item.getSpell_trigger(0));
    	}
    	if (item.getSpell_id(1) != 0) {
	    	spell2.setText(Integer.toString(item.getSpell_id(1)));
	    	spelltrigger2.setSelectedIndex(item.getSpell_trigger(1));
    	}
    	if (item.getSpell_id(2) != 0) {
	    	spell3.setText(Integer.toString(item.getSpell_id(2)));
	    	spelltrigger3.setSelectedIndex(item.getSpell_trigger(2));
    	}
    	if (item.getSpell_id(3) != 0) {
	    	spell4.setText(Integer.toString(item.getSpell_id(3)));
	    	spelltrigger4.setSelectedIndex(item.getSpell_trigger(3));
    	}
    	if (item.getSpell_id(4) != 0) {
	    	spell5.setText(Integer.toString(item.getSpell_id(4)));
	    	spelltrigger5.setSelectedIndex(item.getSpell_trigger(4));
    	}
    	
    	// Repaint Icon
    	if (!display.getText().isEmpty()) {
			String iconPath = "";
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
				itemIcon.resize(80, 80);
				itemIcon.repaint();
			} else {
				// Display Id invalid
				invalidDisplayId.setVisible(true);
				itemIcon.setImage("src/icons/Inv_misc_questionmark.png");
			}
		}
    	
    	//Repaint ToolTip
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
    
    private void clearFields() {
    	name.setText("");
    	desc.setText("");
    	display.setText("");
    	quality.setSelectedIndex(0);
    	equip.setSelectedIndex(0);
    	subclass.setSelectedIndex(0);
    	bind.setSelectedIndex(0);
    	reqlvl.setText("");
    	ilvl.setText("");
    	unique.setText("");
    	selectedStats.clearSelection();
    	selectedResists.clearSelection();
    	spell1.setText("");
    	spelltrigger1.setSelectedIndex(0);
    	spell2.setText("");
    	spelltrigger2.setSelectedIndex(0);
    	spell3.setText("");
    	spelltrigger3.setSelectedIndex(0);
    	spell4.setText("");
    	spelltrigger4.setSelectedIndex(0);
    	spell5.setText("");
    	spelltrigger5.setSelectedIndex(0);
    }
    
	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(createItem.getActionCommand())) {
			// Craft button clicked
			if (!name.getText().isEmpty()) {
				fillItem();
			} else {
				// name field is empty
				JOptionPane.showMessageDialog(ItemBuilder.getInstance().getIBFrame(), "Give your item a name.");
			}
		} else if (e.getActionCommand().equals(insertItem.getActionCommand())) {
			if (item.getEntry() != 0) {
				final JOptionPane optionPane = new JOptionPane(
		                "Insert item into Database?",
		                JOptionPane.QUESTION_MESSAGE,
		                JOptionPane.YES_NO_OPTION);
	
				final JDialog dialog = new JDialog(ItemBuilder.getInstance().getIBFrame(), 
				                             "Database Confirmation",
				                             true);
				dialog.setContentPane(optionPane);
				
				dialog.setLocation(dialog.getParent().getX()+650, dialog.getParent().getY()+350);
				optionPane.addPropertyChangeListener(
				    new PropertyChangeListener() {
				        public void propertyChange(PropertyChangeEvent e) {
				            String prop = e.getPropertyName();
		
				            if (dialog.isVisible() 
				             && (e.getSource() == optionPane)
				             && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
				                //If you were going to check something
				                //before closing the window, you'd do
				                //it here.
				                dialog.setVisible(false);
				            }
				        }
				    });
				dialog.pack();
				dialog.setVisible(true);
		
				int value;
				try {
					value = ((Integer)optionPane.getValue()).intValue();
				} catch(Exception e2) {
					// closed the window
					value = JOptionPane.NO_OPTION;
				}
				if (value == JOptionPane.YES_OPTION) {
					System.out.println("Inserting Item into DB.");
					try {
						db.insertItemIntoTemplate(item);
						int nextEntry = item.getEntry() + 1;
						SettingsPanel.getInstance().setNextEntryID(String.valueOf(nextEntry));
					} catch (Exception e1) {
						System.err.println("Failed to insert item into db.");
						e1.printStackTrace();
					}
				} else if (value == JOptionPane.NO_OPTION) {
				    System.out.println("Not inserting item into DB.");
				}
			} else {
				// entry == 0
				// No item to insert
				JOptionPane.showMessageDialog(ItemBuilder.getInstance().getIBFrame(), "You must craft an item first.");
			}
		}
	}
    
    @Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			createItem.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	
}
