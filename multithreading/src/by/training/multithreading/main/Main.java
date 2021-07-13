package by.training.multithreading.main;

import by.training.multithreading.entity.Van;
import by.training.multithreading.entity.VanParameters;
import by.training.multithreading.exception.LogisticBaseException;
import by.training.multithreading.parser.DataParser;
import by.training.multithreading.reader.DataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	private static final Logger logger = LogManager.getLogger();
	private static final String FILE_PATH = "src\\resources\\data.txt";

	public static void main(String[] args) {
		try {
			DataReader reader = new DataReader();
			List<String> listDataString = reader.readDataFromFile(FILE_PATH);

			List<VanParameters> listVanParameters = DataParser.parserDataToVanParameters(listDataString);
			List<Van> listVans = new ArrayList<>();
			for (VanParameters vanParameters : listVanParameters) {
				listVans.add(new Van(vanParameters));
			}

			ExecutorService executorService = Executors.newFixedThreadPool(listVans.size());
			for (Van van : listVans) {
				executorService.submit(van);
			}
			executorService.shutdown();
		} catch (LogisticBaseException e) {
			logger.log(Level.FATAL, "Application can not work correct", e);
		}

	}
}
