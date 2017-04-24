package de.tud.st.ros.bespoon.tracker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * BeSpoon tracker initializer, initial configuration and bootstrapping of
 * hardwares
 * 
 * @author proteus
 *
 */

public class Initializer {

	private String configFile = "bespoon.properties";

	public void initBeSpoon() {
		System.out.println("BeSpoon initializing...");
		BeSpoonTracker bst = new BeSpoonTracker();

		for (Tag t : getTagValues()) {
			bst.addTag(t.getTagId(), t.getDistX(), t.getDistY());
			// System.out.println(t.toString());
		}

		// bst.addTag(2024, 145, 0);
		// bst.addTag(3383, 315, 290);
		// bst.addTag(1107, 100, 0);
		bst.start();

		System.out.println("BeSpoon starting...");
	}

	/**
	 * Read BeSpoon tags values from properties file (tagid, x, y). properties
	 * file example:
	 * 
	 * <pre>
	 * {@code
	 * tagid.1=1107
	 * 
	 * tagid.1.x=20
	 * tagid.1.y=80
	 * }
	 * </pre>
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public List<Tag> getTagValues() {

		// if property file name set in system, then override
		String propfile = System.getenv("BESPOON_PROP_FILE");
		if (propfile != null) {
			configFile = propfile;
		}

		Properties p = readProperties(configFile);
		List<Tag> tags = new ArrayList<Tag>();

		int total = 1;
		String key = Tag.PROP.concat(".") + total;
		String val;

		// extract the parameters
		while ((val = p.getProperty(key)) != null) {
			int id = Integer.valueOf(val);
			int x = Integer.valueOf(p.getProperty(key.concat(".x"), "0"));
			int y = Integer.valueOf(p.getProperty(key.concat(".y"), "0"));
			tags.add(new Tag(id, x, y));
			// increment for next tag
			total = total + 1;
			key = Tag.PROP.concat(".") + total;
		}
		return tags;
	}

	private Properties readProperties(String filename) {
		Properties prop = new Properties();

		try (FileInputStream in = new FileInputStream(filename)) {
			prop.load(in);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return prop;
	}

}
