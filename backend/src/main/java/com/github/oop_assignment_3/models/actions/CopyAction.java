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
public class CopyAction extends Action {
	private Shape shape;

	public CopyAction(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void apply(Drawing drawing) {
		drawing.addShape(shape);
	}

	@Override
	public void unapply(Drawing drawing) {
		drawing.removeShape(shape.getName());
	}
}
