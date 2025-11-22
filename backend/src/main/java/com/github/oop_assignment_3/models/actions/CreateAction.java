package com.github.oop_assignment_3.models.actions;

import java.util.Map;
import com.github.oop_assignment_3.models.Action;
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
	public void apply(Map<Integer, Shape> stage) {
		stage.put(shape.getId(), shape);
	}

	@Override
	public void undo(Map<Integer, Shape> stage) {
		stage.remove(shape.getId());
	}
}
