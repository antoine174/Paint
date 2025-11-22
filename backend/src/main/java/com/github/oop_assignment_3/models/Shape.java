package com.github.oop_assignment_3.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public abstract class Shape {
	@Setter(AccessLevel.NONE)
	private String className;

	private int id;

	@JsonUnwrapped
	private Transform transform = new Transform();

	private boolean draggable = true;

	private double strokeWidth = 2.0;
	private String fill;
	private String stroke;

	protected Shape(String className) {
		this.className = className;
	}
}

