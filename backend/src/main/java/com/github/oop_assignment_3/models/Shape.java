package com.github.oop_assignment_3.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.oop_assignment_3.models.shapes.Circle;
import com.github.oop_assignment_3.models.shapes.Ellipse;
import com.github.oop_assignment_3.models.shapes.Line;
import com.github.oop_assignment_3.models.shapes.Rect;
import com.github.oop_assignment_3.models.shapes.Square;
import com.github.oop_assignment_3.models.shapes.Triangle;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;


@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "className",
		visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = Circle.class, name = "circle"),
		@JsonSubTypes.Type(value = Ellipse.class, name = "ellipse"),
		@JsonSubTypes.Type(value = Line.class, name = "line"),
		@JsonSubTypes.Type(value = Rect.class, name = "rect"),
		@JsonSubTypes.Type(value = Square.class, name = "square"),
		@JsonSubTypes.Type(value = Triangle.class, name = "triangle")})
public abstract class Shape implements Cloneable {
	@Setter(AccessLevel.NONE)
	private String className;

	private String name;

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

