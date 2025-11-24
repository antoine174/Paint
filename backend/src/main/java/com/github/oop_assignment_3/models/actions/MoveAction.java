package com.github.oop_assignment_3.models.actions;

import com.github.oop_assignment_3.models.Action;
import com.github.oop_assignment_3.models.Drawing;
import com.github.oop_assignment_3.models.Shape;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class MoveAction extends Action {
	private String name;
	private double oldX;
	private double oldY;
	private double newX;
	private double newY;

	public MoveAction(String name, double oldX, double oldY, double newX,
			double newY) {
		this.name = name;
		this.oldX = oldX;
		this.oldY = oldY;
		this.newX = newX;
		this.newY = newY;
	}

	@Override
	public void apply(Drawing drawing) {
		Shape shape = drawing.getShape(name);
		shape.getTransform().setX(newX);
		shape.getTransform().setY(newY);
	}

	@Override
	public void unapply(Drawing drawing) {
		Shape shape = drawing.getShape(name);
		shape.getTransform().setX(oldX);
		shape.getTransform().setY(oldY);
	}
}
