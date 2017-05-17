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

	public List<Object> getTags() {
		return tags;
	}

	public void setTags(List<Object> tags) {
		this.tags = tags;
	}

	public String getAnchor() {
		return anchor;
	}

	public void setAnchor(String anchor) {
		if (anchor != null) {
			anchor = anchor.replace('{', '[').replace('}', ']');
		}
		this.anchor = anchor;
	}

}
