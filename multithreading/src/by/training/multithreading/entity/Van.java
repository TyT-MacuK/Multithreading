package by.training.multithreading.entity;

import by.training.multithreading.util.VanIdGenerator;

public class Van implements Runnable {
	private long id;
	private State state;
	private boolean perishable;

	public enum State {
		LOADED, EMPTY, LOADING, UNLOADING
	}

	public Van(State state, boolean perishable) {
		id = VanIdGenerator.nextId();
		this.state = state;
		this.perishable = perishable;
	}

	public Van(VanParameters parameters) {
		id = VanIdGenerator.nextId();
		state = parameters.state();
		perishable = parameters.perishable();
	}

	public long getId() {
		return id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public boolean getPerishable() {
		return perishable;
	}

	public void setPerishable(boolean perishable) {
		this.perishable = perishable;
	}

	@Override
	public void run() {
		LogisticBase logisticBase = LogisticBase.getInstance();
		Terminal terminal = logisticBase.receiveTerminal(this);

		if (state == State.EMPTY) {
			terminal.loadVan(this);
		} else {
			terminal.unloadVan(this);
		}
		logisticBase.releaseTerminal(terminal);
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(id);
		result = prime * result + (state == null ? 0 : state.hashCode());
		result = prime * result + Boolean.hashCode(perishable);
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
		Van van = (Van) object;
		if (id != van.id) {
			return false;
		}
		if (perishable != van.perishable) {
			return false;
		}
		return van != null ? this.state.equals(state) : van.state.equals(state);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Van: id = ").append(id);
		builder.append(", state = ").append(state);
		builder.append(", perishable = ").append(perishable);
		builder.append("]");
		return builder.toString();
	}
}
