package com.github.oop_assignment_3.models;

import java.util.Map;

public abstract class Action {
	public abstract void apply(Map<Integer, Shape> stage);

	public abstract void undo(Map<Integer, Shape> stage);
}
