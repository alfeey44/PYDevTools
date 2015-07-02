package PYDevTools.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import PYDevTools.Spring.SpringUtilities;
import PYDevTools.utilities.AESEncrypt;

@SuppressWarnings("serial")
public class ServerConfigPanel extends JPanel implements ActionListener, KeyListener {
	
	private static ServerConfigPanel instance = null;
	private static JFrame serverConfigFrame;
	private JPanel panel1, panel2, multPanel;
	private SpringLayout mainLayout;
	// TODO : create server multipliers
	private String armorStatMultiplier, weaponStatMultiplier;
	private String headStatMultiplier, neckStatMultiplier,
			shoulderStatMultiplier, cloakStatMultiplier, chestStatMultiplier,
			shirtStatMultiplier, tabardStatMultiplier, bracerStatMultiplier,
			glovesStatMultiplier, beltStatMultiplier, legsStatMultiplier,
			bootsStatMultiplier, ringStatMultiplier, trinketStatMultiplier,
			oneHandStatMultiplier, twoHandStatMultiplier,
			rangedStatMultiplier, bagStatMultiplier;
	private JTextField armorStatMultField, weaponStatMultField,
			headStatMultField, neckStatMultField, shoulderStatMultField,
			cloakStatMultField, chestStatMultField, shirtStatMultField,
			tabardStatMultField, bracerStatMultField, glovesStatMultField,
			beltStatMultField, legsStatMultField, bootsStatMultField,
			ringStatMultField, trinketStatMultField, oneHandStatMultField,
			twoHandStatMultField, rangedStatMultField, bagStatMultField;
	private JLabel armorStatMultLabel, weaponStatMultLabel, headStatMultLabel,
			neckStatMultLabel, shoulderStatMultLabel, cloakStatMultLabel,
			chestStatMultLabel, shirtStatMultLabel, tabardStatMultLabel,
			bracerStatMultLabel, glovesStatMultLabel, beltStatMultLabel,
			legsStatMultLabel, bootsStatMultLabel, ringStatMultLabel,
			trinketStatMultLabel, oneHandStatMultLabel, twoHandStatMultLabel,
			rangedStatMultLabel, bagStatMultLabel;
	private JButton save;
	private Font defaultFont, saveFont;
	private JLabel saveSuccessLabel, saveFailLabel;
	
