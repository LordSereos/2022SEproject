package Converters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Application.LogControl;
import Classes.Face;
import Classes.Imported3dModel;
import Classes.Line;
import Classes.Location;
import Classes.Point;
import Classes.Polygon;
import Classes.Roof;

public class XmlFileConverter {

	public static Imported3dModel getImported3dModelFromXml(String filePath) {

		Imported3dModel importedModel = new Imported3dModel();

		try {
			LogControl.InfoLog("Starting load xml \""+filePath+"\"");
			// Get the Document object from xmlFile
			File file = new File(filePath);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			// Get reportId
			try {
				Element reportElement = (Element) doc.getElementsByTagName("REPORT").item(0);

				for (int i = 0; i < reportElement.getAttributes().getLength(); i++) {
					String att = reportElement.getAttributes().item(i).getNodeName();

					if (att.toLowerCase().equals("reportid")) {
						importedModel.setReportId(reportElement.getAttributes().item(i).getTextContent());
					}
				}
			} catch (Exception e) {
				LogControl.WarningLog("Error obtaining reportId");
				importedModel.setReportId("");
			}

			// Get sourceVersion & unitOfLength
			try {
				NodeList versionNodeList = doc.getElementsByTagName("VERSION");

				for(int j = 0; j < versionNodeList.getLength();j++)
				{
					Element versionElement = (Element) versionNodeList.item(j);
					
					if(versionElement.getAttributes().getLength()>0) {
						for (int i = 0; i < versionElement.getAttributes().getLength(); i++) {
							String att = versionElement.getAttributes().item(i).getNodeName();
	
							switch (att.toLowerCase()) {
							case "sourceversion":
								importedModel.setSourceVersion(versionElement.getAttributes().item(i).getTextContent());
							case "unitoflength":
								importedModel.setLengthUnit(versionElement.getAttributes().item(i).getTextContent());
							}
						}
						break;
					}
				}								
			} catch (Exception e) {
				LogControl.WarningLog("Error obtaining version data");
				importedModel.setSourceVersion("");
				importedModel.setLengthUnit("");
			}

			// Get location
			try {
				Element locationElement = (Element) doc.getElementsByTagName("LOCATION").item(0);
				importedModel.setLocation(new Location());
				importedModel.getLocation().setAddress(locationElement.getAttribute("address"));
				importedModel.getLocation().setCity(locationElement.getAttribute("city"));
				importedModel.getLocation().setLatitude(tryParseDoubleFromString(locationElement.getAttribute("lat")));
				importedModel.getLocation().setLongitude(tryParseDoubleFromString(locationElement.getAttribute("long")));
				importedModel.getLocation().setPostal(locationElement.getAttribute("postal"));
				importedModel.getLocation().setState(locationElement.getAttribute("state"));
			} catch (Exception e) {
				LogControl.WarningLog("Error obtaining location");
				importedModel.setLocation(new Location());
			}

			// Get structure orientation
			try {
				Element structureElement = (Element) doc.getElementsByTagName("STRUCTURES").item(0);
				importedModel.setStructureOrientation(tryParseDoubleFromString(structureElement.getAttribute("NORTHORIENTATION")));
			} catch (Exception e) {
				LogControl.WarningLog("Error obtaining orientation");
				importedModel.setStructureOrientation(null);
			}

			// Get roofs
			try {
				NodeList roofList = doc.getElementsByTagName("ROOF");

				for (int i = 0; i < roofList.getLength(); i++) {
					Node roofNode = roofList.item(i);
					Element roofElement = (Element) roofNode;
					Roof roof = new Roof(roofElement.getAttributes().item(0).getTextContent());

					//Get faces
					roof.setFaces(GetFacesFromNodeList(roofElement.getElementsByTagName("FACE")));
					
					//Get Lines
					roof.setLines(GetLinesFromNodeList(roofElement.getElementsByTagName("LINE")));
					
					//Get points
					roof.setPoints(GetPointsFromNodeList(roofElement.getElementsByTagName("POINT")));

					importedModel.getRoofs().add(roof);
				}
			} catch (Exception e) {
				LogControl.WarningLog("Error obtaining roofs");
				importedModel.setRoofs(new ArrayList<Roof>());
			}

		} catch (Exception e) {
			LogControl.ErrorLog(e.getMessage());
		}

		LogControl.InfoLog("The xml \""+filePath+"\" has been loaded successfully.");
		return importedModel;
	}

