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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import PYDevTools.Spring.SpringUtilities;
import PYDevTools.utilities.AESEncrypt;
import PYDevTools.utilities.MySQLAccess;

@SuppressWarnings("serial")
public class SettingsPanel extends JPanel implements ActionListener, KeyListener {
	
	private static SettingsPanel instance;
	private static JFrame settingsFrame;
	private JPanel dbInfoPanel;
	private JTextField dbUser, dbName, dbHost, nextEntryIDField;
	private JPasswordField dbPass;
	private JLabel dbUserLabel, dbPassLabel, dbNameLabel, dbHostLabel, saveSuccessLabel,
				   saveFailLabel, nextEntryIDLabel;
	private JButton save;
	private Font defaultFont, saveFont;
	private String dbUserName = ""; 
	private String dbPassword = ""; 
	private String dbWorldName = "";
	private String dbHostName = "";
	
	private String nextEntryID = "";
	
	protected SettingsPanel() {
		super();
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		defaultFont = new Font("Arial", Font.PLAIN, 20);
		saveFont = new Font("Arial", Font.ITALIC, 28);
		
		dbInfoPanel = new JPanel(new SpringLayout());
		SpringLayout.Constraints dbInfoPanelCons = layout.getConstraints(dbInfoPanel);
		dbInfoPanelCons.setX(Spring.constant(10));
		dbInfoPanelCons.setY(Spring.constant(10));
		dbInfoPanel.setPreferredSize(new Dimension(300, 200));
		
		// DB Fields
		dbHostLabel = new JLabel("DB Host: ");
		dbHostLabel.setFont(defaultFont);
		SpringLayout.Constraints dbHostLabelCons = layout.getConstraints(dbHostLabel);
		dbInfoPanel.add(dbHostLabel);
		
		dbHost = new JTextField(15);
		dbHost.addKeyListener(this);
		dbHost.setFont(defaultFont);
		SpringLayout.Constraints dbHostCons = layout.getConstraints(dbHost);
		dbInfoPanel.add(dbHost);
		
		dbUserLabel = new JLabel("DB Username: ");
		dbUserLabel.setFont(defaultFont);
		SpringLayout.Constraints dbUserLabelCons = layout.getConstraints(dbUserLabel);
		dbInfoPanel.add(dbUserLabel);
		
		dbUser = new JTextField(15);
		dbUser.addKeyListener(this);
		dbUser.setFont(defaultFont);
		SpringLayout.Constraints dbUserCons = layout.getConstraints(dbUser);
		dbInfoPanel.add(dbUser);
		
		dbPassLabel = new JLabel("DB Password: ");
		dbPassLabel.setFont(defaultFont);
		SpringLayout.Constraints dbPassLabelCons = layout.getConstraints(dbPassLabel);
		dbInfoPanel.add(dbPassLabel);
		
		dbPass = new JPasswordField(15);
		dbPass.addKeyListener(this);
		dbPass.setFont(defaultFont);
		SpringLayout.Constraints dbPassCons = layout.getConstraints(dbPass);
		dbInfoPanel.add(dbPass);
		
		dbNameLabel = new JLabel("World DB Name: ");
		dbNameLabel.setFont(defaultFont);
		SpringLayout.Constraints dbNameLabelCons = layout.getConstraints(dbNameLabel);
		dbInfoPanel.add(dbNameLabel);
		
		dbName = new JTextField(15);
		dbName.addKeyListener(this);
		dbName.setFont(defaultFont);
		SpringLayout.Constraints dbNameCons = layout.getConstraints(dbName);
		dbInfoPanel.add(dbName);
		
		nextEntryIDLabel = new JLabel("Usable Entry ID: ");
		nextEntryIDLabel.setFont(defaultFont);
		SpringLayout.Constraints nextEntryIDLabelCons = layout.getConstraints(nextEntryIDLabel);
		dbInfoPanel.add(nextEntryIDLabel);
		
		nextEntryIDField = new JTextField(15);
		nextEntryIDField.addKeyListener(this);
		nextEntryIDField.setFont(defaultFont);
		SpringLayout.Constraints nextEntryIDFieldCons = layout.getConstraints(nextEntryIDField);
		dbInfoPanel.add(nextEntryIDField);
		
		SpringUtilities.makeCompactGrid(dbInfoPanel, 5, 2, 10, 10, 5, 10);
		
		saveSuccessLabel = new JLabel("Settings Saved Successfully!");
		saveSuccessLabel.setFont(saveFont);
		SpringLayout.Constraints saveSuccessLabelCons = layout.getConstraints(saveSuccessLabel);
		saveSuccessLabel.setVisible(false);
		add(saveSuccessLabel);
		
		saveFailLabel = new JLabel("Settings Saved Failed!");
		saveFailLabel.setFont(saveFont);
		SpringLayout.Constraints saveFailLabelCons = layout.getConstraints(saveFailLabel);
		saveFailLabel.setVisible(false);
		add(saveFailLabel);
		
		// Button
		save = new JButton("Save");
		save.setFont(defaultFont);
		save.setActionCommand("Save");
		save.addActionListener(this);
		SpringLayout.Constraints saveCons = layout.getConstraints(save);
		saveCons.setX(Spring.constant(495));
		saveCons.setY(Spring.constant(320));
		add(save);
		
		add(dbInfoPanel);
		
		// Initialize Config Info
		readDBConfigFile();
	}
	
