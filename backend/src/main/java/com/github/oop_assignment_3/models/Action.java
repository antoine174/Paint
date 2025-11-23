package com.github.oop_assignment_3.models;

public abstract class Action {
	public abstract void apply(Drawing drawing);

	public abstract void unapply(Drawing drawing);
}
