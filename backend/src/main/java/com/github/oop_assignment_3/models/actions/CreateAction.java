package com.github.oop_assignment_3.models.actions;

import com.github.oop_assignment_3.models.Action;
import com.github.oop_assignment_3.models.Drawing;
import com.github.oop_assignment_3.models.Shape;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateAction extends Action {
	private final Shape shape;

	public CreateAction(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void apply(Drawing drawing) {
		drawing.addShape(shape);
	}

	@Override
	public void unapply(Drawing drawing) {
		drawing.removeShape(shape.getId());
	}
}
