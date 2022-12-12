package DTOs;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class LineDTO {
	@SerializedName(value = "Id")
	private String id;
	@SerializedName(value = "Type")
	private String type;
	@SerializedName(value = "Points")
	private ArrayList<PointDTO> points;
	
	public LineDTO(String id, String type) {		
		this.id = id;
		this.type = type;
		this.points = new ArrayList<PointDTO>();
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

	public ArrayList<PointDTO> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<PointDTO> points) {
		this.points = points;
	}	
}
