package com.github.oop_assignment_3.models.shapes;

import com.github.oop_assignment_3.models.Shape;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Triangle extends Shape {
	private double sides = 3;
	private double radius = 60;

	public Triangle() {
		super("triangle");
	}

	public Triangle(double radius) {
		this();

		this.radius = radius;
	}

	@Override
	public Triangle clone() {
		return (Triangle) super.clone();
	}
}
