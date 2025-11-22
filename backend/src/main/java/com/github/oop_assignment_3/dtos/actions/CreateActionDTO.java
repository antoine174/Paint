package com.github.oop_assignment_3.dtos.actions;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.oop_assignment_3.dtos.ShapeDTO;
import lombok.Data;

@Data
public class CreateActionDTO {
	@JsonUnwrapped
	private ShapeDTO shape;
}
