package com.github.oop_assignment_3.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transform {
	private double x = 100;
	private double y = 100;

	private double scaleX = 1.0;
	private double scaleY = 1.0;

	private double rotation = 0.0;

	@Override
	public Transform clone() {
		return new Transform(this.getX(), this.getY(), this.getScaleX(), this.getScaleY(), this.getRotation());
	}
}
