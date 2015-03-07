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
import PYDevTools.utilities.MySQLAccess;

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
	private String[] toolTipLabels = { "Item Name", "Item Level", "Binds On", "Unique", "Equip", "Type", 
										"Damage", "Delay", "DPS", "Stats", "Duribility", "Spell Equips",
										"Required Level", "Sell Price" };
	private JLabel TTname, TTdesc, TTilvl, TTbinds, TTunique, TTequip, TTtype, TTmindamage, TTmaxdamage, TTdelay,
				   TTDPS, TTresist1, TTresist2, TTresist3, TTresist4, TTresist5, TTresist6,
				   TTsocket1, TTsocket2, TTsocket3, TTsocketbonus, TTduribility, TTreqclass, TTreqrace, TTspell1, 
				   TTspell2, TTspell3, TTspell4, TTspell5, TTreqlvl, TTset, TTsellprice;
	private JLabel[] TTstats = new JLabel[10];
	private int numTTLabels = toolTipLabels.length;
	private String[] labels = { "Name: ", "Description: ", "Display:", "Quality: ", "Equip: ", "Type: ",
								"Sheath: ", "Binds: ", "Delay: ", "Min Damage: ", "Max Damage", "Armor: ", 
								"Block: ", "Required Level: ","Item Level: ", "Unique: ", "Role: ", 
								"Stats: ", "Resists: ", "Spells: ", "Sockets: " };
	private int numLabels = labels.length;
	private JTextField name, desc, display, delay, mindamage, maxdamage, armor, block, reqlvl, ilvl, unique, spell, socket;
	private JComboBox<String> quality, equip, type, sheath, bind, role, resist;
	private JList<String> selectedStats;
	private String[] stats = { "Stamina", "Strength", "Agility", "Intellect", "Spirit", "Spell Power", "Attack Power",
								"Hit Rating", "Crit Rating", "Haste Rating", "Armor Penetration", "Spell Penetration", 
								"Expertise", "Defense", "Dodge", "Parry", "Resilience", };
	private String[] qualities = { "Gray", "White", "Green", "Blue", "Purple", "Orange", "Heirloom" };
	private String[] equips = { "One Handed", "Main Handed", "Off Handed", "Two Handed", "Bow", "Gun", "Thrown", "CrossBow", "Wand" };
	private String[] types = { "1H Axe", "2H Axe", "Bow", "Gun", "1H Mace", "2H Mace", "Polearm", "1H Sword", "2H Sword",
								"Staff", "Fist", "Dagger", "Throwing", "Spear", "Crossbow", "Wand" };
	private String[] sheaths = { "One Handed", "Two Handed", "Staff", "Shield", "Off Hand" };
	private String[] binds = { "None", "On Pick Up", "On Equip", "On Use" };
	private String[] roles = { "DPS", "Tank", "Healer" };
	private String[] resists = { "Fire", "Frost", "Shadow", "Holy", "Nature", "Arcane" };
	private JLabel invalidDisplayId;
	private Font italicFont = new Font("Arial", Font.ITALIC, 14);
	
	private ItemIconFinder iconFinder = new ItemIconFinder();
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
					type = new JComboBox<String>(types);
					type.setSelectedIndex(0);
					type.addActionListener(this);
					type.setActionCommand("types");
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
					selectedStats.setSelectedIndex(0);
					l.setLabelFor(selectedStats);
					leftPanel.add(selectedStats);
					break;
				case 18:
					resist = new JComboBox<String>(resists);
					l.setLabelFor(resist);
					leftPanel.add(resist);
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
		itemToolTip.resize(550, 550);
		SpringLayout.Constraints toolTipCons = rightLayout.getConstraints(itemToolTip);
		toolTipCons.setX(Spring.constant(210));
		toolTipCons.setY(Spring.constant(60));
		rightPanel.add(itemToolTip);
		
		TTname = new JLabel("", JLabel.TRAILING);
		TTname.setForeground(Color.WHITE);
		TTname.setLocation(10, 30);
		itemToolTip.add(TTname);
		TTdesc = new JLabel("", JLabel.TRAILING);
		TTdesc.setForeground(Color.WHITE);
		TTdesc.setLocation(10, 0);
		itemToolTip.add(TTdesc);
		TTilvl = new JLabel("", JLabel.TRAILING);
		TTilvl.setForeground(Color.YELLOW);
		TTilvl.setLocation(10, 60);
		itemToolTip.add(TTilvl);
		TTbinds = new JLabel("", JLabel.TRAILING);
		TTbinds.setForeground(Color.WHITE);
		TTbinds.setLocation(10, 90);
		itemToolTip.add(TTbinds);
		TTunique = new JLabel("", JLabel.TRAILING);
		TTunique.setForeground(Color.WHITE);
		TTunique.setLocation(10, 120);
		itemToolTip.add(TTunique);
		TTequip = new JLabel("", JLabel.TRAILING);
		TTequip.setForeground(Color.WHITE);
		TTequip.setLocation(10, 150);
		itemToolTip.add(TTequip);
		TTtype = new JLabel("", JLabel.TRAILING);
		TTtype.setForeground(Color.WHITE);
		TTtype.setLocation(10, 150);
		itemToolTip.add(TTtype);
		TTmindamage = new JLabel("", JLabel.TRAILING);
		TTmindamage.setForeground(Color.WHITE);
		TTmindamage.setLocation(10, 180);
		itemToolTip.add(TTmindamage);
		TTmaxdamage = new JLabel("", JLabel.TRAILING);
		TTdelay = new JLabel("", JLabel.TRAILING);
		TTdelay.setForeground(Color.WHITE);
		TTdelay.setLocation(410, 180);
		itemToolTip.add(TTdelay);
		TTDPS = new JLabel("", JLabel.TRAILING);
		TTDPS.setForeground(Color.WHITE);
		TTDPS.setLocation(10, 210);
		itemToolTip.add(TTDPS);
		for (int i = 0; i < 10; i++) {
			TTstats[i] = new JLabel("", JLabel.TRAILING);
			TTstats[i].setForeground(Color.WHITE);
			TTstats[i].setLocation(10, 0);
			itemToolTip.add(TTstats[i]);
		}
		TTresist1 = new JLabel("", JLabel.TRAILING);
		TTresist1.setForeground(Color.WHITE);
		TTresist1.setLocation(10, 0);
		itemToolTip.add(TTresist1);
		TTresist2 = new JLabel("", JLabel.TRAILING);
		TTresist2.setForeground(Color.WHITE);
		TTresist2.setLocation(10, 0);
		itemToolTip.add(TTresist2);
		TTresist3 = new JLabel("", JLabel.TRAILING);
		TTresist3.setForeground(Color.WHITE);
		TTresist3.setLocation(10, 0);
		itemToolTip.add(TTresist3);
		TTresist4 = new JLabel("", JLabel.TRAILING);
		TTresist4.setForeground(Color.WHITE);
		TTresist4.setLocation(10, 0);
		itemToolTip.add(TTresist4);
		TTresist5 = new JLabel("", JLabel.TRAILING);
		TTresist5.setForeground(Color.WHITE);
		TTresist5.setLocation(10, 0);
		itemToolTip.add(TTresist5);
		TTresist6 = new JLabel("", JLabel.TRAILING);
		TTresist6.setForeground(Color.WHITE);
		TTresist6.setLocation(10, 0);
		itemToolTip.add(TTresist6);
		TTsocket1 = new JLabel("", JLabel.TRAILING);
		TTsocket1.setForeground(Color.WHITE);
		TTsocket1.setLocation(10, 0);
		itemToolTip.add(TTsocket1);
		TTsocket2 = new JLabel("", JLabel.TRAILING);
		TTsocket2.setForeground(Color.WHITE);
		TTsocket2.setLocation(10, 0);
		itemToolTip.add(TTsocket2);
		TTsocket3 = new JLabel("", JLabel.TRAILING);
		TTsocket3.setForeground(Color.WHITE);
		TTsocket3.setLocation(10, 0);
		itemToolTip.add(TTsocket3);
		TTsocketbonus = new JLabel("", JLabel.TRAILING);
		TTsocketbonus.setForeground(Color.WHITE);
		TTsocketbonus.setLocation(10, 0);
		itemToolTip.add(TTsocketbonus);
		TTduribility = new JLabel("", JLabel.TRAILING);
		TTduribility.setForeground(Color.WHITE);
		TTduribility.setLocation(10, 0);
		itemToolTip.add(TTduribility);
		TTreqclass = new JLabel("", JLabel.TRAILING);
		TTreqclass.setForeground(Color.WHITE);
		TTreqclass.setLocation(10, 0);
		itemToolTip.add(TTreqclass);
		TTreqrace = new JLabel("", JLabel.TRAILING);
		TTreqrace.setForeground(Color.WHITE);
		TTreqrace.setLocation(10, 0);
		itemToolTip.add(TTreqrace);
		TTspell1 = new JLabel("", JLabel.TRAILING);
		TTspell1.setForeground(Color.WHITE);
		TTspell1.setLocation(10, 0);
		itemToolTip.add(TTspell1);
		TTspell2 = new JLabel("", JLabel.TRAILING);
		TTspell2.setForeground(Color.WHITE);
		TTspell2.setLocation(10, 0);
		itemToolTip.add(TTspell2);
		TTspell3 = new JLabel("", JLabel.TRAILING);
		TTspell3.setForeground(Color.WHITE);
		TTspell3.setLocation(10, 0);
		itemToolTip.add(TTspell3);
		TTspell4 = new JLabel("", JLabel.TRAILING);
		TTspell4.setForeground(Color.WHITE);
		TTspell4.setLocation(10, 0);
		itemToolTip.add(TTspell4);
		TTspell5 = new JLabel("", JLabel.TRAILING);
		TTspell5.setForeground(Color.WHITE);
		TTspell5.setLocation(10, 0);
		itemToolTip.add(TTspell5);
		TTreqlvl = new JLabel("", JLabel.TRAILING);
		TTreqlvl.setForeground(Color.WHITE);
		TTreqlvl.setLocation(10, 0);
		itemToolTip.add(TTreqlvl);
		TTset = new JLabel("", JLabel.TRAILING);
		TTset.setForeground(Color.WHITE);
		TTset.setLocation(10, 0);
		itemToolTip.add(TTset);
		TTsellprice = new JLabel("", JLabel.TRAILING);
		TTsellprice.setForeground(Color.WHITE);
		TTsellprice.setLocation(10, 0);
		itemToolTip.add(TTsellprice);
		
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
	public void focusLost(FocusEvent e) {
		if (e.getComponent().equals(display)) {
			String iconPath = "";
			if (!display.getText().isEmpty()) {
				int displayIdText = 0;
				try {
					displayIdText = Integer.parseInt(display.getText());
				} catch (NumberFormatException ex) {
					// Display Id invalid
					invalidDisplayId.setVisible(true);
					itemIcon.setImage("src/icons/Inv_misc_questionmark.png");
				}
				iconPath = iconFinder.findIconByDisplayId(displayIdText);
				if (!iconPath.isEmpty()) {
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
		if (e.getComponent().equals(name)) {
			TTname.setText(name.getText());
			itemToolTip.repaint();
		}
		if (e.getComponent().equals(desc)) {
			TTdesc.setText(desc.getText());
			itemToolTip.repaint();
		}
		if (e.getComponent().equals(delay)) {
			TTmindamage.setVisible(false);
			float delayInSecs = Float.parseFloat(delay.getText());
			TTdelay.setText(TTmindamage.getText() + "Speed " + String.format("%.2g%n", delayInSecs/1000.00f) + "0");
			itemToolTip.repaint();
		}
		if (e.getComponent().equals(mindamage)) {
			TTmindamage.setText(mindamage.getText());
			itemToolTip.repaint();
		}
		if (e.getComponent().equals(maxdamage)) {
			TTmindamage.setText(mindamage.getText() + " - " + maxdamage.getText() + " Damage");
			itemToolTip.repaint();
		}
		if (e.getComponent().equals(armor)) {
			//TTarmor.setText(armor.getText());
			itemToolTip.repaint();
		}
		if (e.getComponent().equals(block)) {
			//TTblock.setText(block.getText());
			itemToolTip.repaint();
		}
		if (e.getComponent().equals(reqlvl)) {
			TTreqlvl.setText(reqlvl.getText());
			itemToolTip.repaint();
		}
		if (e.getComponent().equals(ilvl)) {
			TTilvl.setText("Item Level " + ilvl.getText());
			itemToolTip.repaint();
		}
		if (e.getComponent().equals(unique)) {
			int uniqueNum = 0;
			try {
				uniqueNum = Integer.parseInt(unique.getText());
				
				if (uniqueNum == 1)
					TTunique.setText("Unique");
				else if (uniqueNum == 0)
					TTunique.setText("");
				else
					TTunique.setText("Unique-" + unique.getText());
			} catch (NumberFormatException ex) {
				// TODO
				// Print Error Message
				ex.printStackTrace();
			}
			itemToolTip.repaint();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(createItem.getActionCommand())) {
			// Craft button clicked
			fillItem();
			/*try {
				if (item.getEntry() != 0 && !db.isUsedEntry(item.getEntry()))
					db.insertWeaponIntoTemplate(item);
			} catch (Exception e1) {
				e1.printStackTrace();
			}*/
		}
		if (e.getActionCommand().equals(quality.getActionCommand())) {
			switch (quality.getSelectedIndex()) {
			case 0:
				TTname.setForeground(Color.gray);
				break;
			case 1:
				TTname.setForeground(Color.white);
				break;
			case 2:
				TTname.setForeground(Color.green);
				break;
			case 3:
				TTname.setForeground(Color.blue);
				break;
			case 4:
				TTname.setForeground(Color.magenta);
				break;
			case 5:
				TTname.setForeground(Color.orange);
				break;
			case 6:
				TTname.setForeground(Color.yellow);
				break;
			}
			itemToolTip.repaint();
		}
		
		if (e.getActionCommand().equals(bind.getActionCommand())) {
			switch (bind.getSelectedIndex()) {
			case 0:
				TTbinds.setText("None");
				break;
			case 1:
				TTbinds.setText("Binds when picked up");
				break;
			case 2:
				TTbinds.setText("Binds when equipped");
				break;
			case 3:
				TTbinds.setText("Binds when used");
				break;
			}
			itemToolTip.repaint();
		}
		
		if (e.getActionCommand().equals(equip.getActionCommand())) {
			switch (equip.getSelectedIndex()) {
			case 0:
				TTequip.setText("One-Hand");
				break;
			case 1:
				TTequip.setText("Main-Hand");
				break;
			case 2:
				TTequip.setText("Off-Hand");
				break;
			case 3:
				TTequip.setText("Two-Handed");
				break;
			case 4:
			case 5:
			case 7:
			case 8:
				TTequip.setText("Ranged");
				break;
			case 6:
				TTequip.setText("Thrown");
				break;
			}
			itemToolTip.repaint();
		}
		
		if (e.getActionCommand().equals(type.getActionCommand())) {
			TTequip.setVisible(false);
			switch (type.getSelectedIndex()) {
			case 0:
			case 1:
				TTtype.setText("Axe");
				break;
			case 2:
				TTtype.setText("Bow");
				break;
			case 3:
				TTtype.setText("Gun");
				break;
			case 4:
			case 5:
				TTtype.setText("Mace");
				break;
			case 6:
				TTtype.setText("Polearm");
				break;
			case 7:
			case 8:
				TTtype.setText("Sword");
				break;
			case 9:
				TTtype.setText("Staff");
				break;
			case 10:
				TTtype.setText("Fist");
				break;
			case 11:
				TTtype.setText("Dagger");
				break;
			case 12:
				TTtype.setText("Throwing");
				break;
			case 13:
				TTtype.setText("Spear");
				break;
			case 14:
				TTtype.setText("Crossbow");
				break;
			case 15:
				TTtype.setText("Wand");
				break;
			}
			TTtype.setLocation(((itemToolTip.getWidth()-30)-(TTtype.getText().length()*10)), 150);
			itemToolTip.repaint();
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
    	if (!name.getText().isEmpty()) {
    		item.setName(name.getText());
    	}
    	if (!desc.getText().isEmpty()) {
    		item.setDescription(desc.getText());
    	}
    	if (!display.getText().isEmpty()) {
    		item.setDisplay(Integer.parseInt(display.getText()));
    	}
    	item.setQuality(quality.getSelectedIndex());
    	switch (equip.getSelectedIndex()) {
    	case 0:
    		// One Handed
    		item.setEquip(13);
    		break;
    	case 1:
    		// Main Handed
    		item.setEquip(21);
    		break;
    	case 2:
    		// Off Handed
    		item.setEquip(22);
    		break;
    	case 3:
    		// Two Handed
    		item.setEquip(17);
    		break;
    	case 4:
    		// Bow
    		item.setEquip(15);
    		break;
    	case 5:
    		// Gun
    		item.setEquip(26);
    		break;
    	case 6:
    		// Thrown
    		item.setEquip(25);
    		break;
    	case 7:
    		// Crossbow
    		item.setEquip(15);
    		break;
    	case 8:
    		// Wands
    		item.setEquip(26);
    		break;
    	}
    	switch(type.getSelectedIndex()) {
    	case 0:
    		// 1H axe
    		item.setType(0);
    		break;
    	case 1:
    		// 2H axe
    		item.setType(1);
    		break;
    	case 2:
    		// Bow
    		item.setType(2);
    		break;
    	case 3:
    		// Gun
    		item.setType(3);
    		break;
    	case 4:
    		// 1H mace
    		item.setType(4);
    		break;
    	case 5:
    		// 2H mace
    		item.setType(5);
    		break;
    	case 6:
    		// Polearm
    		item.setType(6);
    		break;
    	case 7:
    		// 1H sword
    		item.setType(7);
    		break;
    	case 8:
    		// 2H sword
    		item.setType(8);
    		break;
    	case 9:
    		// Staff
    		item.setType(10);
    		break;
    	case 10:
    		// Fist
    		item.setType(13);
    		break;
    	case 11:
    		// Dagger
    		item.setType(15);
    		break;
    	case 12:
    		// Thrown
    		item.setType(16);
    		break;
    	case 13:
    		// Spear
    		item.setType(17);
    		break;
    	case 14:
    		// Crossbow
    		item.setType(18);
    		break;
    	case 15:
    		// Wand
    		item.setType(19);
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
    	if (!delay.getText().isEmpty()) {
    		item.setDelay(Integer.parseInt(delay.getText()));
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
    	
    	// Stats
    	int statsToCalc[] = selectedStats.getSelectedIndices();
    	
    	if (statsToCalc.length > 10) {
    		// print error message, can only have 10 stats
    	}
    	
    	for (int i = 0; i < statsToCalc.length || i < 10; i++) {
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
    		
    		float typeMultiplier = 1;
    		
    		switch (item.getType()) {
    		// One handers
    		case 0:
    		case 4:
    		case 7:
    		case 13:
    		case 15:
    			typeMultiplier = 0.85f;
    			break;
    		// ranged
    		case 2:
    		case 3:
    		case 16:
    		case 17:
    		case 18:
    		case 19:
    			typeMultiplier = 1.15f;
    			break;
    		// Two handers
    		case 1:
    		case 5:
    		case 6:
    		case 8:
    		case 10:
    			typeMultiplier = 1.55f;
    			break;
    		default:
    			break;
    		}
    		// TODO: Make this serverMultiplier an option
    		serverMultiplier = 1;
    		// Do calculation for value based off of quality and ilvl and type
    		float statValue = serverMultiplier*typeMultiplier*statMultiplier*item.getIlvl()*item.getQuality()/4;
    		item.setStat_value(i, (int)statValue);
    		if (item.getStat_value(i) != 0) {
    			if (isEquipStat) {
    				TTstats[i].setForeground(Color.GREEN);
    				TTstats[i].setText(statText + item.getStat_value(i) + ".");
    			} else {
    				TTstats[i].setForeground(Color.WHITE);
    				TTstats[i].setText("+" + item.getStat_value(i) + statText);
    			}
    			
    			TTstats[i].setLocation(10, 240+(i*30));
    			itemToolTip.repaint();
    		}
    	}
    	
    	// Resists
    	
    	
    	// Spells
    	
    	
    	// Sockets
    	
    }
}
