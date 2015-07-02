package PYDevTools.utilities;

import java.awt.Color;
import java.io.File;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JLabel;

import PYDevTools.db.structures.Item;
import PYDevTools.enums.ItemType;

@SuppressWarnings("serial")
public class ItemToolTip extends ImageDrawingComponent {
	private Item item;
	private String iconPath;
	private String[] toolTipLabels = { "Item Name", "Item Level", "Binds On", "Unique", "Equip", "subclass", 
			"Damage", "Delay", "DPS", "Stats", "Duribility", "Spell Equips",
			"Required Level", "Sell Price" };
	private JLabel TTname, TTilvl, TTbinds, TTunique, TTequip, TTsubclass, TTmindamage, TTmaxdamage, TTdelay,
	   TTDPS, TTsocket1, TTsocket2, TTsocket3, TTsocketbonus, TTduribility, TTreqclass, TTreqrace, 
	   TTreqlvl, TTset, TTsellprice, TTarmor, TTblock;
	private ArrayList<JLabel> TTdesc = new ArrayList<JLabel>();
	private JLabel[] TTstats = new JLabel[10];
	private JLabel[] TTresists = new JLabel[6];
	private ArrayList<ArrayList<JLabel>> TTspells = new ArrayList<ArrayList<JLabel>>();
	private int numberOfLines = 1;
	private final int lineHeight = 30;
	private int toolTipHeight = numberOfLines*lineHeight;
	private final int toolTipWidth = 550;
	private SpellFinder spellFinder = SpellFinder.getInstance();
	
	public ItemToolTip(Item item) throws MalformedURLException {
		super(new File("src/icons/tooltip.png").toURI().toURL());
		this.item = item;
		
		iconPath = ItemIconFinder.getInstance().findIconByDisplayId(item.getDisplay());
		
		TTname = new JLabel("", JLabel.TRAILING);
		TTname.setForeground(Color.WHITE);
		TTname.setLocation(10, 0);
		add(TTname);
		
		TTilvl = new JLabel("", JLabel.TRAILING);
		TTilvl.setForeground(Color.YELLOW);
		TTilvl.setLocation(10, 0);
		add(TTilvl);
		
		TTbinds = new JLabel("", JLabel.TRAILING);
		TTbinds.setForeground(Color.WHITE);
		TTbinds.setLocation(10, 0);
		add(TTbinds);
		
		TTunique = new JLabel("", JLabel.TRAILING);
		TTunique.setForeground(Color.WHITE);
		TTunique.setLocation(10, 0);
		add(TTunique);
		
		TTequip = new JLabel("", JLabel.TRAILING);
		TTequip.setForeground(Color.WHITE);
		TTequip.setLocation(10, 0);
		add(TTequip);
		TTsubclass = new JLabel("", JLabel.TRAILING);
		TTsubclass.setForeground(Color.WHITE);
		TTsubclass.setLocation(10, 0);
		add(TTsubclass);
		
		TTmindamage = new JLabel("", JLabel.TRAILING);
		TTmindamage.setForeground(Color.WHITE);
		TTmindamage.setLocation(10, 0);
		add(TTmindamage);
		TTmaxdamage = new JLabel("", JLabel.TRAILING);
		TTmaxdamage.setForeground(Color.WHITE);
		TTmaxdamage.setLocation(10, 0);
		add(TTmaxdamage);
		TTdelay = new JLabel("", JLabel.TRAILING);
		TTdelay.setForeground(Color.WHITE);
		TTdelay.setLocation(410, 0);
		add(TTdelay);
		
		TTDPS = new JLabel("", JLabel.TRAILING);
		TTDPS.setForeground(Color.WHITE);
		TTDPS.setLocation(10, 0);
		add(TTDPS);
		
		TTarmor = new JLabel("", JLabel.TRAILING);
		TTarmor.setForeground(Color.WHITE);
		TTarmor.setLocation(10, 0);
		add(TTarmor);
		
		TTblock = new JLabel("", JLabel.TRAILING);
		TTblock.setForeground(Color.WHITE);
		TTblock.setLocation(10, 0);
		add(TTblock);
		
		for (int i = 0; i < 10; i++) {
			TTstats[i] = new JLabel("", JLabel.TRAILING);
			TTstats[i].setForeground(Color.WHITE);
			TTstats[i].setLocation(10, 0);
			add(TTstats[i]);
		}
		for (int i = 0; i < 6; i++) {
			TTresists[i] = new JLabel("", JLabel.TRAILING);
			TTresists[i].setForeground(Color.WHITE);
			TTresists[i].setLocation(10, 0);
			add(TTresists[i]);
		}
		
		TTduribility = new JLabel("", JLabel.TRAILING);
		TTduribility.setForeground(Color.WHITE);
		TTduribility.setLocation(10, 0);
		add(TTduribility);
		
		for (int i = 0; i < 5; i++) {
			ArrayList<JLabel> sDesc = new ArrayList<JLabel>();
			TTspells.add(i, sDesc);
		}
		
		TTsocket1 = new JLabel("", JLabel.TRAILING);
		TTsocket1.setForeground(Color.WHITE);
		TTsocket1.setLocation(10, 0);
		add(TTsocket1);
		TTsocket2 = new JLabel("", JLabel.TRAILING);
		TTsocket2.setForeground(Color.WHITE);
		TTsocket2.setLocation(10, 0);
		add(TTsocket2);
		TTsocket3 = new JLabel("", JLabel.TRAILING);
		TTsocket3.setForeground(Color.WHITE);
		TTsocket3.setLocation(10, 0);
		add(TTsocket3);
		TTsocketbonus = new JLabel("", JLabel.TRAILING);
		TTsocketbonus.setForeground(Color.WHITE);
		TTsocketbonus.setLocation(10, 0);
		add(TTsocketbonus);
		TTreqclass = new JLabel("", JLabel.TRAILING);
		TTreqclass.setForeground(Color.WHITE);
		TTreqclass.setLocation(10, 0);
		add(TTreqclass);
		TTreqrace = new JLabel("", JLabel.TRAILING);
		TTreqrace.setForeground(Color.WHITE);
		TTreqrace.setLocation(10, 0);
		add(TTreqrace);
		
		TTset = new JLabel("", JLabel.TRAILING);
		TTset.setForeground(Color.WHITE);
		TTset.setLocation(10, 0);
		add(TTset);
		
		TTreqlvl = new JLabel("", JLabel.TRAILING);
		TTreqlvl.setForeground(Color.WHITE);
		TTreqlvl.setLocation(10, 0);
		add(TTreqlvl);
		
		TTsellprice = new JLabel("", JLabel.TRAILING);
		TTsellprice.setForeground(Color.WHITE);
		TTsellprice.setLocation(10, 0);
		add(TTsellprice);
		
		resize(toolTipWidth, 100);
		
		fillToolTip();
	}
	
