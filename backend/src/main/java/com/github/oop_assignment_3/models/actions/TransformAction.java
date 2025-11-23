package com.github.oop_assignment_3.models.actions;

import com.github.oop_assignment_3.models.Action;
import com.github.oop_assignment_3.models.Drawing;
import com.github.oop_assignment_3.models.Shape;
import com.github.oop_assignment_3.models.Transform;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransformAction extends Action {
	private final int id;
	private final Transform oldTransform;
	private final Transform newTransform;

	public TransformAction(int id, Transform oldTransform,
			Transform newTransform) {
		this.id = id;
		this.oldTransform = oldTransform;
		this.newTransform = newTransform;
	}

	@Override
	public void apply(Drawing drawing) {
		Shape shape = drawing.getShape(id);
		shape.setTransform(newTransform);
	}

	@Override
	public void unapply(Drawing drawing) {
		Shape shape = drawing.getShape(id);
		shape.setTransform(oldTransform);
	}
}
