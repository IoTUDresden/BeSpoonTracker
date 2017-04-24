package de.tud.st.ros.bespoon.tracker;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.tud.bespoon.BeSpoon;
import org.tud.bespoon.BeSpoon2D;
import org.tud.bespoon.event.BeSpoonAnchorEventListener;
import org.tud.bespoon.event.BeSpoonDistanceEventListener;
import org.tud.bespoon.event.BeSpoonTagEventListener;
import org.tud.bespoon.model.BeSpoonTag;
import org.tud.bespoon.model.Position;
import org.tud.bespoon.model.Position2D;

/**
 * 
 * BeSpoon Anchor Tracker, Continuously update the anchor position and
 * corresponding distance from each tags it associated
 * 
 * @author proteus
 *
 */

public class BeSpoonTracker implements BeSpoonAnchorEventListener<Euclidean2D>, BeSpoonDistanceEventListener,
		BeSpoonTagEventListener<Euclidean2D> {

	private BeSpoon<Euclidean2D> bespoon;

	// Save anchor distance from distinct tags, <TagId, Distance>
	private Map<Integer, Integer> anchorDistance;
	// Anchor current position
	private String anchor_position;

	public BeSpoonTracker() {
		bespoon = new BeSpoon2D();
		anchorDistance = new HashMap<>();
	}

	public void start() {
		bespoon.addAnchorListener(this);
		bespoon.addTagListener(this);

		for (BeSpoonTag<Euclidean2D> tag : bespoon.getTags()) {
			bespoon.addDistanceListener(tag, this);
		}

		bespoon.start();
	}

	/**
	 * add a tag in BeSpoon tag list
	 * 
	 * @param tagid
	 *            tag id
	 * @param x
	 *            initial position in X
	 * @param y
	 *            initial position in y
	 */
	public void addTag(int tagid, int x, int y) {
		BeSpoonTag<Euclidean2D> tag = bespoon.newTag(tagid);
		tag.setPosition(new Position2D(x, y));
		bespoon.addTag(tag);
	}

	public void connected() {
		System.out.println("BeSpoon Connected");
	}

	public void tagAdded(BeSpoonTag<Euclidean2D> tag) {
		System.out.println("Tag added: " + tag.getId());
	}

	public void tagRemoved(BeSpoonTag<Euclidean2D> tag) {
		System.out.println("Tag removed: " + tag.getId());
		// remove tag and distance while it removed
		anchorDistance.remove(tag.getId());
	}

	public void distanceMesurement(int tagId, int distance) {
		// System.out.println("Distance of anchor from tag " + tagId + " to " +
		// distance + "cm");

		updateAnchorDistance(tagId, distance);
	}

	public void anchorPositionUpdate(Position<Euclidean2D> position) {
		// System.out.println("Anchor position: " + position.toString());
		anchor_position = position.toString();
	}

	/**
	 * Store the tag and distance from that tag, and generate output for ROS
	 * published topic
	 * 
	 * @param tagId:
	 *            id of the tag
	 * @param distance:
	 *            distance from that tag
	 */

	public void updateAnchorDistance(int tagId, int distance) {
		anchorDistance.put(tagId, distance);

		System.out.println("tags: " + Arrays.toString(anchorDistance.keySet().stream().mapToInt(c -> c).toArray())
				+ ", distance: " + Arrays.toString(anchorDistance.values().stream().mapToInt(c -> c).toArray())
				+ ", anchor_position: \"" + anchor_position + "\"");
	}
}
