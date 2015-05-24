package PYDevTools.utilities;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ItemIconFinder {
	private static ItemIconFinder instance = null;
	private HashMap<Integer, String> itemIcons;
	
	private final String path = "src/icons/ItemDisplayInfo.dbc.txt";
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public static ItemIconFinder getInstance() {
		if (instance == null) {
			instance = new ItemIconFinder();
		}
		return instance;
	}
	
	protected ItemIconFinder() {
		itemIcons = new HashMap<Integer, String>();
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// fills itemIcons HashMap
				processFile(path);
			}
		});

		t.start();
	}

	public String findIconByDisplayId(int id) {
		return itemIcons.get(id);
	}
	
	private void processLine(String aLine, String displayId) {
		String icon = "";
		String id = "";
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter(",");
		if (scanner.hasNext()) {
			id = scanner.next();
			if (scanner.hasNext()) {
				icon = scanner.next();
				if (!id.isEmpty() && !icon.isEmpty()) {
					itemIcons.put(Integer.parseInt(id), icon);
				}
			}
		}
	}
	
	private void processFile(String displayId) {
		try (Scanner scanner = new Scanner(Paths.get(path), ENCODING.name())) {
			while (scanner.hasNextLine()) {
				processLine(scanner.nextLine(), displayId);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
