package Classes;

import java.util.ArrayList;

public class Line {
	private String id;
	private String type;
	private ArrayList<String> pointIds;
	
	public Line(String id, String type) {		
		this.id = id;
		this.type = type;
		this.pointIds = new ArrayList<String>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<String> getPointIds() {
		return pointIds;
	}

	public void setPointIds(ArrayList<String> pointIds) {
		this.pointIds = pointIds;
	}
}