	protected ServerConfigPanel() {
		super();
		
		defaultFont = new Font("Arial", Font.PLAIN, 20);
		saveFont = new Font("Arial", Font.ITALIC, 28);
		
		SpringLayout mainLayout = new SpringLayout();
		setLayout(mainLayout);
		
		SpringLayout layout1 = new SpringLayout();
		SpringLayout layout2 = new SpringLayout();
		SpringLayout multLayout = new SpringLayout();
		
		multPanel = new JPanel(multLayout);
		SpringLayout.Constraints multPanelCons = mainLayout.getConstraints(multPanel);
		multPanelCons.setX(Spring.constant(10));
		multPanelCons.setY(Spring.constant(10));
		multPanel.setPreferredSize(new Dimension(470, 440));
		
		TitledBorder multBorder;
		multBorder = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Stat Multipliers");
		multBorder.setTitlePosition(TitledBorder.ABOVE_TOP);
		multPanel.setBorder(multBorder);
		
		panel1 = new JPanel(layout1);
		SpringLayout.Constraints panel1Cons = multLayout.getConstraints(panel1);
		panel1.setPreferredSize(new Dimension(200, 400));
		
		panel2 = new JPanel(layout2);
		SpringLayout.Constraints panel2Cons = multLayout.getConstraints(panel2);
		panel2Cons.setX(Spring.constant(230));
		panel2.setPreferredSize(new Dimension(200, 400));
		
		// Label
		armorStatMultLabel = new JLabel("Armor: ");
		armorStatMultLabel.setFont(defaultFont);
		panel1.add(armorStatMultLabel);
		// Text Field
		armorStatMultField = new JTextField(5);
		armorStatMultField.addKeyListener(this);
		armorStatMultField.setFont(defaultFont);
		panel1.add(armorStatMultField);
		
		// Label
		weaponStatMultLabel = new JLabel("Weapons: ");
		weaponStatMultLabel.setFont(defaultFont);
		panel1.add(weaponStatMultLabel);
		// Text Field
		weaponStatMultField = new JTextField(5);
		weaponStatMultField.addKeyListener(this);
		weaponStatMultField.setFont(defaultFont);
		panel1.add(weaponStatMultField);

		// Label
		headStatMultLabel = new JLabel("Head: ");
		headStatMultLabel.setFont(defaultFont);
		panel1.add(headStatMultLabel);
		// Text Field
		headStatMultField = new JTextField(5);
		headStatMultField.addKeyListener(this);
		headStatMultField.setFont(defaultFont);
		panel1.add(headStatMultField);

		// Label
		neckStatMultLabel = new JLabel("Neck: ");
		neckStatMultLabel.setFont(defaultFont);
		panel1.add(neckStatMultLabel);
		// Text Field
		neckStatMultField = new JTextField(5);
		neckStatMultField.addKeyListener(this);
		neckStatMultField.setFont(defaultFont);
		panel1.add(neckStatMultField);

		// Label
		shoulderStatMultLabel = new JLabel("Shoulders: ");
		shoulderStatMultLabel.setFont(defaultFont);
		panel1.add(shoulderStatMultLabel);
		// Text Field
		shoulderStatMultField = new JTextField(5);
		shoulderStatMultField.addKeyListener(this);
		shoulderStatMultField.setFont(defaultFont);
		panel1.add(shoulderStatMultField);

		// Label
		cloakStatMultLabel = new JLabel("Cloak: ");
		cloakStatMultLabel.setFont(defaultFont);
		panel1.add(cloakStatMultLabel);
		// Text Field
		cloakStatMultField = new JTextField(5);
		cloakStatMultField.addKeyListener(this);
		cloakStatMultField.setFont(defaultFont);
		panel1.add(cloakStatMultField);

		// Label
		chestStatMultLabel = new JLabel("Chest: ");
		chestStatMultLabel.setFont(defaultFont);
		panel1.add(chestStatMultLabel);
		// Text Field
		chestStatMultField = new JTextField(5);
		chestStatMultField.addKeyListener(this);
		chestStatMultField.setFont(defaultFont);
		panel1.add(chestStatMultField);

		// Label
		shirtStatMultLabel = new JLabel("Shirt: ");
		shirtStatMultLabel.setFont(defaultFont);
		panel1.add(shirtStatMultLabel);
		// Text Field
		shirtStatMultField = new JTextField(5);
		shirtStatMultField.addKeyListener(this);
		shirtStatMultField.setFont(defaultFont);
		panel1.add(shirtStatMultField);

		// Label
		tabardStatMultLabel = new JLabel("Tabard: ");
		tabardStatMultLabel.setFont(defaultFont);
		panel1.add(tabardStatMultLabel);
		// Text Field
		tabardStatMultField = new JTextField(5);
		tabardStatMultField.addKeyListener(this);
		tabardStatMultField.setFont(defaultFont);
		panel1.add(tabardStatMultField);

		// Label
		bracerStatMultLabel = new JLabel("Bracers: ");
		bracerStatMultLabel.setFont(defaultFont);
		panel1.add(bracerStatMultLabel);
		// Text Field
		bracerStatMultField = new JTextField(5);
		bracerStatMultField.addKeyListener(this);
		bracerStatMultField.setFont(defaultFont);
		panel1.add(bracerStatMultField);

		// Label
		glovesStatMultLabel = new JLabel("Gloves: ");
		glovesStatMultLabel.setFont(defaultFont);
		panel2.add(glovesStatMultLabel);
		// Text Field
		glovesStatMultField = new JTextField(5);
		glovesStatMultField.addKeyListener(this);
		glovesStatMultField.setFont(defaultFont);
		panel2.add(glovesStatMultField);

		// Label
		beltStatMultLabel = new JLabel("Belt: ");
		beltStatMultLabel.setFont(defaultFont);
		panel2.add(beltStatMultLabel);
		// Text Field
		beltStatMultField = new JTextField(5);
		beltStatMultField.addKeyListener(this);
		beltStatMultField.setFont(defaultFont);
		panel2.add(beltStatMultField);

		// Label
		legsStatMultLabel = new JLabel("Legs: ");
		legsStatMultLabel.setFont(defaultFont);
		panel2.add(legsStatMultLabel);
		// Text Field
		legsStatMultField = new JTextField(5);
		legsStatMultField.addKeyListener(this);
		legsStatMultField.setFont(defaultFont);
		panel2.add(legsStatMultField);

		// Label
		bootsStatMultLabel = new JLabel("Boots: ");
		bootsStatMultLabel.setFont(defaultFont);
		panel2.add(bootsStatMultLabel);
		// Text Field
		bootsStatMultField = new JTextField(5);
		bootsStatMultField.addKeyListener(this);
		bootsStatMultField.setFont(defaultFont);
		panel2.add(bootsStatMultField);

		// Label
		ringStatMultLabel = new JLabel("Rings: ");
		ringStatMultLabel.setFont(defaultFont);
		panel2.add(ringStatMultLabel);
		// Text Field
		ringStatMultField = new JTextField(5);
		ringStatMultField.addKeyListener(this);
		ringStatMultField.setFont(defaultFont);
		panel2.add(ringStatMultField);

		// Label
		trinketStatMultLabel = new JLabel("Trinkets: ");
		trinketStatMultLabel.setFont(defaultFont);
		panel2.add(trinketStatMultLabel);
		// Text Field
		trinketStatMultField = new JTextField(5);
		trinketStatMultField.addKeyListener(this);
		trinketStatMultField.setFont(defaultFont);
		panel2.add(trinketStatMultField);

		// Label
		oneHandStatMultLabel = new JLabel("One Hand: ");
		oneHandStatMultLabel.setFont(defaultFont);
		panel2.add(oneHandStatMultLabel);
		// Text Field
		oneHandStatMultField = new JTextField(5);
		oneHandStatMultField.addKeyListener(this);
		oneHandStatMultField.setFont(defaultFont);
		panel2.add(oneHandStatMultField);

		// Label
		twoHandStatMultLabel = new JLabel("Two Hand: ");
		twoHandStatMultLabel.setFont(defaultFont);
		panel2.add(twoHandStatMultLabel);
		// Text Field
		twoHandStatMultField = new JTextField(5);
		twoHandStatMultField.addKeyListener(this);
		twoHandStatMultField.setFont(defaultFont);
		panel2.add(twoHandStatMultField);

		// Label
		rangedStatMultLabel = new JLabel("Ranged: ");
		rangedStatMultLabel.setFont(defaultFont);
		panel2.add(rangedStatMultLabel);
		// Text Field
		rangedStatMultField = new JTextField(5);
		rangedStatMultField.addKeyListener(this);
		rangedStatMultField.setFont(defaultFont);
		panel2.add(rangedStatMultField);

		// Label
		bagStatMultLabel = new JLabel("Bags: ");
		bagStatMultLabel.setFont(defaultFont);
		panel2.add(bagStatMultLabel);
		// Text Field
		bagStatMultField = new JTextField(5);
		bagStatMultField.addKeyListener(this);
		bagStatMultField.setFont(defaultFont);
		panel2.add(bagStatMultField);
		
		//TODO make stat multipliers

		// Button
		save = new JButton("Save");
		save.setFont(defaultFont);
		save.setActionCommand("Save");
		save.addActionListener(this);
		SpringLayout.Constraints saveCons = mainLayout.getConstraints(save);
		saveCons.setX(Spring.constant(1080));
		saveCons.setY(Spring.constant(600));
		add(save);
		
		saveSuccessLabel = new JLabel("Settings Saved Successfully!");
		saveSuccessLabel.setFont(saveFont);
		SpringLayout.Constraints saveSuccessLabelCons = mainLayout.getConstraints(saveSuccessLabel);
		saveSuccessLabelCons.setX(Spring.constant(1060));
		saveSuccessLabelCons.setY(Spring.constant(560));
		saveSuccessLabel.setVisible(false);
		add(saveSuccessLabel);
		
		saveFailLabel = new JLabel("Settings Saved Failed!");
		saveFailLabel.setFont(saveFont);
		SpringLayout.Constraints saveFailLabelCons = mainLayout.getConstraints(saveFailLabel);
		saveSuccessLabelCons.setX(Spring.constant(1060));
		saveSuccessLabelCons.setY(Spring.constant(560));
		saveFailLabel.setVisible(false);
		add(saveFailLabel);
		
		SpringUtilities.makeCompactGrid(panel1, 10, 2, 10, 10, 5, 10);
		SpringUtilities.makeCompactGrid(panel2, 10, 2, 10, 10, 5, 10);
		
		multPanel.add(panel1);
		multPanel.add(panel2);
		
		add(multPanel);
		
		// Initialize Config Info
		readServerConfigFile();
		
		setStatMultipliers();
	}
	
