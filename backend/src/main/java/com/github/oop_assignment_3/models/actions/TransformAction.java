package com.github.oop_assignment_3.models.actions;

import com.github.oop_assignment_3.models.Action;
import com.github.oop_assignment_3.models.Drawing;
import com.github.oop_assignment_3.models.Shape;
import com.github.oop_assignment_3.models.Transform;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class TransformAction extends Action {
	private String name;
	private Transform oldTransform;
	private Transform newTransform;

	public TransformAction(String name, Transform oldTransform,
			Transform newTransform) {
		this.name = name;
		this.oldTransform = oldTransform;
		this.newTransform = newTransform;
	}

	@Override
	public void apply(Drawing drawing) {
		Shape shape = drawing.getShape(name);
		shape.setTransform(newTransform);
	}

	@Override
	public void unapply(Drawing drawing) {
		Shape shape = drawing.getShape(name);
		shape.setTransform(oldTransform);
	}
}
