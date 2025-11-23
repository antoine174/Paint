package com.github.oop_assignment_3.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

public class Drawing {
	private Map<Integer, Shape> shapes = new HashMap<>();
	@Getter
	private List<Action> history = new ArrayList<>();
	@Getter
	private int historyIndex = 0;

	public Drawing() {}

	public Drawing(List<Shape> shapes, List<Action> history, int historyIndex) {
		shapes.forEach(shape -> this.shapes.put(shape.getId(), shape));
		this.history = history;
		this.historyIndex = historyIndex;
	}

	public List<Shape> getShapes() {
		return new ArrayList<>(shapes.values());
	}

	public Shape getShape(int id) {
		return shapes.get(id);
	}

	public void addShape(Shape shape) {
		shapes.put(shape.getId(), shape);
	}

	public void removeShape(int id) {
		shapes.remove(id);
	}

	public void apply(Action action) {
		while (history.size() > historyIndex) {
			history.remove(history.size() - 1);
		}

		action.apply(this);

		history.add(action);
		historyIndex++;
	}


	public void undo() {
		if (historyIndex > 0) {
			historyIndex--;
			Action action = history.get(historyIndex);
			action.unapply(this);
		}
	}

	public void redo() {
		if (historyIndex < history.size()) {
			Action action = history.get(historyIndex);
			action.apply(this);
			historyIndex++;
		}
	}

}
