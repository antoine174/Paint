package com.github.oop_assignment_3.dtos;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.oop_assignment_3.models.Transform;
import lombok.Data;

@Data
public class ShapeDTO {
	private String className;
	private int id;
	@JsonUnwrapped
	private Transform transform;
	private boolean draggable;
	private double strokeWidth;
	private String fill;
	private String stroke;
}
