/**
 * 
 */
package PYDevTools.view;

import javax.swing.*;

import PYDevTools.Spring.SpringUtilities;
import PYDevTools.utilities.SceneLoader;

import java.awt.*;
import java.io.IOException;

/**
 * @author alfeey44
 *
 */
public class WeaponPanel extends JPanel{
	
	// Panels
	private JSplitPane mainPane;
	private JScrollPane leftPane, rightPane;
	private JPanel leftPanel, rightPanel;
	private String[] labels = { "Name: ", "Description: ", "Quality: ", "Equip: ", "Type: ",
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
			boolean isTextField = true;
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			leftPanel.add(l);
			// Non Text Fields
			switch(i) {
				case 2:
					JComboBox<String> quality = new JComboBox<String>(qualities);
					quality.setSelectedIndex(0);
					leftPanel.add(quality);
					isTextField = false;
					break;
				case 3:
					JComboBox<String> equip = new JComboBox<String>(equips);
					equip.setSelectedIndex(0);
					leftPanel.add(equip);
					isTextField = false;
					break;
				case 4:
					JComboBox<String> type = new JComboBox<String>(types);
					type.setSelectedIndex(0);
					leftPanel.add(type);
					isTextField = false;
					break;
				case 5:
					JComboBox<String> sheath = new JComboBox<String>(sheaths);
					sheath.setSelectedIndex(0);
					leftPanel.add(sheath);
					isTextField = false;
					break;
				case 6:
					JComboBox<String> bind = new JComboBox<String>(binds);
					bind.setSelectedIndex(0);
					leftPanel.add(bind);
					isTextField = false;
					break;
				case 13:
					JComboBox<String> role = new JComboBox<String>(roles);
					role.setSelectedIndex(0);
					leftPanel.add(role);
					isTextField = false;
					break;
				case 14:
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
		rightPanel = new JPanel(new SpringLayout());
		rightPane = new JScrollPane(rightPanel);
		
		// Add Panels to Weapon Panel
		mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
		add(mainPane);
		SpringUtilities.makeCompactGrid(this, 1, 1, 6, 6, 6, 6);
	}
}
