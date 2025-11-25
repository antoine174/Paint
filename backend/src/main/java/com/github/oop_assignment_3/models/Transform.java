package com.github.oop_assignment_3.models;

import lombok.Data;

@Data
public class Transform {
	private double x = 100;
	private double y = 100;

	private double scaleX = 1.0;
	private double scaleY = 1.0;

	private double rotation = 0.0;
}
