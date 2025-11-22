package com.github.oop_assignment_3.models.shapes;

import com.github.oop_assignment_3.models.Shape;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Square extends Shape {
	private double width = 100;
	private double height = 100;

	public Square() {
		super("square");
	}

	public Square(double size) {
		this();

		this.width = size;
		this.height = size;
	}

	@Override
	public Square clone() {
		return (Square) super.clone();
	}
}
