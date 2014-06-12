/**
 * 
 */
package PYDevTools.view;

import javax.swing.*;

/**
 * @author Alfeey
 *
 */
public class ItemBuilder extends JFrame {
	
	JPanel panel;
	
	private ItemBuilder() {
		panel = new JPanel();
		
		this.add(panel);
	}
	
	public static void main(String[] args) {

		JFrame IBFrame = new ItemBuilder();
		IBFrame.setTitle("PY ItemBuilder");
		IBFrame.setSize(1280, 720);
		IBFrame.setLocation(200, 100);
		IBFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		IBFrame.setVisible(true);
	}

}
