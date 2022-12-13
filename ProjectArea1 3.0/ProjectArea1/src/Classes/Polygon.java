package Classes;

import java.util.ArrayList;

public class Polygon {
	private String id;
	private Double orientation;
	private Double pitch;
	private Double size;
	private Double unroundedSize;
	private String pitchuom;
	private String sizeuom;
	private String material;
	private ArrayList<String> lineIds;
	
	public Polygon(String id, Double orientation, Double pitch, Double size, Double unroundedSize, 
			String pitchuom, String sizeuom, String material) {		
		this.id = id;
		this.orientation = orientation;
		this.pitch = pitch;
		this.size = size;
		this.unroundedSize = unroundedSize;
		this.pitchuom = pitchuom;
		this.sizeuom = sizeuom;
		this.material = material;
		this.lineIds = new ArrayList<String>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getOrientation() {
		return orientation;
	}

	public void setOrientation(Double orientation) {
		this.orientation = orientation;
	}

	public Double getPitch() {
		return pitch;
	}

	public void setPitch(Double pitch) {
		this.pitch = pitch;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Double getUnroundedSize() {
		return unroundedSize;
	}

	public void setUnroundedSize(Double unroundedSize) {
		this.unroundedSize = unroundedSize;
	}	

	public String getPitchuom() {
		return pitchuom;
	}

	public void setPitchuom(String pitchuom) {
		this.pitchuom = pitchuom;
	}
	
	public String getSizeuom() {
		return sizeuom;
	}

	public void setSizeuom(String sizeuom) {
		this.sizeuom = sizeuom;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public ArrayList<String> getLineIds() {
		return lineIds;
	}

	public void setLineIds(ArrayList<String> lineIds) {
		this.lineIds = lineIds;
	}	
}
