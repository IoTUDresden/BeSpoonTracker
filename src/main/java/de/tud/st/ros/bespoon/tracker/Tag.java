package de.tud.st.ros.bespoon.tracker;

public class Tag {

	public static String PROP = "tagid";

	private int tagId;
	private int x;
	private int y;
	private int z;
	private int anchorDistance;

	public Tag(int id, int x, int y) {
		this.tagId = id;
		this.x = x;
		this.y = y;
	}

	public Tag(int id, int x, int y, int z) {
		this.tagId = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Tag(int id, int x, int y, int z, int dist) {
		this.tagId = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.anchorDistance = dist;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getAnchorDistance() {
		return anchorDistance;
	}

	public void setAnchorDistance(int anchorDistance) {
		this.anchorDistance = anchorDistance;
	}

	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", x=" + x + ", y=" + y + ", z=" + z + ", anchorDistance=" + anchorDistance
				+ "]";
	}

}