	public static ServerConfigPanel getInstance() {
		if (instance == null)
			instance = new ServerConfigPanel();
		
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Save")) {
			setStatMultipliers();
			writeServerConfigFile();
			serverConfigFrame.dispatchEvent(new WindowEvent(serverConfigFrame, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	private void setStatMultipliersToDefault() {
		armorStatMultiplier = "1";
		armorStatMultField.setText("1");
		
		weaponStatMultiplier = "1";
		weaponStatMultField.setText("1");
		
		headStatMultiplier = "1";
		headStatMultField.setText("1");
		
		neckStatMultiplier = "0.8";
		neckStatMultField.setText("0.8");
		
		shoulderStatMultiplier = "1.05";
		shoulderStatMultField.setText("1.05");
		
		cloakStatMultiplier = "0.95";
		cloakStatMultField.setText("0.95");
		
		chestStatMultiplier = "1.15";
		chestStatMultField.setText("1.15");
		
		shirtStatMultiplier = "0.45";
		shirtStatMultField.setText("0.45");
		
		tabardStatMultiplier = "0.6";
		tabardStatMultField.setText("0.6");
		
		bracerStatMultiplier = "0.75";
		bracerStatMultField.setText("0.75");
		
		glovesStatMultiplier = "0.82";
		glovesStatMultField.setText("0.82");
		
		beltStatMultiplier = "0.75";
		beltStatMultField.setText("0.75");
		
		legsStatMultiplier = "1.15";
		legsStatMultField.setText("1.15");
		
		bootsStatMultiplier = "1";
		bootsStatMultField.setText("1");
		
		ringStatMultiplier = "0.7";
		ringStatMultField.setText("0.7");
		
		trinketStatMultiplier = "0.73";
		trinketStatMultField.setText("0.73");
		
		oneHandStatMultiplier = "0.85";
		oneHandStatMultField.setText("0.85");
		
		twoHandStatMultiplier = "1.55";
		twoHandStatMultField.setText("1.55");
		
		rangedStatMultiplier = "1.15";
		rangedStatMultField.setText("1.15");
		
		bagStatMultiplier = "1";
		bagStatMultField.setText("1");
	}
	
	private void setStatMultipliers() {
		if (!armorStatMultField.getText().isEmpty()) {
			armorStatMultiplier = armorStatMultField.getText();
		} else {
			armorStatMultiplier = "1";
			armorStatMultField.setText("1");
		}
		if (!weaponStatMultField.getText().isEmpty()) {
			weaponStatMultiplier = weaponStatMultField.getText();
		} else {
			weaponStatMultiplier = "1";
			weaponStatMultField.setText("1");
		}
		if (!headStatMultField.getText().isEmpty()) {
			headStatMultiplier = headStatMultField.getText();
		} else {
			headStatMultiplier = "1";
			headStatMultField.setText("1");
		}
		if (!neckStatMultField.getText().isEmpty()) {
			neckStatMultiplier = neckStatMultField.getText();
		} else {
			neckStatMultiplier = "1";
			neckStatMultField.setText("1");
		}
		if (!shoulderStatMultField.getText().isEmpty()) {
			shoulderStatMultiplier = shoulderStatMultField.getText();
		} else {
			shoulderStatMultiplier = "1";
			shoulderStatMultField.setText("1");
		}
		if (!cloakStatMultField.getText().isEmpty()) {
			cloakStatMultiplier = cloakStatMultField.getText();
		} else {
			cloakStatMultiplier = "1";
			cloakStatMultField.setText("1");
		}
		if (!chestStatMultField.getText().isEmpty()) {
			chestStatMultiplier = chestStatMultField.getText();
		} else {
			chestStatMultiplier = "1";
			chestStatMultField.setText("1");
		}
		if (!shirtStatMultField.getText().isEmpty()) {
			shirtStatMultiplier = shirtStatMultField.getText();
		} else {
			shirtStatMultiplier = "1";
			shirtStatMultField.setText("1");
		}
		if (!tabardStatMultField.getText().isEmpty()) {
			tabardStatMultiplier = tabardStatMultField.getText();
		} else {
			tabardStatMultiplier = "1";
			tabardStatMultField.setText("1");
		}
		if (!bracerStatMultField.getText().isEmpty()) {
			bracerStatMultiplier = bracerStatMultField.getText();
		} else {
			bracerStatMultiplier = "1";
			bracerStatMultField.setText("1");
		}
		if (!glovesStatMultField.getText().isEmpty()) {
			glovesStatMultiplier = glovesStatMultField.getText();
		} else {
			glovesStatMultiplier = "1";
			glovesStatMultField.setText("1");
		}
		if (!beltStatMultField.getText().isEmpty()) {
			beltStatMultiplier = beltStatMultField.getText();
		} else {
			beltStatMultiplier = "1";
			beltStatMultField.setText("1");
		}
		if (!legsStatMultField.getText().isEmpty()) {
			legsStatMultiplier = legsStatMultField.getText();
		} else {
			legsStatMultiplier = "1";
			legsStatMultField.setText("1");
		}
		if (!bootsStatMultField.getText().isEmpty()) {
			bootsStatMultiplier = bootsStatMultField.getText();
		} else {
			bootsStatMultiplier = "1";
			bootsStatMultField.setText("1");
		}
		if (!ringStatMultField.getText().isEmpty()) {
			ringStatMultiplier = ringStatMultField.getText();
		} else {
			ringStatMultiplier = "1";
			ringStatMultField.setText("1");
		}
		if (!trinketStatMultField.getText().isEmpty()) {
			trinketStatMultiplier = trinketStatMultField.getText();
		} else {
			trinketStatMultiplier = "1";
			trinketStatMultField.setText("1");
		}
		if (!oneHandStatMultField.getText().isEmpty()) {
			oneHandStatMultiplier = oneHandStatMultField.getText();
		} else {
			oneHandStatMultiplier = "0.85";
			oneHandStatMultField.setText("0.85");
		}
		if (!twoHandStatMultField.getText().isEmpty()) {
			twoHandStatMultiplier = twoHandStatMultField.getText();
		} else {
			twoHandStatMultiplier = "1.55";
			twoHandStatMultField.setText("1.55");
		}
		if (!rangedStatMultField.getText().isEmpty()) {
			rangedStatMultiplier = rangedStatMultField.getText();
		} else {
			rangedStatMultiplier = "1.15";
			rangedStatMultField.setText("1.15");
		}
		if (!bagStatMultField.getText().isEmpty()) {
			bagStatMultiplier = bagStatMultField.getText();
		} else {
			bagStatMultiplier = "1";
			bagStatMultField.setText("1");
		}
	}
	
	private void writeServerConfigFile() {
		Writer writer = null;
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("pyServerConfig.conf"), "utf-8"));
			writer.write("armor: " + armorStatMultiplier + "\n");
			writer.write("weapons: " + weaponStatMultiplier + "\n");
			writer.write("head: " + headStatMultiplier + "\n");
			writer.write("neck: " + neckStatMultiplier + "\n");
			writer.write("shoulders: " + shoulderStatMultiplier + "\n");
			writer.write("cloak: " + cloakStatMultiplier + "\n");
			writer.write("chest: " + chestStatMultiplier + "\n");
			writer.write("shirt: " + shirtStatMultiplier + "\n");
			writer.write("tabard: " + tabardStatMultiplier + "\n");
			writer.write("bracers: " + bracerStatMultiplier + "\n");
			writer.write("gloves: " + glovesStatMultiplier + "\n");
			writer.write("belt: " + beltStatMultiplier + "\n");
			writer.write("legs: " + legsStatMultiplier + "\n");
			writer.write("boots: " + bootsStatMultiplier + "\n");
			writer.write("rings: " + ringStatMultiplier + "\n");
			writer.write("trinkets: " + trinketStatMultiplier + "\n");
			writer.write("one hand: " + oneHandStatMultiplier + "\n");
			writer.write("two hand: " + twoHandStatMultiplier + "\n");
			writer.write("ranged: " + rangedStatMultiplier + "\n");
			writer.write("bags: " + bagStatMultiplier);
		} catch (IOException e) {
			saveFailLabel.setForeground(Color.red);
			saveSuccessLabel.setVisible(false);
			saveFailLabel.setVisible(true);
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				saveSuccessLabel.setForeground(Color.green);
				saveFailLabel.setVisible(false);
				saveSuccessLabel.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void readServerConfigFile() {
		try (Scanner scanner = new Scanner(Paths.get("pyServerConfig.conf"), StandardCharsets.UTF_8.name())) {
			// First Line DB Info
			scanner.useDelimiter("\n");
			while (scanner.hasNext())
				processServerConfigs(scanner.next());
		} catch (NoSuchFileException nsfe) {
			//nsfe.printStackTrace();
			System.out.println("No Server Configuration File... Creating one...");
			setStatMultipliersToDefault();
			writeServerConfigFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void processServerConfigs(String aLine) {
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter(": ");
		if (scanner.hasNext()) {
			String field = scanner.next();
			if (field.contains("armor")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					armorStatMultiplier = field;
					armorStatMultField.setText(field);
				}
			} else if (field.contains("weapon")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					weaponStatMultiplier = field;
					weaponStatMultField.setText(field);
				}
			} else if (field.contains("head")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					headStatMultiplier = field;
					headStatMultField.setText(field);
				}
			} else if (field.contains("neck")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					neckStatMultiplier = field;
					neckStatMultField.setText(field);
				}
			} else if (field.contains("shoulder")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					shoulderStatMultiplier = field;
					shoulderStatMultField.setText(field);
				}
			} else if (field.contains("cloak")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					cloakStatMultiplier = field;
					cloakStatMultField.setText(field);
				}
			} else if (field.contains("chest")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					chestStatMultiplier = field;
					chestStatMultField.setText(field);
				}
			} else if (field.contains("shirt")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					shirtStatMultiplier = field;
					shirtStatMultField.setText(field);
				}
			} else if (field.contains("tabard")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					tabardStatMultiplier = field;
					tabardStatMultField.setText(field);
				}
			} else if (field.contains("bracer")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					bracerStatMultiplier = field;
					bracerStatMultField.setText(field);
				}
			} else if (field.contains("gloves")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					glovesStatMultiplier = field;
					glovesStatMultField.setText(field);
				}
			} else if (field.contains("belt")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					beltStatMultiplier = field;
					beltStatMultField.setText(field);
				}
			} else if (field.contains("legs")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					legsStatMultiplier = field;
					legsStatMultField.setText(field);
				}
			} else if (field.contains("boots")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					bootsStatMultiplier = field;
					bootsStatMultField.setText(field);
				}
			} else if (field.contains("ring")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					ringStatMultiplier = field;
					ringStatMultField.setText(field);
				}
			} else if (field.contains("trinket")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					trinketStatMultiplier = field;
					trinketStatMultField.setText(field);
				}
			} else if (field.contains("one hand")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					oneHandStatMultiplier = field;
					oneHandStatMultField.setText(field);
				}
			} else if (field.contains("two hand")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					twoHandStatMultiplier = field;
					twoHandStatMultField.setText(field);
				}
			} else if (field.contains("ranged")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					rangedStatMultiplier = field;
					rangedStatMultField.setText(field);
				}
			} else if (field.contains("bags")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					bagStatMultiplier = field;
					bagStatMultField.setText(field);
				}
			} else {
				System.err.println("Unknown field in database configuration file: " + field);
			}
		}
	}
	
	public void setServerConfigFrame(JFrame serverConfigFrame) {
		this.serverConfigFrame = serverConfigFrame;
	}

	public String getArmorStatMultiplier() {
		return armorStatMultiplier;
	}

	public void setArmorStatMultiplier(String armorStatMultiplier) {
		this.armorStatMultiplier = armorStatMultiplier;
	}

	public String getWeaponStatMultiplier() {
		return weaponStatMultiplier;
	}

	public void setWeaponStatMultiplier(String weaponStatMultiplier) {
		this.weaponStatMultiplier = weaponStatMultiplier;
	}

	public String getHeadStatMultiplier() {
		return headStatMultiplier;
	}

	public void setHeadStatMultiplier(String headStatMultiplier) {
		this.headStatMultiplier = headStatMultiplier;
	}

	public String getNeckStatMultiplier() {
		return neckStatMultiplier;
	}

	public void setNeckStatMultiplier(String neckStatMultiplier) {
		this.neckStatMultiplier = neckStatMultiplier;
	}

	public String getShoulderStatMultiplier() {
		return shoulderStatMultiplier;
	}

	public void setShoulderStatMultiplier(String shoulderStatMultiplier) {
		this.shoulderStatMultiplier = shoulderStatMultiplier;
	}

	public String getCloakStatMultiplier() {
		return cloakStatMultiplier;
	}

	public void setCloakStatMultiplier(String cloakStatMultiplier) {
		this.cloakStatMultiplier = cloakStatMultiplier;
	}

	public String getChestStatMultiplier() {
		return chestStatMultiplier;
	}

	public void setChestStatMultiplier(String chestStatMultiplier) {
		this.chestStatMultiplier = chestStatMultiplier;
	}

	public String getShirtStatMultiplier() {
		return shirtStatMultiplier;
	}

	public void setShirtStatMultiplier(String shirtStatMultiplier) {
		this.shirtStatMultiplier = shirtStatMultiplier;
	}

	public String getTabardStatMultiplier() {
		return tabardStatMultiplier;
	}

	public void setTabardStatMultiplier(String tabardStatMultiplier) {
		this.tabardStatMultiplier = tabardStatMultiplier;
	}

	public String getBracerStatMultiplier() {
		return bracerStatMultiplier;
	}

	public void setBracerStatMultiplier(String bracerStatMultiplier) {
		this.bracerStatMultiplier = bracerStatMultiplier;
	}

	public String getGlovesStatMultiplier() {
		return glovesStatMultiplier;
	}

	public void setGlovesStatMultiplier(String glovesStatMultiplier) {
		this.glovesStatMultiplier = glovesStatMultiplier;
	}

	public String getBeltStatMultiplier() {
		return beltStatMultiplier;
	}

	public void setBeltStatMultiplier(String beltStatMultiplier) {
		this.beltStatMultiplier = beltStatMultiplier;
	}

	public String getLegsStatMultiplier() {
		return legsStatMultiplier;
	}

	public void setLegsStatMultiplier(String legsStatMultiplier) {
		this.legsStatMultiplier = legsStatMultiplier;
	}

	public String getBootsStatMultiplier() {
		return bootsStatMultiplier;
	}

	public void setBootsStatMultiplier(String bootsStatMultiplier) {
		this.bootsStatMultiplier = bootsStatMultiplier;
	}

	public String getRingStatMultiplier() {
		return ringStatMultiplier;
	}

	public void setRingStatMultiplier(String ringStatMultiplier) {
		this.ringStatMultiplier = ringStatMultiplier;
	}

	public String getTrinketStatMultiplier() {
		return trinketStatMultiplier;
	}

	public void setTrinketStatMultiplier(String trinketStatMultiplier) {
		this.trinketStatMultiplier = trinketStatMultiplier;
	}

	public String getOneHandStatMultiplier() {
		return oneHandStatMultiplier;
	}

	public void setOneHandStatMultiplier(String oneHandStatMultiplier) {
		this.oneHandStatMultiplier = oneHandStatMultiplier;
	}

	public String getTwoHandStatMultiplier() {
		return twoHandStatMultiplier;
	}

	public void setTwoHandStatMultiplier(String twoHandStatMultiplier) {
		this.twoHandStatMultiplier = twoHandStatMultiplier;
	}

	public String getRangedStatMultiplier() {
		return rangedStatMultiplier;
	}

	public void setRangedStatMultiplier(String rangedStatMultiplier) {
		this.rangedStatMultiplier = rangedStatMultiplier;
	}

	public String getBagStatMultiplier() {
		return bagStatMultiplier;
	}

	public void setBagStatMultiplier(String bagStatMultiplier) {
		this.bagStatMultiplier = bagStatMultiplier;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			save.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
}

