/**
 * 
 */
package PYDevTools.view;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * @author alfeey44
 *
 */
public class WeaponPanel extends JPanel{
	
	private JLabel lName, lDescription, lILevel, lReqLevel;
	private JTextField fName, fDescription, fILevel, fReqLevel;
	
	
	
	public WeaponPanel() {
		super();
		
		/////////////
		// Content //
		/////////////
		
		lName = new JLabel("Name: ");
		add(lName);
		
	}
}