	public static SettingsPanel getInstance() {
		if (instance == null)
			instance = new SettingsPanel();
		
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Save")) {
			setDBUserName(getDBUserText());
			setDBPassword(getDBPassText());
			setDBWorldName(getDBNameText());
			setDBHostName(getDBHostText());
			if (nextEntryIDField.getText().isEmpty()) {
				setNextEntryID("100000");
			} else {
				setNextEntryID(nextEntryIDField.getText());
			}
			writeDBConfigFile();
			settingsFrame.dispatchEvent(new WindowEvent(settingsFrame, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	private void writeDBConfigFile() {
		Writer writer = null;
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("pyDBConfig.conf"), "utf-8"));
			writer.write("hostname: " + dbHostName + "\n");
			writer.write("username: " + dbUserName + "\n");
			String pass = "";
			try {
				pass = AESEncrypt.encrypt(dbPassword);
			} catch (Exception e) {
				e.printStackTrace();
			}
			writer.write("password: " + pass + "\n");
			writer.write("world name: " + dbWorldName + "\n");
			writer.write("next entry: " + nextEntryID);
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
	
	private void readDBConfigFile() {
		try (Scanner scanner = new Scanner(Paths.get("pyDBConfig.conf"), StandardCharsets.UTF_8.name())) {
			// First Line DB Info
			scanner.useDelimiter("\n");
			while (scanner.hasNext())
				processDBConfigs(scanner.next());
		} catch (NoSuchFileException nsfe) {
			//nsfe.printStackTrace();
			System.out.println("No DB Configuration File... Creating one...");
			writeDBConfigFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void processDBConfigs(String aLine) {
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter(": ");
		if (scanner.hasNext()) {
			String field = scanner.next();
			if (field.contains("hostname")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					setDBHostName(field);
				}
			} else if (field.contains("username")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					setDBUserName(field);
				}
			} else if (field.contains("password")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					try {
						setDBPassword(AESEncrypt.decrypt(field));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (field.contains("world")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					setDBWorldName(field);
				}
			} else if (field.contains("next entry")) {
				if (scanner.hasNext()) {
					field = scanner.next();
					nextEntryID = field;
					nextEntryIDField.setText(field);
				} else {
					// no entry
					System.out.println("No entry in settings... Setting to default.");
			    	nextEntryID = "100000";
			    	nextEntryIDField.setText(nextEntryID);
			    	//TODO fill in field
				}
			} else {
				System.err.println("Unknown field in database configuration file.");
			}
		}
	}
	
	public String getDBUserText() {
		return dbUser.getText();
	}
	
	public String getDBPassText() {
		// Might want to do password
		// decrypting instead of printing
		// actual password text to file
		return dbPass.getText();
	}
	
	public String getDBNameText() {
		return dbName.getText();
	}
	
	public String getDBHostText() {
		return dbHost.getText();
	}
	
	public String getDBUserName() {
		return dbUserName;
	}
	
	public String getDBPassword() {
		return dbPassword;
	}
	
	public String getDBWorldName() {
		return dbWorldName;
	}
	
	public String getDBHostName() {
		return dbHostName;
	}
	
	public void setDBUserName(String string) {
		dbUserName = string;
		dbUser.setText(string);
	}
	
	public void setDBPassword(String string) {
		dbPassword = string;
		dbPass.setText(string);
	}
	
	public void setDBWorldName(String string) {
		dbWorldName = string;
		dbName.setText(string);
	}
	
	public void setDBHostName(String string) {
		dbHostName = string;
		dbHost.setText(string);
	}
	
	public void setSettingsFrame(JFrame settingsFrame) {
		this.settingsFrame = settingsFrame;
	}

	public String getNextEntryID() {
		return nextEntryID;
	}

	public void setNextEntryID(String nextEntryID) {
		this.nextEntryID = nextEntryID;
		nextEntryIDField.setText(nextEntryID);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!dbUser.getText().isEmpty() && !dbName.getText().isEmpty() && !dbPass.getText().isEmpty() && !dbHost.getText().isEmpty()) {
				save.doClick();
			} else {
				// error message: fill in empty fields
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
