package de.tud.st.ros.bespoon.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.TestCase;

public class RosDataTest extends TestCase {

	public void testRosJsonData() {
		String expected = "{\"tags\":[{\"tagId\":3383,\"x\":20,\"y\":30,\"z\":0,\"anchorDistance\":0},"
				+ "{\"tagId\":3383,\"x\":20,\"y\":30,\"z\":0,\"anchorDistance\":0}],"
				+ "\"anchor\":\"{20; 30}\",\"anchorX\":20,\"anchorY\":30}";

		Tag t = new Tag(3383, 20, 30);

		Map<Integer, Tag> tracker = new HashMap<>();
		tracker.put(1, t);
		tracker.put(2, t);

		RosData r = new RosData();
		r.setTags(new ArrayList<>(tracker.values()));
		r.setAnchor("{20; 30}");

		Gson gson = new GsonBuilder().create();

		assertEquals(expected, gson.toJson(r));
	}

}
