/**
 * 
 */
package PYDevTools.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import PYDevTools.db.structures.Item;
import PYDevTools.enums.ItemType;
import PYDevTools.utilities.ImageDrawingComponent;
import PYDevTools.utilities.ItemToolTip;
import PYDevTools.utilities.MySQLAccess;

/**
 * @author alfeey44
 *
 */
public class DbPanel extends JPanel implements ActionListener, KeyListener {
	private JSplitPane mainPane;
	private JScrollPane leftPane, rightPane;
	private JPanel leftPanel, rightPanel;
	private HashMap<Item, ItemToolTip> items;
	private ItemToolTip currentTT;
	private ImageDrawingComponent currentIcon;
	private JLabel searchBarLabel, itemSearchStatusLabel;
	private JTextField searchBar;
	private JButton search;
	
	private JScrollPane tableScrollPane;
	private JTable itemsTable;
	private Vector<String> columnNames;
	private Vector<Vector<String>> tableData;
	
	private SpringLayout rightLayout;
	private SpringLayout leftLayout;
	
	private MySQLAccess db = new MySQLAccess();
	private Thread dbThread;
	
	@SuppressWarnings("serial")
	public DbPanel() {
		super();
		
		leftLayout = new SpringLayout();
		leftPanel = new JPanel(leftLayout);
		leftPane = new JScrollPane(leftPanel);
		leftPane.setPreferredSize(new Dimension(550, 650));
		leftPane.getVerticalScrollBar().setUnitIncrement(16);
		
		rightLayout = new SpringLayout();
		rightPanel = new JPanel(rightLayout);
		rightPane = new JScrollPane(rightPanel);
		rightPane.setPreferredSize(new Dimension(850, 650));
		rightPane.getVerticalScrollBar().setUnitIncrement(16);
		
		tableScrollPane = new JScrollPane();
		items = new HashMap<Item, ItemToolTip>();
		
		// Search Bar
		searchBarLabel = new JLabel("Search for Items ");
		SpringLayout.Constraints searchBarLabelCons = leftLayout.getConstraints(searchBarLabel);
		searchBarLabelCons.setX(Spring.constant(20));
		searchBarLabelCons.setY(Spring.constant(20));
		leftPanel.add(searchBarLabel);
		searchBar = new JTextField(20);
		searchBar.addKeyListener(this);
		SpringLayout.Constraints searchBarCons = leftLayout.getConstraints(searchBar);
		searchBarCons.setX(Spring.constant(120));
		searchBarCons.setY(Spring.constant(20));
		leftPanel.add(searchBar);
		search = new JButton("Search");
		search.setActionCommand("Search");
		search.addActionListener(this);
		SpringLayout.Constraints searchCons = leftLayout.getConstraints(search);
		searchCons.setX(Spring.constant(350));
		searchCons.setY(Spring.constant(17));
		leftPanel.add(search);
		
		itemSearchStatusLabel = new JLabel("No items found");
		itemSearchStatusLabel.setForeground(Color.red);
		itemSearchStatusLabel.setVisible(false);
		SpringLayout.Constraints itemSearchStatusLabelCons = leftLayout.getConstraints(itemSearchStatusLabel);
		itemSearchStatusLabelCons.setX(Spring.constant(190));
		itemSearchStatusLabelCons.setY(Spring.constant(50));
		leftPanel.add(itemSearchStatusLabel);
		
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
				
				Iterator<Map.Entry<Item, ItemToolTip>> iterator = items.entrySet().iterator();
				while(iterator.hasNext()){
				   try {
					   Map.Entry<Item, ItemToolTip> entry = iterator.next();
					   
					   if (entry.getKey().getEntry() == selectedEntry) {
						   rightPanel.remove(currentTT);
						   currentTT = entry.getValue();
						   SpringLayout.Constraints toolTipCons = rightLayout.getConstraints(currentTT);
						   toolTipCons.setX(Spring.constant(150));
						   toolTipCons.setY(Spring.constant(50));
						   rightPanel.add(currentTT);
						   
						   rightPanel.setPreferredSize(new Dimension(550, currentTT.getToolTipHeight()+125));
						   
						   currentIcon.setImage("src/icons/WoWIcons/" + currentTT.getIconPath() + ".png");
						   currentIcon.resize(80, 80);
						   currentIcon.repaint();
						   
						   repaint();
						   revalidate();
					   } else {
						   
					   }
				   } catch(ConcurrentModificationException cme) {
					   // Dont do anything, you cant edit the table anyway
				   }
				}
			}
			
		});
		
		itemsTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        String selectedEntry;
		        // Item in table double clicked
		        if (me.getClickCount() == 2) {
		        	Item clickedItem;
		        	Iterator<Map.Entry<Item, ItemToolTip>> iterator = items.entrySet().iterator();
		        	// Iterate through items for this id
					while(iterator.hasNext()){
						Map.Entry<Item, ItemToolTip> entry = iterator.next();
			        	if ((selectedEntry = (String)table.getValueAt(row, 0)) != null) {
			        		if (entry.getKey().getEntry() == Integer.parseInt(selectedEntry)) {
			        			clickedItem = entry.getKey();
			        			// Send clickedItem to editor based on type
			        			if (clickedItem.getItemType() == ItemType.weapon) {
			        				// send to weapon tab
			        				System.out.println("Send Weapon to Weapon Tab");
			        				WeaponPanel.getInstance().setCurrentWeapon(clickedItem);
			        				ItemBuilder.getInstance().goToWeaponTab();
			        			} else if (clickedItem.getItemType() == ItemType.armor) {
			        				// send to armor tab
			        				System.out.println("Send armor to armor Tab");
			        				ArmorPanel.getInstance().setCurrentItem(clickedItem);
			        				ItemBuilder.getInstance().goToArmorTab();
			        			}
			        			// 
			        		} else {
							   
			        		}
			        	} else {
			        		// Double clicked whitespace
			        	}
					}
		        }
		    }
		});
		
		tableScrollPane.setPreferredSize(new Dimension(500, 500));
		
		SpringLayout.Constraints scrollPaneCons = leftLayout.getConstraints(tableScrollPane);
		scrollPaneCons.setX(Spring.constant(20));
		scrollPaneCons.setY(Spring.constant(100));
		tableScrollPane.getViewport().add(itemsTable);
		
		leftPanel.add(tableScrollPane);
		
		// Item Icon
		try {
			currentIcon = new ImageDrawingComponent(new File("src/icons/Inv_misc_questionmark.png").toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		currentIcon.resize(80, 80);
		SpringLayout.Constraints iconCons = rightLayout.getConstraints(currentIcon);
		iconCons.setX(Spring.constant(50));
		iconCons.setY(Spring.constant(50));
		rightPanel.add(currentIcon);
		
		Item tempItem = new Item();
		try {
			currentTT = new ItemToolTip(tempItem);
			SpringLayout.Constraints toolTipCons = rightLayout.getConstraints(currentTT);
			toolTipCons.setX(Spring.constant(150));
			toolTipCons.setY(Spring.constant(50));
		   
			rightPanel.add(currentTT);
			currentTT.setVisible(false);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
		add(mainPane);
	}
	
	private void clearItemTable() {
		Iterator<Map.Entry<Item, ItemToolTip>> iterator = items.entrySet().iterator();
		while(iterator.hasNext()){
		   Map.Entry<Item, ItemToolTip> entry = iterator.next();
		   remove(entry.getValue());
		   iterator.remove(); // right way to remove entries from Map, 
		}
		tableData.removeAllElements();
	}
	
	private void fillItemTableFromSearchBarText() {
		itemSearchStatusLabel.setText("Querying Database...");
		itemSearchStatusLabel.setForeground(Color.blue);
		itemSearchStatusLabel.setVisible(true);
		
		dbThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				search.setEnabled(false);
				try {
					ArrayList<Item> dbItems = db.getItemFromTemplate(searchBar.getText());
					if (dbItems != null) {
						for (int i = 0; i < dbItems.size(); i++) {
							ItemToolTip toolTip = new ItemToolTip(dbItems.get(i));
							items.put(dbItems.get(i), toolTip);
							
							Vector<String> row = new Vector<String>();
							row.add(0, String.valueOf(dbItems.get(i).getEntry()));
							row.add(1, dbItems.get(i).getName());
							row.add(2, dbItems.get(i).getDescription());
							
							tableData.add(row);
						}
						// get rid of status message
						itemSearchStatusLabel.setVisible(false);
						// Needed to repaint the panel
						tableScrollPane.getViewport().revalidate();
						tableScrollPane.getViewport().repaint();
						repaint();
						revalidate();
					} else {
						// Item not found
						itemSearchStatusLabel.setText("No items found");
						itemSearchStatusLabel.setForeground(Color.red);
						itemSearchStatusLabel.setVisible(true);
						// used when removing component
						repaint();
						// used when adding component
						revalidate();
					}
				} catch(NullPointerException npe) {
					// Set error
					System.err.println("Not connected to db");
					itemSearchStatusLabel.setText("Can't connect to database");
					itemSearchStatusLabel.setForeground(Color.red);
					itemSearchStatusLabel.setVisible(true);
				} catch (Exception exeption) {
					exeption.printStackTrace();
				}
				search.setEnabled(true);
			}
		});
		
		dbThread.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Search")) {
			// Remove current list of items
			clearItemTable();
			if (!searchBar.getText().equals("")) {
				fillItemTableFromSearchBarText();
			} else {
				// searchbar is empty
				itemSearchStatusLabel.setVisible(false);
				clearItemTable();
				repaint();
				revalidate();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!searchBar.getText().isEmpty()) {
				search.doClick();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