	private static Map<String, Face> GetFacesFromNodeList(NodeList facesList)
	{
		try {
			Map<String, Face> faces = new HashMap<String, Face>();
			for (int i = 0; i < facesList.getLength(); i++) {
				Element faceElement = (Element) facesList.item(i);
				
				ArrayList<String> children = new ArrayList<String>();
				if(!faceElement.getAttribute("children").equals(""))
				{
					children = new ArrayList<String>(
							Arrays.asList(faceElement.getAttribute("children").split(","))); 
				}
				
				Face face = new Face(faceElement.getAttribute("id"), 
									faceElement.getAttribute("designator"),
									faceElement.getAttribute("type"),
									children);
				
				Element polygonElement = (Element) faceElement.getElementsByTagName("POLYGON").item(0);
				
				Polygon polygon = new Polygon( polygonElement.getAttribute("id"),
												tryParseDoubleFromString(polygonElement.getAttribute("orientation")),
												tryParseDoubleFromString(polygonElement.getAttribute("pitch")),
												tryParseDoubleFromString(polygonElement.getAttribute("size")),
												tryParseDoubleFromString(polygonElement.getAttribute("unroundedsize")),
												polygonElement.getAttribute("pitchuom"),
												polygonElement.getAttribute("sizeuom"),
												polygonElement.getAttribute("mat"));
				
				ArrayList<String> lineIds = new ArrayList<String>();
				if(!polygonElement.getAttribute("path").equals(""))
				{
					lineIds = new ArrayList<String>(
							Arrays.asList(polygonElement.getAttribute("path").split(",")));
				}
				
				polygon.setLineIds(lineIds);
				
				face.setPolygon(polygon);
				
				faces.put(face.getId(), face);
			}

			return faces;
		} catch (Exception e) {
			LogControl.WarningLog("Error obtaining faces");
			return new HashMap<String, Face>();
		}
	}

	private static Map<String, Line> GetLinesFromNodeList(NodeList linesList) {
		try {
			Map<String, Line> lines = new HashMap<String, Line>();
			for (int i = 0; i < linesList.getLength(); i++) {
				Element lineElement = (Element) linesList.item(i);

				Line line = new Line(lineElement.getAttribute("id"), lineElement.getAttribute("type"));
				ArrayList<String> points = new ArrayList<String>();
				
				if(!lineElement.getAttribute("path").equals(""))
				{
					points = new ArrayList<String>(
							Arrays.asList(lineElement.getAttribute("path").split(",")));
				}
				
				line.setPointIds(points);
				lines.put(line.getId(), line);
			}

			return lines;
		} catch (Exception e) {
			LogControl.WarningLog("Error obtaining lines");
			return new HashMap<String, Line>();
		}
	}

	public static Map<String, Point> GetPointsFromNodeList(NodeList pointsList) {
		try {
			Map<String, Point> points = new HashMap<String, Point>();
			for (int i = 0; i < pointsList.getLength(); i++) {
				Element pointElement = (Element) pointsList.item(i);

				Point point = new Point(pointElement.getAttribute("id"));
				String[] coordinates = pointElement.getAttribute("data").split(",");
				point.setCoordinateX(tryParseDoubleFromString(coordinates[0]));
				point.setCoordinateY(tryParseDoubleFromString(coordinates[1]));
				point.setCoordinateZ(tryParseDoubleFromString(coordinates[2]));
				
				points.put(point.getId(), point);
			}

			return points;
		} catch (Exception e) {
			LogControl.WarningLog("Error obtaining points");
			return new HashMap<String, Point>();
		}
	}

	private static Double tryParseDoubleFromString(String number)
	{
		try
		{
			return Double.parseDouble(number);
		}catch(Exception e)
		{
			return (Double) null;
		}
	}
}
