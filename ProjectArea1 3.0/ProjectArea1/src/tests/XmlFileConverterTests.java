package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Converters.XmlFileConverter;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Classes.Line;
import Classes.Point;

public class XmlFileConverterTests {

	@Test
	public void GetPointsFromNodeList_HappyPath() throws ParserConfigurationException {
		//Arrange		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Element element = doc.createElement("Element");
		doc.appendChild(element);
		
		Element root = doc.getDocumentElement();
		
		Element punto1 = doc.createElement("Elemento1");
		punto1.setAttribute("id", "1");
		punto1.setAttribute("data", "1.0,2.0,3.0");
		root.appendChild(punto1);
		
		Element punto2 = doc.createElement("Elemento2");
		punto2.setAttribute("id", "2");
		punto2.setAttribute("data", "4.0,5.0,6.0");
		root.appendChild(punto2);
		
		Element punto3 = doc.createElement("Elemento3");
		punto3.setAttribute("id", "3");
		punto3.setAttribute("data", "7.0,8.0,9.0");
		root.appendChild(punto3);
		
		//Act		
		Map<String, Point> mapPoint = XmlFileConverter.GetPointsFromNodeList(doc.getDocumentElement().getChildNodes());
		
		//Assert
		assertEquals(3, mapPoint.size());
	}
	
	@Test
	public void GetPointsFromNodeList_ThrowsExpection() throws ParserConfigurationException {
		//Arrange		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Element element = doc.createElement("Element");
		doc.appendChild(element);
		
		Element root = doc.getDocumentElement();
		
		Element punto1 = doc.createElement("Elemento1");
		punto1.setAttribute("id", "1");
		punto1.setAttribute("data", "1.0,2.0");
		root.appendChild(punto1);
		
		Element punto2 = doc.createElement("Elemento2");
		punto2.setAttribute("id", "2");
		punto2.setAttribute("data", "4.0,5.0,6.0");
		root.appendChild(punto2);
		
		Element punto3 = doc.createElement("Elemento3");
		punto3.setAttribute("id", "3");
		punto3.setAttribute("data", "7.0,8.0,9.0");
		root.appendChild(punto3);
		
		//Act		
		Map<String, Point> mapPoint = XmlFileConverter.GetPointsFromNodeList(doc.getDocumentElement().getChildNodes());
		
		//Assert
		assertEquals(0, mapPoint.size());
	}
}
