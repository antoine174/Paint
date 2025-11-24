package com.github.oop_assignment_3.dtos.actions;

import lombok.Data;

@Data
public class MoveActionDTO {
	private String name;
	private double newX;
	private double newY;
}
