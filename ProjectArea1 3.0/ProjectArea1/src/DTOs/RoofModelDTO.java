package DTOs;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class RoofModelDTO {
	@SerializedName(value = "BaseData")
	private BaseDataDTO baseData;
	@SerializedName(value = "Roofs")
	private ArrayList<LineDTO> roofsArray;
	
	public RoofModelDTO() {
		roofsArray = new ArrayList<LineDTO>();
	}

	public BaseDataDTO getBaseData() {
		return baseData;
	}

	public void setBaseData(BaseDataDTO baseData) {
		this.baseData = baseData;
	}

	public ArrayList<LineDTO> getRoofsArray() {
		return roofsArray;
	}

	public void setRoofsArray(ArrayList<LineDTO> roofsArray) {
		this.roofsArray = roofsArray;
	}	
}
