package com.github.oop_assignment_3.models.shapes;

import com.github.oop_assignment_3.models.Shape;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Ellipse extends Shape {
	private double radiusX = 50;
	private double radiusY = 50;

	public Ellipse() {
		super("ellipse");
	}

	public Ellipse(double radiusX, double radiusY) {
		this();

		this.radiusX = radiusX;
		this.radiusY = radiusY;
	}

	@Override
	public Ellipse clone() {
		return (Ellipse) super.clone();
	}
}
