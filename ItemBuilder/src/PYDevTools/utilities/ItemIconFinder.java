package PYDevTools.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ItemIconFinder {
	
	private final String path = "src/icons/ItemDisplayInfo.dbc.txt";
	private final static Charset ENCODING = StandardCharsets.UTF_8;  

	public String findIconByDisplayId(int id) {
		String iconName = "";
		
		try {
			iconName = processFile(Integer.toString(id));
		} catch (NullPointerException e) {
			System.err.println("Did not find an icon for id: " + id);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return iconName;
	}
	
	private String processLine(String aLine, String displayId) {
		String icon = "";
		String id = "";
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter(",");
		if (scanner.hasNext()) {
			id = scanner.next();
			if (scanner.hasNext())
				icon = scanner.next();
			if (id.equals(displayId))
				return icon;
		}
		return null;
	}
	
	private String processFile(String displayId) throws IOException {
		String iconName;
		try (Scanner scanner = new Scanner(Paths.get(path), ENCODING.name())) {
			while (scanner.hasNextLine()) {
				iconName = processLine(scanner.nextLine(), displayId);
				if (iconName != null)
					return iconName;
			}
		}
		throw null;
	}
	
}
