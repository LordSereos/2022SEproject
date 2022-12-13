package Application;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

import com.google.gson.Gson;

import Classes.Imported3dModel;
import Converters.ModelToDTOConverter;
import Converters.XmlFileConverter;
import DTOs.RoofModelDTO;

public class MainApplication {
	public static void main(String[] args) {
		try {
			// Log system
			Handler fileHandler = new FileHandler("./log.log", false);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			LogControl.getLogger().addHandler(fileHandler);
			LogControl.InfoLog("The log has been generated correctly");
		} catch (Exception e) {
			System.out.println("Error while initialising log system");
		}

		for (String fileName : AuxiliarFilesFunctions.getXmlFiles()) {
			Imported3dModel model = XmlFileConverter.getImported3dModelFromXml("./" + fileName);

			RoofModelDTO roofModel = ModelToDTOConverter.ConvertImportedModelToRoofModelDTO(model);
			AuxiliarFilesFunctions.saveJson(new Gson().toJson(roofModel),
					AuxiliarFilesFunctions.stripExtension(fileName) + ".json");
		}
	}
}
