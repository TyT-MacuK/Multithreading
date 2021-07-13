package by.training.multithreading.parser;

import by.training.multithreading.entity.Van;
import by.training.multithreading.entity.VanParameters;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataParser {
	private static final Logger logger = LogManager.getLogger();
	private static final String SPACE_DIVIDER = "\\s+";

	private DataParser() {
	}

	public static List<VanParameters> parserDataToVanParameters(List<String> listDataString) {
		List<VanParameters> listVanParameters = new ArrayList<>();
		for (String stringData : listDataString) {
			listVanParameters.add(parserStringToVanParameters(stringData));
		}
		return listVanParameters;
	}
	
	private static VanParameters parserStringToVanParameters(String stringData) {
		logger.log(Level.INFO, "method: parserDataToVanParameters");
		String[] arrayStringData = stringData.split(SPACE_DIVIDER);
		Van.State state = Van.State.valueOf(arrayStringData[0]);
		boolean perishable = Boolean.getBoolean(arrayStringData[1]);
		logger.log(Level.INFO, "Data was parsed. State = {}, perishable = {}", state, perishable);
		return new VanParameters(state, perishable);
	}
}
