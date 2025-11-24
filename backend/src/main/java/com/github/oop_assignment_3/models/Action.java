package com.github.oop_assignment_3.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.oop_assignment_3.models.actions.CreateAction;
import com.github.oop_assignment_3.models.actions.DeleteAction;
import com.github.oop_assignment_3.models.actions.MoveAction;
import com.github.oop_assignment_3.models.actions.TransformAction;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({@JsonSubTypes.Type(value = CreateAction.class),
		@JsonSubTypes.Type(value = DeleteAction.class),
		@JsonSubTypes.Type(value = MoveAction.class),
		@JsonSubTypes.Type(value = TransformAction.class)})
public abstract class Action {
	public abstract void apply(Drawing drawing);

	public abstract void unapply(Drawing drawing);
}
