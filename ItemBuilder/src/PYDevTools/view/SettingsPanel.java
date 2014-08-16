package PYDevTools.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class SettingsPanel extends JPanel implements ActionListener {
	
	private static SettingsPanel instance = null;
	private JTextField dbUser, dbName, dbHost;
	private JPasswordField dbPass;
	private JLabel dbUserLabel, dbPassLabel, dbNameLabel, dbHostLabel, saveSuccessLabel,
				   saveFailLabel;
	private JButton save;
	private Font defaultFont, saveFont;
	private String dbUserName = ""; 
	private String dbPassword = ""; 
	private String dbWorldName = "";
	private String dbHostName = "";
	
	public SettingsPanel() {
		super();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		defaultFont = new Font("Arial", Font.PLAIN, 20);
		saveFont = new Font("Arial", Font.ITALIC, 28);
		
		// Labels
		dbUserLabel = new JLabel("DB Username: ");
		dbUserLabel.setFont(defaultFont);
		SpringLayout.Constraints dbUserLabelCons = layout.getConstraints(dbUserLabel);
		dbUserLabelCons.setX(Spring.constant(10));
		dbUserLabelCons.setY(Spring.constant(10));
		add(dbUserLabel);
		dbPassLabel = new JLabel("DB Password: ");
		dbPassLabel.setFont(defaultFont);
		SpringLayout.Constraints dbPassLabelCons = layout.getConstraints(dbPassLabel);
		dbPassLabelCons.setX(Spring.constant(10));
		dbPassLabelCons.setY(Spring.constant(40));
		add(dbPassLabel);
		dbNameLabel = new JLabel("World DB Name: ");
		dbNameLabel.setFont(defaultFont);
		SpringLayout.Constraints dbNameLabelCons = layout.getConstraints(dbNameLabel);
		dbNameLabelCons.setX(Spring.constant(10));
		dbNameLabelCons.setY(Spring.constant(70));
		add(dbNameLabel);
		dbHostLabel = new JLabel("DB Host: ");
		dbHostLabel.setFont(defaultFont);
		SpringLayout.Constraints dbHostLabelCons = layout.getConstraints(dbHostLabel);
		dbHostLabelCons.setX(Spring.constant(10));
		dbHostLabelCons.setY(Spring.constant(100));
		add(dbHostLabel);
		saveSuccessLabel = new JLabel("Settings Saved Successfully!");
		saveSuccessLabel.setFont(saveFont);
		SpringLayout.Constraints saveSuccessLabelCons = layout.getConstraints(saveSuccessLabel);
		saveSuccessLabelCons.setX(Spring.constant(100));
		saveSuccessLabelCons.setY(Spring.constant(320));
		saveSuccessLabel.setVisible(false);
		add(saveSuccessLabel);
		saveFailLabel = new JLabel("Settings Saved Failed!");
		saveFailLabel.setFont(saveFont);
		SpringLayout.Constraints saveFailLabelCons = layout.getConstraints(saveFailLabel);
		saveSuccessLabelCons.setX(Spring.constant(100));
		saveSuccessLabelCons.setY(Spring.constant(320));
		saveFailLabel.setVisible(false);
		add(saveFailLabel);
		
		// TextFields
		dbUser = new JTextField(15);
		dbUser.setFont(defaultFont);
		SpringLayout.Constraints dbUserCons = layout.getConstraints(dbUser);
		dbUserCons.setX(Spring.constant(170));
		dbUserCons.setY(Spring.constant(10));
		add(dbUser);
		dbPass = new JPasswordField(15);
		dbPass.setFont(defaultFont);
		SpringLayout.Constraints dbPassCons = layout.getConstraints(dbPass);
		dbPassCons.setX(Spring.constant(170));
		dbPassCons.setY(Spring.constant(40));
		add(dbPass);
		dbName = new JTextField(15);
		dbName.setFont(defaultFont);
		SpringLayout.Constraints dbNameCons = layout.getConstraints(dbName);
		dbNameCons.setX(Spring.constant(170));
		dbNameCons.setY(Spring.constant(70));
		add(dbName);
		dbHost = new JTextField(15);
		dbHost.setFont(defaultFont);
		SpringLayout.Constraints dbHostCons = layout.getConstraints(dbHost);
		dbHostCons.setX(Spring.constant(170));
		dbHostCons.setY(Spring.constant(100));
		add(dbHost);
		
		// Button
		save = new JButton("Save");
		save.setFont(defaultFont);
		save.setActionCommand("Save");
		save.addActionListener(this);
		SpringLayout.Constraints saveCons = layout.getConstraints(save);
		saveCons.setX(Spring.constant(495));
		saveCons.setY(Spring.constant(320));
		add(save);
		
		// Initialize Config Info
		readConfigFile();
	}
	
	public static SettingsPanel getInstance() {
		if (instance == null)
			instance = new SettingsPanel();
		
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Save")) {
			dbUserName = getDBUserText();
			dbPassword = getDBPassText();
			dbWorldName = getDBNameText();
			dbHostName = getDBHostText();
			writeConfigFile();
		}
	}
	
	private void writeConfigFile() {
		Writer writer = null;
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("pyconfig.conf"), "utf-8"));
			writer.write(dbUserName + ",");
			writer.write(dbPassword + ",");
			writer.write(dbWorldName + ",");
			writer.write(dbHostName);
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
	
	private void readConfigFile() {
		try (Scanner scanner = new Scanner(Paths.get("pyconfig.conf"), StandardCharsets.UTF_8.name())) {
			// First Line DB Info
			if (scanner.hasNext())
				processDBConfigs(scanner.next());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void processDBConfigs(String aLine) {
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter(",");
		if (scanner.hasNext()) {
			setDBUserName(scanner.next());
			setDBUserText(getDBUserName());
			if (scanner.hasNext()) {
				setDBPassword(scanner.next());
				setDBPassText(getDBPassword());
				if (scanner.hasNext()) {
					setDBWorldName(scanner.next());
					setDBNameText(getDBWorldName());
					if (scanner.hasNext()) {
						setDBHostName(scanner.next());
						setDBHostText(getDBHostName());
					}
				}
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
	
	public void setDBUserText(String string) {
		dbUser.setText(string);
	}
	
	public void setDBPassText(String string) {
		dbPass.setText(string);
	}
	
	public void setDBNameText(String string) {
		dbName.setText(string);
	}
	
	public void setDBHostText(String string) {
		dbHost.setText(string);
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
	}
	
	public void setDBPassword(String string) {
		dbPassword = string;
	}
	
	public void setDBWorldName(String string) {
		dbWorldName = string;
	}
	
	public void setDBHostName(String string) {
		dbHostName = string;
	}
	
	private String charArrayToString(char[] chars) {
		String temp = "";
		for (char c : chars) {
			temp = temp + c;
		}
		return temp;
	}
}
