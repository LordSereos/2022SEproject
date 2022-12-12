package Classes;

import java.util.HashMap;
import java.util.Map;

public class Roof {
	private String id;
	private Map<String, Face> faces;
	private Map<String, Line> lines;
	private Map<String, Point> points;

	public Roof(String id) {
		this.id = id;
		faces = new HashMap<String, Face>();
		lines = new HashMap<String, Line>();
		points = new HashMap<String, Point>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Face> getFaces() {
		return faces;
	}

	public void setFaces(Map<String, Face> faces) {
		this.faces = faces;
	}

	public Map<String, Line> getLines() {
		return lines;
	}

	public void setLines(Map<String, Line> lines) {
		this.lines = lines;
	}

	public Map<String, Point> getPoints() {
		return points;
	}

	public void setPoints(Map<String, Point> points) {
		this.points = points;
	}
}
