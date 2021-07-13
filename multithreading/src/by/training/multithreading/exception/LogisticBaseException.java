package by.training.multithreading.exception;

public class LogisticBaseException extends Exception {

	public LogisticBaseException() {
	}

	public LogisticBaseException(String massage) {
		super(massage);
	}

	public LogisticBaseException(Throwable cause) {
		super(cause);
	}

	public LogisticBaseException(String massage, Throwable cause) {
		super(massage, cause);
	}
}
