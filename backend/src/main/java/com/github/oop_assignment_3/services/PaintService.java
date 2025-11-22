package com.github.oop_assignment_3.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.github.oop_assignment_3.dtos.actions.CreateActionDTO;
import com.github.oop_assignment_3.dtos.actions.DeleteActionDTO;
import com.github.oop_assignment_3.dtos.actions.MoveActionDTO;
import com.github.oop_assignment_3.dtos.actions.TransformActionDTO;
import com.github.oop_assignment_3.models.Action;
import com.github.oop_assignment_3.models.Shape;
import com.github.oop_assignment_3.models.Transform;
import com.github.oop_assignment_3.models.actions.CreateAction;
import com.github.oop_assignment_3.models.actions.DeleteAction;
import com.github.oop_assignment_3.models.actions.MoveAction;
import com.github.oop_assignment_3.models.actions.TransformAction;
import com.github.oop_assignment_3.models.shapes.Circle;
import com.github.oop_assignment_3.models.shapes.Ellipse;
import com.github.oop_assignment_3.models.shapes.Line;
import com.github.oop_assignment_3.models.shapes.Rect;
import com.github.oop_assignment_3.models.shapes.Square;
import com.github.oop_assignment_3.models.shapes.Triangle;

@Service
public class PaintService {
	ShapeService shapeService;

	Map<Integer, Shape> stage = new HashMap<>();
	List<Action> history = new ArrayList<>();
	int historyIndex = 0;

	public PaintService(ShapeService shapeService) {
		this.shapeService = shapeService;

		shapeService.registerShape(new Circle());
		shapeService.registerShape(new Ellipse());
		shapeService.registerShape(new Line());
		shapeService.registerShape(new Rect());
		shapeService.registerShape(new Square());
		shapeService.registerShape(new Triangle());
	}

	public List<Shape> getStage() {
		return new ArrayList<>(stage.values());
	}

	private List<Shape> apply(Action action) {
		while (history.size() > historyIndex) {
			history.remove(history.size() - 1);
		}

		action.apply(stage);

		history.add(action);
		historyIndex++;

		return getStage();
	}

	public List<Shape> delete(DeleteActionDTO actionDTO) {
		Shape shape = stage.get(actionDTO.getId());

		if (shape == null) {
			throw new IllegalArgumentException(
					"Shape with id " + actionDTO.getId() + " does not exist.");
		}

		DeleteAction action = new DeleteAction(shape);

		return apply(action);
	}

	public List<Shape> create(CreateActionDTO actionDTO) {
		Shape shape = shapeService.createShape(actionDTO.getShape().getClassName());
		shape.setTransform(actionDTO.getShape().getTransform());
		shape.setDraggable(actionDTO.getShape().isDraggable());
		shape.setStrokeWidth(actionDTO.getShape().getStrokeWidth());
		shape.setStroke(actionDTO.getShape().getStroke());
		shape.setFill(actionDTO.getShape().getFill());

		CreateAction action = new CreateAction(shape);

		return apply(action);
	}

	public List<Shape> move(MoveActionDTO actionDTO) {
		Shape shape = stage.get(actionDTO.getId());

		double oldX = shape.getTransform().getX();
		double oldY = shape.getTransform().getY();

		MoveAction action = new MoveAction(actionDTO.getId(), oldX, oldY,
				actionDTO.getNewX(), actionDTO.getNewY());

		return apply(action);
	}

	public List<Shape> transform(TransformActionDTO actionDTO) {
		Shape shape = stage.get(actionDTO.getId());
		Transform oldTransform = shape.getTransform();

		TransformAction action = new TransformAction(actionDTO.getId(),
				oldTransform, actionDTO.getNewTransform());

		return apply(action);
	}

	public List<Shape> redo() {
		if (historyIndex < history.size()) {
			Action action = history.get(historyIndex);
			action.apply(stage);
			historyIndex++;
		}

		return getStage();
	}

	public List<Shape> undo() {
		if (historyIndex > 0) {
			historyIndex--;
			Action action = history.get(historyIndex);
			action.undo(stage);
		}

		return getStage();
	}
}
