package com.github.oop_assignment_3.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Drawing {
	private Map<String, Shape> shapes = new HashMap<>();
	@Getter
	private List<Action> history = new ArrayList<>();
	@Getter
	private int historyIndex = 0;

	public List<Shape> getShapes() {
		return new ArrayList<>(shapes.values());
	}

	@SuppressWarnings("unused")
	private void setShapes(List<Shape> shapes) {
		shapes.forEach(shape -> this.shapes.put(shape.getName(), shape));
	}

	public Shape getShape(String name) {
		return shapes.get(name);
	}

	public void addShape(Shape shape) {
		shapes.put(shape.getName(), shape);
	}

	public void removeShape(String name) {
		shapes.remove(name);
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
