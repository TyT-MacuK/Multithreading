package by.training.multithreading.entity;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;

import by.training.multithreading.util.TerminalIdGenerator;

public class Terminal {
	private static final Logger logger = LogManager.getLogger();
	private long id;
	private static final int MIN_MILLISECONDS = 500;
	private static final int MAX_MILLISECONDS = 1000;

	public Terminal() {
		id = TerminalIdGenerator.nextId();
	}

	public long getId() {
		return id;
	}

	public void unloadVan(Van van) {
		logger.log(Level.INFO, "Unloading at the van id {} has begun", van.getId());
		van.setState(Van.State.UNLOADING);
		Random random = new Random();
		int timeForUnloading = random.nextInt(MAX_MILLISECONDS - MIN_MILLISECONDS) + MIN_MILLISECONDS;
		try {
			TimeUnit.MILLISECONDS.sleep(timeForUnloading);
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, "Unload falled");
			Thread.currentThread().interrupt();
		}
		van.setState(Van.State.EMPTY);
		van.setPerishable(false);
		logger.log(Level.INFO, "Van id {} is empty", van.getId());
	}

	public void loadVan(Van van) {
		logger.log(Level.INFO, "Loading at the van id {} has begun", van.getId());
		van.setState(Van.State.LOADING);
		Random random = new Random();
		int timeForLoading = random.nextInt(MAX_MILLISECONDS - MIN_MILLISECONDS) + MIN_MILLISECONDS;
		try {
			TimeUnit.MILLISECONDS.sleep(timeForLoading);
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, "Load falled");
			Thread.currentThread().interrupt();
		}
		van.setState(Van.State.LOADED);
		van.setPerishable(random.nextBoolean());
		logger.log(Level.INFO, "Van id {} is loaded", van.getId());
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(id);
		return result;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Terminal terminal = (Terminal) object;
		return id == terminal.id;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Terminal: id = ").append(id);
		builder.append("]");
		return builder.toString();
	}
}
