package com.github.oop_assignment_3.dtos.actions;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.oop_assignment_3.models.Transform;
import lombok.Data;

@Data
public class CreateActionDTO {
	private String className;
	private int id;
	@JsonUnwrapped
	private Transform transform;
	private boolean draggable;
	private double strokeWidth;
	private String stroke;
	private String fill;
}
