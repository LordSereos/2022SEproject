package Classes;

import Application.LogControl;

public class Location {
	private String address;
	private String city;
	private String state;
	private String postal;
	private Double latitude;
	private Double longitude;
	
	public Location()
	{		
	}
	
	public Location(String address, String city, String state, String postal, Double latitude, Double longitude) {		super();
		this.address = address;
		this.city = city;
		this.state = state;
		this.postal = postal;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public String getComposeAddress()
	{
		return address.concat(", ")
				.concat(city).concat(", ")
				.concat(state).concat(", ")
				.concat(postal);
	}
	
	public String getCoordinates()
	{
		try {
			return Double.toString(latitude).concat(",").concat(Double.toString(longitude));
		}catch(Exception ex)
		{
			LogControl.ErrorLog("Error getting coordinates");
			return "";
		}
		
	}
}
