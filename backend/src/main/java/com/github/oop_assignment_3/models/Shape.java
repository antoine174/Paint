package com.github.oop_assignment_3.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public abstract class Shape implements Cloneable {
	@Setter(AccessLevel.NONE)
	private String className;

	private int id;

	@JsonUnwrapped
	private Transform transform = new Transform();

	private boolean draggable = true;

	private double strokeWidth = 2.0;
	private String stroke;
	private String fill;

	protected Shape(String className) {
		this.className = className;
	}

	@Override
	public Shape clone() {
		try {
			return (Shape) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}

