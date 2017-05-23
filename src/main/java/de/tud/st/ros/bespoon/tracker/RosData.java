package de.tud.st.ros.bespoon.tracker;

import java.util.List;

/**
 * ROS data object for publish
 * 
 * @author proteus
 *
 */

public class RosData {

	private List<Object> tags;
	private String anchor;
	private int anchorX;
	private int anchorY;

	public List<Object> getTags() {
		return tags;
	}

	public void setTags(List<Object> tags) {
		this.tags = tags;
	}

	public int getAnchorX() {
		return anchorX;
	}

	public void setAnchorX(int anchorX) {
		this.anchorX = anchorX;
	}

	public int getAnchorY() {
		return anchorY;
	}

	public void setAnchorY(int anchorY) {
		this.anchorY = anchorY;
	}

	public String getAnchor() {
		return anchor;
	}

	public void setAnchor(String anchor) {
		this.anchor = anchor;

		// format data for ROS publisher
		if (anchor != null) {
			String token[] = (anchor.replace('{', ' ').replace('}', ' ')).split(";");
			if (token != null && token.length >= 2) {
				this.anchorX = Integer.parseInt(token[0].trim());
				this.anchorY = Integer.parseInt(token[1].trim());
			}
		}
	}

}
