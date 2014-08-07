package PYDevTools.view;

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
	private JTextField dbUser, dbName;
	private JPasswordField dbPass;
	private JLabel dbUserLabel, dbPassLabel, dbNameLabel;
	private JButton save;
	private Font font;
	private String dbUserName = ""; 
	private String dbPassword = ""; 
	private String dbWorldName = "";
	
	public SettingsPanel() {
		super();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		font = new Font("Arial", Font.PLAIN, 20);
		
		// Labels
		dbUserLabel = new JLabel("DB Username: ");
		dbUserLabel.setFont(font);
		SpringLayout.Constraints dbUserLabelCons = layout.getConstraints(dbUserLabel);
		dbUserLabelCons.setX(Spring.constant(10));
		dbUserLabelCons.setY(Spring.constant(10));
		add(dbUserLabel);
		dbPassLabel = new JLabel("DB Password: ");
		dbPassLabel.setFont(font);
		SpringLayout.Constraints dbPassLabelCons = layout.getConstraints(dbPassLabel);
		dbPassLabelCons.setX(Spring.constant(10));
		dbPassLabelCons.setY(Spring.constant(40));
		add(dbPassLabel);
		dbNameLabel = new JLabel("World DB Name: ");
		dbNameLabel.setFont(font);
		SpringLayout.Constraints dbNameLabelCons = layout.getConstraints(dbNameLabel);
		dbNameLabelCons.setX(Spring.constant(10));
		dbNameLabelCons.setY(Spring.constant(70));
		add(dbNameLabel);
		
		// TextFields
		dbUser = new JTextField(15);
		dbUser.setFont(font);
		SpringLayout.Constraints dbUserCons = layout.getConstraints(dbUser);
		dbUserCons.setX(Spring.constant(170));
		dbUserCons.setY(Spring.constant(10));
		add(dbUser);
		dbPass = new JPasswordField(15);
		dbPass.setFont(font);
		SpringLayout.Constraints dbPassCons = layout.getConstraints(dbPass);
		dbPassCons.setX(Spring.constant(170));
		dbPassCons.setY(Spring.constant(40));
		add(dbPass);
		dbName = new JTextField(15);
		dbName.setFont(font);
		SpringLayout.Constraints dbNameCons = layout.getConstraints(dbName);
		dbNameCons.setX(Spring.constant(170));
		dbNameCons.setY(Spring.constant(70));
		add(dbName);
		
		// Button
		save = new JButton("Save");
		save.setFont(font);
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
			dbUserName = dbUser.getText();
			dbPassword = dbPass.getPassword().toString();
			dbWorldName = dbName.getText();
			writeConfigFile();
		}
	}
	
	private void writeConfigFile() {
		Writer writer = null;
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("pyconfig.conf"), "utf-8"));
			writer.write(dbUserName + ",");
			writer.write(dbPassword + ",");
			writer.write(dbWorldName);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
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
				}
			}
		}
	}
	
	public String getDBUserText() {
		return dbUser.getText();
	}
	
	public String getDBPassText() {
		return dbPass.getPassword().toString();
	}
	
	public String getDBNameText() {
		return dbName.getText();
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
	
	public String getDBUserName() {
		return dbUserName;
	}
	
	public String getDBPassword() {
		return dbPassword;
	}
	
	public String getDBWorldName() {
		return dbWorldName;
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
}
