/**
 * 
 */
package PYDevTools.view;

import javax.swing.JPanel;

import PYDevTools.utilities.MySQLAccess;

/**
 * @author alfeey44
 *
 */
public class MiscPanel extends JPanel{
	
	MySQLAccess db = new MySQLAccess();
	
	public MiscPanel() {
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
