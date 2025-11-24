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
public class DeleteAction extends Action {
	private Shape shape;

	public DeleteAction(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void apply(Drawing drawing) {
		drawing.removeShape(shape.getName());
	}

	@Override
	public void unapply(Drawing drawing) {
		drawing.addShape(shape);
	}
}
