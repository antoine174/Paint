package com.github.oop_assignment_3.dtos.actions;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.oop_assignment_3.models.Transform;
import lombok.Data;

@Data
public class TransformActionDTO {
	private final int id;
	@JsonUnwrapped(prefix = "new")
	private final Transform newTransform;
}
