package Classes;

public class Point {
	private String id;
	private Double coordinateX;
	private Double coordinateY;
	private Double coordinateZ;

	public Point(String id) {
		this.id = id;
	}

	public Point(String id, Double coordinateX, Double coordinateY, Double coordinateZ) {
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