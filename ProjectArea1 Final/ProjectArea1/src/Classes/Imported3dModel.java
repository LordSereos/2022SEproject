package Classes;

import java.util.ArrayList;

public class Imported3dModel {
	private String reportId;
	private String sourceVersion;
	private String lengthUnit;
	private Location location;
	private Double structureOrientation;
	private ArrayList<Roof> roofs;
	
	public Imported3dModel() {
		this.roofs = new ArrayList<Roof>();
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getSourceVersion() {
		return sourceVersion;
	}

	public void setSourceVersion(String sourceVersion) {
		this.sourceVersion = sourceVersion;
	}

	public String getLengthUnit() {
		return lengthUnit;
	}

	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Double getStructureOrientation() {
		return structureOrientation;
	}

	public void setStructureOrientation(Double structureOrientation) {
		this.structureOrientation = structureOrientation;
	}

	public ArrayList<Roof> getRoofs() {
		return roofs;
	}

	public void setRoofs(ArrayList<Roof> roofs) {
		this.roofs = roofs;
	}	
}