	private void incrementLines() {
		numberOfLines++;
		toolTipHeight = numberOfLines*lineHeight;
	}
	
	private void decrementLines() {
		numberOfLines--;
		toolTipHeight = numberOfLines*lineHeight;
	}
	
	public void fillToolTip() {
		numberOfLines = 0;
		toolTipHeight = 0;
		
		if (!item.getName().isEmpty()) {
			switch (item.getQuality()) {
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
			TTname.setText(item.getName());
			incrementLines();
			TTname.setLocation(10, toolTipHeight);
		}
		if (item.getIlvl() != 0) {
			TTilvl.setText("Item Level " + String.valueOf(item.getIlvl()));
			incrementLines();
			TTilvl.setLocation(10, toolTipHeight);
		}
		
		switch (item.getBinds()) {
		case 0:
			TTbinds.setText("");
			break;
		case 1:
			TTbinds.setText("Binds when picked up");
			incrementLines();
			TTbinds.setLocation(10, toolTipHeight);
			break;
		case 2:
			TTbinds.setText("Binds when equipped");
			incrementLines();
			TTbinds.setLocation(10, toolTipHeight);
			break;
		case 3:
			TTbinds.setText("Binds when used");
			incrementLines();
			TTbinds.setLocation(10, toolTipHeight);
			break;
		}
		
		if (item.getUnique() == 1) {
			TTunique.setText("Unique");
			incrementLines();
			TTunique.setLocation(10, toolTipHeight);
		} else if (item.getUnique() == 0) {
			TTunique.setText("");
		} else {
			TTunique.setText("Unique-" + item.getUnique());
			incrementLines();
			TTunique.setLocation(10, toolTipHeight);
		}
		
		switch (item.getInventoryType()) {
		case 1:
			TTequip.setText("Head");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 2:
			TTequip.setText("Neck");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 3:
			TTequip.setText("Shoulder");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 4:
			TTequip.setText("Shirt");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 5:
			TTequip.setText("Chest");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 6:
			TTequip.setText("Waist");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 7:
			TTequip.setText("Legs");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 8:
			TTequip.setText("Feet");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 9:
			TTequip.setText("Wrists");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 10:
			TTequip.setText("Hands");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 11:
			TTequip.setText("Finger");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 12:
			TTequip.setText("Trinket");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 13:
			TTequip.setText("One-Hand");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 14:
			//TTequip.setText("Shield");
			TTequip.setText("Off-Hand");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 16:
			TTequip.setText("Back");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 18:
			TTequip.setText("Bag");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 19:
			TTequip.setText("Tabard");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 20:
			TTequip.setText("Robe");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 21:
			TTequip.setText("Main-Hand");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 22:
			TTequip.setText("Off-Hand");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 23:
			TTequip.setText("Tome");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 24:
			TTequip.setText("Ammo");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 17:
			TTequip.setText("Two-Handed");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 15:
		case 26:
			TTequip.setText("Ranged");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 25:
			TTequip.setText("Thrown");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 27:
			TTequip.setText("Quiver");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		case 28:
			TTequip.setText("Relic");
			incrementLines();
			TTequip.setLocation(10, toolTipHeight);
			break;
		default:
			TTequip.setText("");
			break;
		}
		
		if (item.getItemType() == ItemType.armor) {
			switch (item.getSubclass()) {
			case 0:
				TTsubclass.setText("");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 1:
				TTsubclass.setText("Cloth");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 2:
				TTsubclass.setText("Leather");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 3:
				TTsubclass.setText("Mail");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 4:
				TTsubclass.setText("Plate");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 6:
				TTsubclass.setText("Shield");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 7:
				TTsubclass.setText("Libram");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 8:
				TTsubclass.setText("Idol");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 9:
				TTsubclass.setText("Totem");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 10:
				TTsubclass.setText("Sigil");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			default:
				TTsubclass.setText("");
				break;
			}
		} else if (item.getItemType() == ItemType.weapon) {
			switch (item.getSubclass()) {
			case 0:
			case 1:
				TTsubclass.setText("Axe");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 2:
				TTsubclass.setText("Bow");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 3:
				TTsubclass.setText("Gun");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 4:
			case 5:
				TTsubclass.setText("Mace");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 6:
				TTsubclass.setText("Polearm");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 7:
			case 8:
				TTsubclass.setText("Sword");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 10:
				TTsubclass.setText("Staff");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 13:
				TTsubclass.setText("Fist");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 15:
				TTsubclass.setText("Dagger");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 16:
				TTsubclass.setText("Throwing");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 17:
				TTsubclass.setText("Spear");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 18:
				TTsubclass.setText("Crossbow");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			case 19:
				TTsubclass.setText("Wand");
				TTsubclass.setLocation(((toolTipWidth-10)-(TTsubclass.getText().length()*15)), toolTipHeight);
				break;
			default:
				TTsubclass.setText("");
				break;
			}
		}
		
		// If its a weapon
		if (item.getItemType() == ItemType.weapon) {
			if (item.getMinDamage() != 0) {
				incrementLines();
				TTmindamage.setText(String.valueOf(item.getMinDamage()));
				TTmindamage.setLocation(10, toolTipHeight);
				TTmaxdamage.setText(" - " + item.getMaxDamage());
				TTmaxdamage.setLocation(TTmindamage.getText().length()*15+10, toolTipHeight);
				TTdelay.setText("Speed " + item.getDelay()/1000.0f + "0");
				TTdelay.setLocation(410, toolTipHeight);
				
				// rough dps
				float dps = (item.getMinDamage() + item.getMaxDamage());
				dps /= 2;
				dps /= ((float)item.getDelay()/1000);
				dps = round(dps, 2);
				
				incrementLines();
				TTDPS.setText("(" + dps + " damage per second)");
				TTDPS.setLocation(10, toolTipHeight);
			}
		}
		
		if (item.getArmor() != 0) {
			incrementLines();
			TTarmor.setText(String.valueOf(item.getArmor()) + " Armor");
			TTarmor.setLocation(10, toolTipHeight);
		}
		
		if (item.getBlock() != 0) {
			incrementLines();
			TTblock.setText(String.valueOf(item.getBlock()) + " Block");
			TTblock.setLocation(10, toolTipHeight);
		}
		
		for (int i = 0; i < 10; i++) {
			String statText = "";
    		// initialize true because their are less normal stats
    		boolean isEquipStat = true;
			switch(item.getStat_type(i)) {
			case 3: //agility
    			statText = " Agility";
    			isEquipStat = false;
    			break;
    		case 7: //stamina
    			statText = " Stamina";
    			isEquipStat = false;
    			break;
    		case 4: //strength
    			statText = " Strength";
    			isEquipStat = false;
    			break;
			case 5: //intellect
				statText = " Intellect";
				isEquipStat = false;
			    break;
			case 6: //spirit
				statText = " Spirit";
				isEquipStat = false;
				break;
			case 45: //spell power
				statText = "Equip: Increases spell power by ";
				break;
			case 38: //attack power
				statText = "Equip: Increases attack power by ";
				break;
			case 31: //hitrating
				statText = "Equip: Improves hit rating by ";
				break;
			case 32: //critrating
				statText = "Equip: Improves critical strike rating by ";
				break;
			case 36: //hasterating
				statText = "Equip: Improves haste rating by ";
				break;
			case 44: //armorpen
				statText = "Equip: Improves armor penetration by ";
				break;
			case 47: //spellpen
				statText = "Equip: Improves spell penetration by ";
				break;
			case 37: //expertise
				statText = "Equip: Improves expertise rating by ";
				break;
			case 12: //defense
				statText = "Equip: Improves defense rating by ";
				break;
			case 13: //dodge
				statText = "Equip: Improves dodge rating by ";
				break;
			case 14: //parry
				statText = "Equip: Improves parry rating by ";
				break;
			case 35: //resilience
				statText = "Equip: Improves your resilience rating by ";
				break;
			default: //nothing
    			// No stats should be added to item
    			statText = "";
    			break;
    		}
			
			if (item.getStat_value(i) != 0) {
				incrementLines();
    			if (isEquipStat) {
    				TTstats[i].setForeground(Color.GREEN);
    				TTstats[i].setText(statText + item.getStat_value(i) + ".");
    			} else {
    				TTstats[i].setForeground(Color.WHITE);
    				TTstats[i].setText("+" + item.getStat_value(i) + statText);
    			}
    		}
			TTstats[i].setLocation(10, toolTipHeight);
		}
		for (int i = 0; i < 6; i++) {
			String resistText = "";
			switch(i) {
    		case 0: //fire
    			if (item.getFire_resist() != 0) {
	    			resistText = "Equip: Increases fire resist by ";
	    			TTresists[i].setForeground(Color.GREEN);
	    			TTresists[i].setText(resistText + item.getFire_resist() + ".");
	    			incrementLines();
	    			TTresists[i].setLocation(10, toolTipHeight);
    			}
    			break;
    		case 1: //frost
    			if (item.getFrost_resist() != 0) {
	    			resistText = "Equip: Increases frost resist by ";
	    			TTresists[i].setForeground(Color.GREEN);
	    			TTresists[i].setText(resistText + item.getFrost_resist() + ".");
	    			incrementLines();
	    			TTresists[i].setLocation(10, toolTipHeight);
    			}
    			break;
    		case 2: //shadow
    			if (item.getShadow_resist() != 0) {
	    			resistText = "Equip: Increases shadow resist by ";
	    			TTresists[i].setForeground(Color.GREEN);
	    			TTresists[i].setText(resistText + item.getShadow_resist() + ".");
	    			incrementLines();
	    			TTresists[i].setLocation(10, toolTipHeight);
    			}
    			break;
			case 3: //holy
				if (item.getHoly_resist() != 0) {
	    			resistText = "Equip: Increases holy resist by ";
	    			TTresists[i].setForeground(Color.GREEN);
	    			TTresists[i].setText(resistText + item.getHoly_resist() + ".");
	    			incrementLines();
	    			TTresists[i].setLocation(10, toolTipHeight);
				}
			    break;
			case 4: //nature
				if (item.getNature_resist() != 0) {
	    			resistText = "Equip: Increases nature resist by ";
	    			TTresists[i].setForeground(Color.GREEN);
	    			TTresists[i].setText(resistText + item.getNature_resist() + ".");
	    			incrementLines();
	    			TTresists[i].setLocation(10, toolTipHeight);
				}
				break;
			case 5: //arcane
				if (item.getArcane_resist() != 0) {
					resistText = "Equip: Increases nature resist by ";
    				TTresists[i].setForeground(Color.GREEN);
    				TTresists[i].setText(resistText + item.getArcane_resist() + ".");
    				incrementLines();
    				TTresists[i].setLocation(10, toolTipHeight);
				}
				break;
			default: //nothing
    			// No resists should be added to item
    			break;
    		}
		}
		
		if (item.getDuribility() != 0) {
			incrementLines();
			TTduribility.setText("Duribility " + item.getDuribility() + " / " + item.getDuribility());
			TTduribility.setLocation(10, toolTipHeight);
		}
		
		for (int i = 0; i < 5; i++) {
			if (item.getSpell_id(i) != 0) {
				String prefix = "";
				switch(item.getSpell_trigger(i)) {
				case 0: // Use
					prefix = "Use: ";
					break;
				case 1: // On Equip
					prefix = "Equip: ";
					break;
				case 2: // Chance on hit
					prefix = "Chance on hit: ";
					break;
				case 4: // Soulstone
					prefix = "Soulstone: ";
					break;
				case 5: // Use with no delay
					prefix = "Use: ";
					break;
				case 6: // Learn spell ID
					prefix = "Learn: ";
					break;
				}
				
				String spellDesc = spellFinder.findSpellDescriptionById(item.getSpell_id(i));
				ArrayList<String> descLines = stringToStringArray(prefix+spellDesc, 45);
				
				for (int j = 0; j < descLines.size(); j++) {
					TTspells.get(i).add(j, new JLabel());
					incrementLines();
					TTspells.get(i).get(j).setText(descLines.get(j));
					TTspells.get(i).get(j).setForeground(Color.GREEN);
					TTspells.get(i).get(j).setLocation(10, toolTipHeight);
					add(TTspells.get(i).get(j));
				}
			}
		}
		
		/*
		TTsocket1.setLocation(10, 0);
		TTsocket2.setLocation(10, 0);
		TTsocket3.setLocation(10, 0);
		TTsocketbonus.setLocation(10, 0);
		
		TTreqclass.setLocation(10, 0);
		TTreqrace.setLocation(10, 0);
		
		TTset.setLocation(10, 0);
		*/
		
		if (item.getReqlvl() != 0) {
			incrementLines();
			TTreqlvl.setText("Requires Level " + item.getReqlvl());
			TTreqlvl.setLocation(10, toolTipHeight);
		}
		
		if (!item.getDescription().isEmpty()) {
			ArrayList<String> descLines = stringToStringArray(item.getDescription(), 42);
			
			for (int i = 0; i < descLines.size(); i++) {
				TTdesc.add(i, new JLabel());
				incrementLines();
				if (descLines.size() == 1 && i == 0) {
					TTdesc.get(i).setText('"' + descLines.get(i) + '"');
				} else if(descLines.size() != 1 && i == 0) {
					TTdesc.get(i).setText('"' + descLines.get(i));
				} else if (descLines.size() == i+1) {
					TTdesc.get(i).setText(descLines.get(i) + '"');
				} else {
					TTdesc.get(i).setText(descLines.get(i));
				}
				TTdesc.get(i).setForeground(Color.YELLOW);
				TTdesc.get(i).setLocation(10, toolTipHeight);
				add(TTdesc.get(i));
			}
		}
		
		//incrementLines();
		//TTsellprice.setLocation(10, toolTipHeight);
		
		resize(toolTipWidth, ((numberOfLines+1)*lineHeight));
	}
	
	private static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
	
	private ArrayList<String> stringToStringArray(String s, int length) {
		ArrayList<String> stringArray = new ArrayList<String>();
		
		String tempString = s;
		while (tempString.length() > length) {
			String findLastWord = tempString.substring(0, length);
			int pos = 1;
			while (!findLastWord.endsWith(" ")) {
				if (pos == length) {
					pos = 3;
					findLastWord = tempString.substring(0, length-pos) + " - ";
					System.out.println("That is a really big word: " + findLastWord);
					continue;
				}
				findLastWord = findLastWord.substring(0, length-pos);
				pos++;
			}
			stringArray.add(findLastWord);
			tempString = tempString.substring(findLastWord.length());
		}
		
		stringArray.add(tempString);
		
		return stringArray;
	}
	
	public int getToolTipHeight() {
		return toolTipHeight;
	}
	
	public String getIconPath() {
		return iconPath;
	}
	
	
}
