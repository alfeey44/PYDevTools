/**
 * 
 */
package PYDevTools.view;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.*;

import PYDevTools.utilities.MySQLAccess;

/**
 * @author alfeey44
 *
 */
public class MiscPanel extends JPanel{
	
	public MiscPanel() {
		super();
		
		/////////////
		// Content //
		/////////////
		
		MySQLAccess db = new MySQLAccess();
		try {
			db.getItemFromTemplate(19019);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Item not found with given entry.");
		}
	}
}
