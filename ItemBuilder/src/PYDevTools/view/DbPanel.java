/**
 * 
 */
package PYDevTools.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import PYDevTools.db.structures.Item;
import PYDevTools.utilities.ItemToolTip;
import PYDevTools.utilities.MySQLAccess;

/**
 * @author alfeey44
 *
 */
public class DbPanel extends JPanel implements ActionListener {
	HashMap<Item, ItemToolTip> items;
	ItemToolTip currentTT;
	JLabel searchBarLabel, itemNotFoundLabel;
	JTextField searchBar;
	JButton search;
	
	JScrollPane tableScrollPane;
	JTable itemsTable;
	Vector<String> columnNames;
	Vector<Vector<String>> tableData;
	
	SpringLayout mainLayout;
	
	MySQLAccess db = new MySQLAccess();
	
	@SuppressWarnings("serial")
	public DbPanel() {
		super();
		tableScrollPane = new JScrollPane();
		items = new HashMap<Item, ItemToolTip>();
		
		mainLayout = new SpringLayout();
		setLayout(mainLayout);
		
		// Search Bar
		searchBarLabel = new JLabel("Search for Items ");
		SpringLayout.Constraints searchBarLabelCons = mainLayout.getConstraints(searchBarLabel);
		searchBarLabelCons.setX(Spring.constant(20));
		searchBarLabelCons.setY(Spring.constant(20));
		add(searchBarLabel);
		searchBar = new JTextField(20);
		SpringLayout.Constraints searchBarCons = mainLayout.getConstraints(searchBar);
		searchBarCons.setX(Spring.constant(120));
		searchBarCons.setY(Spring.constant(20));
		add(searchBar);
		search = new JButton("Search");
		search.setActionCommand("Search");
		search.addActionListener(this);
		SpringLayout.Constraints searchCons = mainLayout.getConstraints(search);
		searchCons.setX(Spring.constant(350));
		searchCons.setY(Spring.constant(17));
		add(search);
		
		itemNotFoundLabel = new JLabel("No items found");
		itemNotFoundLabel.setForeground(Color.red);
		itemNotFoundLabel.setVisible(false);
		SpringLayout.Constraints itemNotFoundLabelCons = mainLayout.getConstraints(itemNotFoundLabel);
		itemNotFoundLabelCons.setX(Spring.constant(250));
		itemNotFoundLabelCons.setY(Spring.constant(40));
		add(itemNotFoundLabel);
		
		// Table
		columnNames = new Vector<String>();
		columnNames.add(0, "Entry");
		columnNames.add(1, "Name");
		columnNames.add(2, "Description");
		
		tableData = new Vector<Vector<String>>();
		
		DefaultTableModel tableModel = new DefaultTableModel(tableData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		itemsTable = new JTable(tableModel);
		itemsTable.setFillsViewportHeight(true);
		
		itemsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = itemsTable.getSelectedRow();
				int selectedEntry = Integer.parseInt((String) itemsTable.getValueAt(selectedRow, 0));
				
				Iterator<HashMap.Entry<Item, ItemToolTip>> iterator = items.entrySet().iterator();
				while(iterator.hasNext()){
				   HashMap.Entry<Item, ItemToolTip> entry = iterator.next();
				   if (entry.getKey().getEntry() == selectedEntry) {
					   remove(currentTT);
					   currentTT = entry.getValue();
					   SpringLayout.Constraints toolTipCons = mainLayout.getConstraints(currentTT);
					   toolTipCons.setX(Spring.constant(700));
					   toolTipCons.setY(Spring.constant(100));
					   add(currentTT);
					   
					   repaint();
					   revalidate();
				   } else {
					   
				   }
				}
			}
			
		});
		
		tableScrollPane.setPreferredSize(new Dimension(500, 500));
		
		SpringLayout.Constraints scrollPaneCons = mainLayout.getConstraints(tableScrollPane);
		scrollPaneCons.setX(Spring.constant(20));
		scrollPaneCons.setY(Spring.constant(100));
		tableScrollPane.getViewport().add(itemsTable);
		
		add(tableScrollPane);
		
		Item tempItem = new Item();
		try {
			currentTT = new ItemToolTip(tempItem);
			SpringLayout.Constraints toolTipCons = mainLayout.getConstraints(currentTT);
			toolTipCons.setX(Spring.constant(700));
			toolTipCons.setY(Spring.constant(100));
		   
			add(currentTT);
			currentTT.setVisible(false);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Search")) {
			// Remove current list of items
			Iterator<HashMap.Entry<Item, ItemToolTip>> iterator = items.entrySet().iterator();
			while(iterator.hasNext()){
			   HashMap.Entry<Item, ItemToolTip> entry = iterator.next();
			   remove(entry.getValue());
			   iterator.remove(); // right way to remove entries from Map, 
			}
			tableData.removeAllElements();
			if (!searchBar.getText().equals("")) {
				try {
					ArrayList<Item> dbItems = db.getItemFromTemplate(searchBar.getText());
					if (dbItems != null) {
						itemNotFoundLabel.setVisible(false);
						for (int i = 0; i < dbItems.size(); i++) {
							ItemToolTip toolTip = new ItemToolTip(dbItems.get(i));
							items.put(dbItems.get(i), toolTip);
							
							Vector<String> row = new Vector<String>();
							row.add(0, String.valueOf(dbItems.get(i).getEntry()));
							row.add(1, dbItems.get(i).getName());
							row.add(2, dbItems.get(i).getDescription());
							
							tableData.add(row);
						}
						// Needed to repaint the panel
						tableScrollPane.getViewport().revalidate();
						tableScrollPane.getViewport().repaint();
						repaint();
						revalidate();
					} else {
						// Item not found
						itemNotFoundLabel.setVisible(true);
						// used when removing component
						repaint();
						// used when adding component
						revalidate();
					}
				} catch (Exception exeption) {
					//exeption.printStackTrace();
					System.out.println("Item not found with given entry.");
				}
			} else {
				// searchbar is empty
				itemNotFoundLabel.setVisible(false);
				repaint();
				revalidate();
			}
		}
	}
}
