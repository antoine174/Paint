package com.github.oop_assignment_3.models.shapes;

import com.github.oop_assignment_3.models.Shape;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Line extends Shape {
	private int[] points;

	public Line() {
		super("line");

		this.points = new int[] {50, 50, 250, 50};
	}

	public Line(int[] points) {
		this();

		this.points = points;
	}

	@Override
	public Line clone() {
		Line cloned = (Line) super.clone();
		cloned.points = points.clone();
		return cloned;
	}
}
