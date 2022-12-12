package DTOs;

import com.google.gson.annotations.SerializedName;

public class BaseDataDTO {
	@SerializedName(value = "ReportId")
	private String reportId;
	@SerializedName(value = "UnitOfLength")
	private String unitOfLength;
	@SerializedName(value = "Address")
	private String address;
	@SerializedName(value = "Coordinates")
	private String coordinates;
	
	public BaseDataDTO(String reportId, String unitOfLength, String address, String coordinates) {		
		this.reportId = reportId;
		this.unitOfLength = unitOfLength;
		this.address = address;
		this.coordinates = coordinates;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getUnitOfLength() {
		return unitOfLength;
	}

	public void setUnitOfLength(String unitOfLength) {
		this.unitOfLength = unitOfLength;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}	
}
