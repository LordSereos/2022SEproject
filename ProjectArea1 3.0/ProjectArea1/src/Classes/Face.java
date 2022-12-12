package Classes;

import java.util.ArrayList;

public class Face {
	private String id;
	private String designator;
	private String type;
	private ArrayList<String> children;
	private Polygon polygon;

	public Face(String id, String designator, String type, ArrayList<String> children) {
		this.id = id;
		this.designator = designator;
		this.type = type;
		this.children = children;
		this.polygon = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesignator() {
		return designator;
	}

	public void setDesignator(String designator) {
		this.designator = designator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<String> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<String> children) {
		this.children = children;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
}
