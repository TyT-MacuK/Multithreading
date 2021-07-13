package by.training.multithreading.entity;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class LogisticBase {
	private static final Logger logger = LogManager.getLogger();
	private static final int NUMBER_OF_TERMINALS = 2;
	private static LogisticBase instance = null;
	private static Lock lock = new ReentrantLock();
	private Deque<Terminal> availableTerminals;
	private Deque<Condition> waitingCondition = new ArrayDeque<>();

	private LogisticBase() {
		availableTerminals = new ArrayDeque<>();
		for (int i = 0; i < NUMBER_OF_TERMINALS; i++) {
			availableTerminals.push(new Terminal());
		}
	}

	public static LogisticBase getInstance() {
		lock.lock();
		try {
			if (instance == null) {
				instance = new LogisticBase();
			}
		} finally {
			lock.unlock();
		}
		return instance;
	}

	public Terminal receiveTerminal(Van van) {
		logger.log(Level.INFO, "method: receiveTerminal");
		try {
			lock.lock();
			try {
				if (availableTerminals.isEmpty()) {
					Condition condition = lock.newCondition();
					if (van.getPerishable()) {
						waitingCondition.addFirst(condition);
					} else {
						waitingCondition.addLast(condition);
					}
					condition.await();
				}
			} catch (InterruptedException e) {
				logger.log(Level.ERROR, "InterruptedException in method receiveTerminal", e);
				Thread.currentThread().interrupt();
			}
			logger.log(Level.INFO, "Terminal {} resived", availableTerminals.getFirst().getId());
			return availableTerminals.removeFirst();
		} finally {
			lock.unlock();
		}
	}

	public void releaseTerminal(Terminal terminal) {
		lock.lock();
		logger.log(Level.INFO, "method: releaseTerminal");
		try {
			if (availableTerminals.size() < NUMBER_OF_TERMINALS) {
				availableTerminals.push(terminal);
				Condition condition = waitingCondition.pollFirst();
				if (condition != null) {
					condition.signal();
				}
				logger.log(Level.INFO, "Terminal {} was released", terminal.getId());
			}
		} finally {
			lock.unlock();
		}
	}
}
