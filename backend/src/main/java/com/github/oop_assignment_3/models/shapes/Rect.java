package com.github.oop_assignment_3.models.shapes;

import com.github.oop_assignment_3.models.Shape;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Rect extends Shape {
	private double width = 200;
	private double height = 100;

	public Rect() {
		super("rect");
	}

	public Rect(double width, double height) {
		this();

		this.width = width;
		this.height = height;
	}

	@Override
	public Rect clone() {
		return (Rect) super.clone();
	}
}
