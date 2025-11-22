package com.github.oop_assignment_3.models.actions;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.oop_assignment_3.models.Action;
import com.github.oop_assignment_3.models.Shape;
import com.github.oop_assignment_3.models.Transform;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransformAction extends Action {
	private final int id;
	@JsonUnwrapped(prefix = "new")
	private final Transform newTransform;
	@JsonUnwrapped(prefix = "old")
	private final Transform oldTransform;

	public TransformAction(int id, Transform oldTransform,
			Transform newTransform) {
		this.id = id;
		this.newTransform = newTransform;
		this.oldTransform = oldTransform;
	}

	@Override
	public void apply(Map<Integer, Shape> stage) {
		Shape shape = stage.get(id);
		shape.setTransform(newTransform);
	}

	@Override
	public void undo(Map<Integer, Shape> stage) {
		Shape shape = stage.get(id);
		shape.setTransform(oldTransform);
	}
}
