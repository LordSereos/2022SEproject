package DTOs;

import com.google.gson.annotations.SerializedName;

public class PointDTO {
	@SerializedName(value = "Id")
	private String id;
	@SerializedName(value = "CoordinateX")
	private Double coordinateX;
	@SerializedName(value = "CoordinateY")
	private Double coordinateY;
	@SerializedName(value = "CoordinateZ")
	private Double coordinateZ;
	
	public PointDTO(String id, Double coordinateX, Double coordinateY, Double coordinateZ) {
		this.id = id;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.coordinateZ = coordinateZ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(Double coordinateX) {
		this.coordinateX = coordinateX;
	}

	public Double getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(Double coordinateY) {
		this.coordinateY = coordinateY;
	}

	public Double getCoordinateZ() {
		return coordinateZ;
	}

	public void setCoordinateZ(Double coordinateZ) {
		this.coordinateZ = coordinateZ;
	}	
}
