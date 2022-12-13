package Converters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import Application.LogControl;
import Classes.Face;
import Classes.Imported3dModel;
import Classes.Line;
import Classes.Point;
import Classes.Polygon;
import Classes.Roof;
import DTOs.BaseDataDTO;
import DTOs.LineDTO;
import DTOs.PointDTO;
import DTOs.RoofModelDTO;

public class ModelToDTOConverter {
	public static RoofModelDTO ConvertImportedModelToRoofModelDTO(Imported3dModel importedModel)
	{
		LogControl.InfoLog("Starting create DTO for report "+importedModel.getReportId());
		RoofModelDTO roofModel = new RoofModelDTO();
		
		//Create BaseDataDTO
		BaseDataDTO baseData = new BaseDataDTO(importedModel.getReportId(),
												importedModel.getLengthUnit(),
												importedModel.getLocation().getComposeAddress(),
												importedModel.getLocation().getCoordinates());
		roofModel.setBaseData(baseData);
		
		//Go in roof
		for(Roof roof : importedModel.getRoofs())
		{
			Map<String,LineDTO> roofsMap = new TreeMap<String,LineDTO>();
			
			//Go in faces
			Iterator<String> facesIterator = roof.getFaces().keySet().iterator();
			while(facesIterator.hasNext()){
				Face face = (Face) roof.getFaces().get(facesIterator.next());
				
				//Check if the face type is roof
				if(face.getType().toLowerCase().equals("roof"))
				{
					//Go in polygon
					Polygon polygon = face.getPolygon();
					
					//Create LineDTOs
					for(String lineId : polygon.getLineIds())
					{
						Line line = roof.getLines().get(lineId);
						LineDTO lineDTO = new LineDTO(line.getId(), line.getType());
						
						//CreatePointDTOs
						for (String pointId : line.getPointIds())
						{
							Point point = roof.getPoints().get(pointId);
							PointDTO pointDTO = new PointDTO(point.getId(), point.getCoordinateX(), point.getCoordinateY(), point.getCoordinateZ());
							
							lineDTO.getPoints().add(pointDTO);
						}
						if(!roofsMap.containsKey(lineDTO.getId())) {
							roofsMap.put(lineDTO.getId(),lineDTO);
						}
					}		
					
					
				}
				
				
			}

			roofModel.setRoofsArray(new ArrayList<LineDTO>(roofsMap.values()));
		}
		
		LogControl.InfoLog("The DTO for report " + importedModel.getReportId() + " has been created successfully.");
		
		return roofModel;
	} 
}
