package by.training.multithreading.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import by.training.multithreading.exception.LogisticBaseException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;

public class DataReader {
	private static final Logger logger = LogManager.getLogger();

	public List<String> readDataFromFile(String path) throws LogisticBaseException {
		logger.log(Level.INFO, "method: readDataFromFile");
		if (path == null || Files.notExists(Path.of(path))) {
			logger.log(Level.ERROR, "path {} is not correct", path);
			throw new LogisticBaseException("Path exception. Path is not correct.");
		}
		List<String> stringData;
		try (Stream<String> streamData = Files.newBufferedReader(Path.of(path)).lines()) {
			stringData = streamData.collect(Collectors.toList());
		} catch (IOException e) {
			logger.log(Level.ERROR, "Exception in method: Files.newBufferedReader(Paths.get(path)).lines()");
			throw new LogisticBaseException("IOException in readDataFromFile method ", e);
		}
		logger.log(Level.INFO, "Data was read: {}", stringData);
		return stringData;
	}
}
