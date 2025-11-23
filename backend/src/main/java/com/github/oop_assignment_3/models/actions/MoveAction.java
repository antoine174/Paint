package com.github.oop_assignment_3.models.actions;

import com.github.oop_assignment_3.models.Action;
import com.github.oop_assignment_3.models.Drawing;
import com.github.oop_assignment_3.models.Shape;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MoveAction extends Action {
	private final int id;
	private final double oldX;
	private final double oldY;
	private final double newX;
	private final double newY;

	public MoveAction(int id, double oldX, double oldY, double newX,
			double newY) {
		this.id = id;
		this.oldX = oldX;
		this.oldY = oldY;
		this.newX = newX;
		this.newY = newY;
	}

	@Override
	public void apply(Drawing drawing) {
		Shape shape = drawing.getShape(id);
		shape.getTransform().setX(newX);
		shape.getTransform().setY(newY);
	}

	@Override
	public void unapply(Drawing drawing) {
		Shape shape = drawing.getShape(id);
		shape.getTransform().setX(oldX);
		shape.getTransform().setY(oldY);
	}
}
