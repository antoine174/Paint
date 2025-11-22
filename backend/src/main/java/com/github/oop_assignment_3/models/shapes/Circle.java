package com.github.oop_assignment_3.models.shapes;

import com.github.oop_assignment_3.models.Shape;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Circle extends Shape {
	private double radius;

	public Circle() {
		super("circle");

		this.radius = 100;
	}

	public Circle(double radius) {
		this();

		this.radius = radius;
	}

	@Override
	public Circle clone() {
		return (Circle) super.clone();
	}
}
