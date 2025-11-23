package com.github.oop_assignment_3.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.github.oop_assignment_3.models.Shape;

@Service
public class ShapeManager {
	Map<String, Shape> shapes = new HashMap<>();

	public void registerShape(Shape shape) {
		if (shapes.containsKey(shape.getClassName())) {
			throw new IllegalArgumentException(
					"Shape class already registered: " + shape.getClassName());
		}

		shapes.put(shape.getClassName(), shape);
	}

	public Shape createShape(String className) {
		Shape shapePrototype = shapes.get(className);
		if (shapePrototype == null) {
			throw new IllegalArgumentException(
					"Shape class not registered: " + className);
		}

		return (Shape) shapePrototype.clone();
	}
}
