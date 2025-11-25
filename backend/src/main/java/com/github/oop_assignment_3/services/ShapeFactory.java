package com.github.oop_assignment_3.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.github.oop_assignment_3.models.Shape;
import com.github.oop_assignment_3.models.shapes.Circle;
import com.github.oop_assignment_3.models.shapes.Ellipse;
import com.github.oop_assignment_3.models.shapes.Line;
import com.github.oop_assignment_3.models.shapes.Rect;
import com.github.oop_assignment_3.models.shapes.Square;
import com.github.oop_assignment_3.models.shapes.Triangle;

@Service
public class ShapeFactory {
	public Shape getShape(String className) {
		switch (className) {
			case "circle":
				return new Circle();
			case "ellipse":
				return new Ellipse();
			case "line":
				return new Line();
			case "rect":
				return new Rect();
			case "square":
				return new Square();
			case "triangle":
				return new Triangle();
			default:
				throw new IllegalArgumentException("Unknown shape: " + className);
		}
	}
}
