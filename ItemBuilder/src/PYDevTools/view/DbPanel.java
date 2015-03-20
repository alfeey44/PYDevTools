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
public class DbPanel extends JPanel{
	
	MySQLAccess db = new MySQLAccess();
	
	public DbPanel() {
		super();
		
		/////////////
		// Content //
		/////////////
		
		// Testing Purposes
		/*try {
			db.getItemFromTemplate(19019);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Item not found with given entry.");
		}*/
	}
}
