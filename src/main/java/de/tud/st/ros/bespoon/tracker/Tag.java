package de.tud.st.ros.bespoon.tracker;

public class Tag {

	public static String PROP = "tagid";

	private int tagId;
	private int distX;
	private int distY;

	public Tag(int id, int x, int y) {
		this.tagId = id;
		this.distX = x;
		this.distY = y;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getDistX() {
		return distX;
	}

	public void setDistX(int distX) {
		this.distX = distX;
	}

	public int getDistY() {
		return distY;
	}

	public void setDistY(int distY) {
		this.distY = distY;
	}

	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", distX=" + distX + ", distY=" + distY + "]";
	}
}
