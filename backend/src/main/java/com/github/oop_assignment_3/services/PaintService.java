package com.github.oop_assignment_3.services;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import com.github.oop_assignment_3.dtos.actions.CreateActionDTO;
import com.github.oop_assignment_3.dtos.actions.DeleteActionDTO;
import com.github.oop_assignment_3.dtos.actions.MoveActionDTO;
import com.github.oop_assignment_3.dtos.actions.TransformActionDTO;
import com.github.oop_assignment_3.models.Drawing;
import com.github.oop_assignment_3.models.LoadRequest;
import com.github.oop_assignment_3.models.Loader;
import com.github.oop_assignment_3.models.SaveRequest;
import com.github.oop_assignment_3.models.SaveResponse;
import com.github.oop_assignment_3.models.Saver;
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
	ShapeManager shapeManager;
	SaverFactory saverFactory;
	LoaderFactory loaderFactory;

	Drawing drawing = new Drawing();

	public PaintService(ShapeManager shapeManager, SaverFactory saverFactory,
			LoaderFactory loaderFactory) {
		this.shapeManager = shapeManager;

		this.shapeManager.registerShape(new Circle());
		this.shapeManager.registerShape(new Ellipse());
		this.shapeManager.registerShape(new Line());
		this.shapeManager.registerShape(new Rect());
		this.shapeManager.registerShape(new Square());
		this.shapeManager.registerShape(new Triangle());

		this.saverFactory = saverFactory;
		this.loaderFactory = loaderFactory;
	}

	public List<Shape> getStage() {
		return drawing.getShapes();
	}

	public List<Shape> delete(DeleteActionDTO actionDTO) {
		Shape shape = drawing.getShape(actionDTO.getName());

		if (shape == null) {
			throw new IllegalArgumentException(
					"Shape with id " + actionDTO.getName() + " does not exist.");
		}

		DeleteAction action = new DeleteAction(shape);

		drawing.apply(action);

		return drawing.getShapes();
	}

	public List<Shape> create(CreateActionDTO actionDTO) {
		Shape shape = shapeManager.createShape(actionDTO.getClassName());
		shape.setName(Long.toString(System.currentTimeMillis()));
		shape.setTransform(actionDTO.getTransform());
		shape.setDraggable(actionDTO.isDraggable());
		shape.setStrokeWidth(actionDTO.getStrokeWidth());
		shape.setStroke(actionDTO.getStroke());
		shape.setFill(actionDTO.getFill());
		shape.setFill(actionDTO.getFill());

		CreateAction action = new CreateAction(shape);

		drawing.apply(action);

		return drawing.getShapes();
	}

	public List<Shape> move(MoveActionDTO actionDTO) {
		Shape shape = drawing.getShape(actionDTO.getName());

		double oldX = shape.getTransform().getX();
		double oldY = shape.getTransform().getY();

		MoveAction action = new MoveAction(actionDTO.getName(), oldX, oldY,
				actionDTO.getNewX(), actionDTO.getNewY());


		drawing.apply(action);

		return drawing.getShapes();
	}

	public List<Shape> transform(TransformActionDTO actionDTO) {
		Shape shape = drawing.getShape(actionDTO.getName());
		Transform oldTransform = shape.getTransform();

		TransformAction action = new TransformAction(actionDTO.getName(),
				oldTransform, actionDTO.getNewTransform());

		drawing.apply(action);

		return drawing.getShapes();
	}

	public List<Shape> undo() {
		drawing.undo();
		return drawing.getShapes();
	}

	public List<Shape> redo() {
		drawing.redo();
		return drawing.getShapes();
	}

	public SaveResponse save(SaveRequest request) throws IOException {
		Saver saver = saverFactory.getSaver(request.getFormat());
		String data = saver.save(drawing);
		return new SaveResponse(data);
	}

	public List<Shape> load(LoadRequest request) throws IOException {
		Loader loader = loaderFactory.getLoader(request.getFormat());
		this.drawing = loader.load(request.getData());

		return drawing.getShapes();
	}
}
