package PYDevTools.utilities;

import java.awt.Color;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JLabel;

public class ItemToolTip {
	private ImageDrawingComponent toolTip;
	
	private String[] toolTipLabels = { "Item Name", "Item Level", "Binds On", "Unique", "Equip", "subclass", 
			"Damage", "Delay", "DPS", "Stats", "Duribility", "Spell Equips",
			"Required Level", "Sell Price" };
	private JLabel TTname, TTdesc, TTilvl, TTbinds, TTunique, TTequip, TTsubclass, TTmindamage, TTmaxdamage, TTdelay,
	   TTDPS, TTsocket1, TTsocket2, TTsocket3, TTsocketbonus, TTduribility, TTreqclass, TTreqrace, TTspell1, 
	   TTspell2, TTspell3, TTspell4, TTspell5, TTreqlvl, TTset, TTsellprice;
	private JLabel[] TTstats = new JLabel[10];
	private int numTTLabels = toolTipLabels.length;
	private JLabel[] TTresists = new JLabel[6];
	
	public ItemToolTip() {
		
	}
	
	public void draw() {
		int width = 550;
		int height = 100;
		
		try {
			toolTip = new ImageDrawingComponent(new File("src/icons/tooltip.png").toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		toolTip.resize(width, height);
		
		TTname = new JLabel("", JLabel.TRAILING);
		TTname.setForeground(Color.WHITE);
		TTname.setLocation(10, 30);
		toolTip.add(TTname);
		TTdesc = new JLabel("", JLabel.TRAILING);
		TTdesc.setForeground(Color.WHITE);
		TTdesc.setLocation(10, 0);
		toolTip.add(TTdesc);
		TTilvl = new JLabel("", JLabel.TRAILING);
		TTilvl.setForeground(Color.YELLOW);
		TTilvl.setLocation(10, 60);
		toolTip.add(TTilvl);
		TTbinds = new JLabel("", JLabel.TRAILING);
		TTbinds.setForeground(Color.WHITE);
		TTbinds.setLocation(10, 90);
		toolTip.add(TTbinds);
		TTunique = new JLabel("", JLabel.TRAILING);
		TTunique.setForeground(Color.WHITE);
		TTunique.setLocation(10, 120);
		toolTip.add(TTunique);
		TTequip = new JLabel("", JLabel.TRAILING);
		TTequip.setForeground(Color.WHITE);
		TTequip.setLocation(10, 150);
		toolTip.add(TTequip);
		TTsubclass = new JLabel("", JLabel.TRAILING);
		TTsubclass.setForeground(Color.WHITE);
		TTsubclass.setLocation(10, 150);
		toolTip.add(TTsubclass);
		TTmindamage = new JLabel("", JLabel.TRAILING);
		TTmindamage.setForeground(Color.WHITE);
		TTmindamage.setLocation(10, 180);
		toolTip.add(TTmindamage);
		TTmaxdamage = new JLabel("", JLabel.TRAILING);
		TTdelay = new JLabel("", JLabel.TRAILING);
		TTdelay.setForeground(Color.WHITE);
		TTdelay.setLocation(410, 180);
		toolTip.add(TTdelay);
		TTDPS = new JLabel("", JLabel.TRAILING);
		TTDPS.setForeground(Color.WHITE);
		TTDPS.setLocation(10, 210);
		toolTip.add(TTDPS);
		for (int i = 0; i < 10; i++) {
			TTstats[i] = new JLabel("", JLabel.TRAILING);
			TTstats[i].setForeground(Color.WHITE);
			TTstats[i].setLocation(10, 0);
			toolTip.add(TTstats[i]);
		}
		for (int i = 0; i < 6; i++) {
			TTresists[i] = new JLabel("", JLabel.TRAILING);
			TTresists[i].setForeground(Color.WHITE);
			TTresists[i].setLocation(10, 0);
			toolTip.add(TTresists[i]);
		}
		TTsocket1 = new JLabel("", JLabel.TRAILING);
		TTsocket1.setForeground(Color.WHITE);
		TTsocket1.setLocation(10, 0);
		toolTip.add(TTsocket1);
		TTsocket2 = new JLabel("", JLabel.TRAILING);
		TTsocket2.setForeground(Color.WHITE);
		TTsocket2.setLocation(10, 0);
		toolTip.add(TTsocket2);
		TTsocket3 = new JLabel("", JLabel.TRAILING);
		TTsocket3.setForeground(Color.WHITE);
		TTsocket3.setLocation(10, 0);
		toolTip.add(TTsocket3);
		TTsocketbonus = new JLabel("", JLabel.TRAILING);
		TTsocketbonus.setForeground(Color.WHITE);
		TTsocketbonus.setLocation(10, 0);
		toolTip.add(TTsocketbonus);
		TTduribility = new JLabel("", JLabel.TRAILING);
		TTduribility.setForeground(Color.WHITE);
		TTduribility.setLocation(10, 0);
		toolTip.add(TTduribility);
		TTreqclass = new JLabel("", JLabel.TRAILING);
		TTreqclass.setForeground(Color.WHITE);
		TTreqclass.setLocation(10, 0);
		toolTip.add(TTreqclass);
		TTreqrace = new JLabel("", JLabel.TRAILING);
		TTreqrace.setForeground(Color.WHITE);
		TTreqrace.setLocation(10, 0);
		toolTip.add(TTreqrace);
		TTspell1 = new JLabel("", JLabel.TRAILING);
		TTspell1.setForeground(Color.WHITE);
		TTspell1.setLocation(10, 0);
		toolTip.add(TTspell1);
		TTspell2 = new JLabel("", JLabel.TRAILING);
		TTspell2.setForeground(Color.WHITE);
		TTspell2.setLocation(10, 0);
		toolTip.add(TTspell2);
		TTspell3 = new JLabel("", JLabel.TRAILING);
		TTspell3.setForeground(Color.WHITE);
		TTspell3.setLocation(10, 0);
		toolTip.add(TTspell3);
		TTspell4 = new JLabel("", JLabel.TRAILING);
		TTspell4.setForeground(Color.WHITE);
		TTspell4.setLocation(10, 0);
		toolTip.add(TTspell4);
		TTspell5 = new JLabel("", JLabel.TRAILING);
		TTspell5.setForeground(Color.WHITE);
		TTspell5.setLocation(10, 0);
		toolTip.add(TTspell5);
		TTreqlvl = new JLabel("", JLabel.TRAILING);
		TTreqlvl.setForeground(Color.WHITE);
		TTreqlvl.setLocation(10, 0);
		toolTip.add(TTreqlvl);
		TTset = new JLabel("", JLabel.TRAILING);
		TTset.setForeground(Color.WHITE);
		TTset.setLocation(10, 0);
		toolTip.add(TTset);
		TTsellprice = new JLabel("", JLabel.TRAILING);
		TTsellprice.setForeground(Color.WHITE);
		TTsellprice.setLocation(10, 0);
		toolTip.add(TTsellprice);
	}
}
