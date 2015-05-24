package PYDevTools.utilities;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class SpellFinder {
	private static SpellFinder instance = null;
	// spellId, IconName
	private HashMap<Integer, String> spellIcons;
	// spellId, IconId
	private HashMap<Integer, Integer> spellIconIds;
	// spellId, description
	private HashMap<Integer, String> spellDescriptions;
	
	// Spell1.dbc.txt for ; delimiter
	private final String spellDBC = "src/icons/Spell1.dbc.txt";
	private final String spellIconDBC = "src/icons/SpellIcon.dbc.txt";
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public static SpellFinder getInstance() {
		if (instance == null) {
			instance = new SpellFinder();
		}
		return instance;
	}
	
	protected SpellFinder() {
		spellIcons = new HashMap<Integer, String>();
		spellIconIds = new HashMap<Integer, Integer>();
		spellDescriptions = new HashMap<Integer, String>();
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// fills itemIcons HashMap
				processFileForSpellIcons();
				processFileForSpellIconIds();
				processFileForSpellDescriptions();
			}
		});

		t.start();
	}

	public String findSpellIconById(int id) {
		return spellIcons.get(spellIconIds.get(id));
	}
	
	private void processLineForSpellIcon(String aLine) {
		String icon = "";
		String id = "";
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter(",");
		if (scanner.hasNext()) {
			id = scanner.next();
			try {
				Integer.parseInt(id);
			} catch(Exception e) {
				// Id was not successfully grabbed
				return;
			}
			if (scanner.hasNext()) {
				icon = scanner.next();
				if (!id.isEmpty() && !icon.isEmpty()) {
					spellIcons.put(Integer.parseInt(id), icon);
				}
			}
		}
	}
	
	private void processFileForSpellIcons() {
		try (Scanner scanner = new Scanner(Paths.get(spellIconDBC), ENCODING.name())) {
			while (scanner.hasNextLine()) {
				processLineForSpellIcon(scanner.nextLine());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String findSpellDescriptionById(int id) {
		return spellDescriptions.get(id);
	}
	
	private void processLineForSpellDescription(String aLine) {
		String desc = "";
		String id = "";
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter(";");
		if (scanner.hasNext()) {
			id = scanner.next();
			try {
				Integer.parseInt(id);
			} catch(Exception e) {
				// Id was not successfully grabbed
				return;
			}
			System.out.println("id = " + id);
			for (int i = 0; i < 4; i++) {
				System.out.println(scanner.next());
			}
			if (scanner.hasNext()) {
				desc = scanner.next();
				System.out.println("desc = " + desc);
				if (!id.isEmpty() && !desc.isEmpty()) {
					spellDescriptions.put(Integer.parseInt(id), desc);
				}
			}
		}
	}
	
	private void processFileForSpellDescriptions() {
		try (Scanner scanner = new Scanner(Paths.get(spellDBC), ENCODING.name())) {
			while (scanner.hasNextLine()) {
				processLineForSpellDescription(scanner.nextLine());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void processLineForSpellIconId(String aLine) {
		String iconId = "";
		String id = "";
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter(";");
		if (scanner.hasNext()) {
			id = scanner.next();
			try {
				Integer.parseInt(id);
			} catch(Exception e) {
				// Id was not successfully grabbed
				return;
			}
			scanner.next();
			if (scanner.hasNext()) {
				iconId = scanner.next();
				if (!id.isEmpty() && !iconId.isEmpty()) {
					spellIconIds.put(Integer.parseInt(id), Integer.parseInt(iconId));
				} else
					System.out.println("id or itemId is empty");
			}
		}
	}
	
	private void processFileForSpellIconIds() {
		try (Scanner scanner = new Scanner(Paths.get(spellDBC), ENCODING.name())) {
			while (scanner.hasNextLine()) {
				processLineForSpellIconId(scanner.nextLine());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
