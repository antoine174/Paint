import {ShapeConfig} from 'konva/lib/Shape';
import {WritableSignal} from '@angular/core';

export abstract class Action {
  constructor(type: string) {
    this.type = type
  }
  type: string;
  // All actions now operate on a WritableSignal for the shapes array.
  abstract apply(shapesSignal: WritableSignal<{type: string, config: ShapeConfig}[]>): void
  abstract undo(shapesSignal: WritableSignal<{type: string, config: ShapeConfig}[]>): void
}

export class Move extends Action {
  name: string;
  newX: number
  newY: number
  oldX: number
  oldY: number
  constructor(name: string, oldX: number, oldY: number, newX: number, newY: number) {
    super("move");
    this.name = name
    this.newX = newX
    this.newY = newY
    this.oldX = oldX
    this.oldY = oldY
  }
  apply(shapesSignal: WritableSignal<{type: string, config: ShapeConfig}[]>): void {
    shapesSignal.update(currentShapes =>
      currentShapes.map(shape => {
        if (shape.config.name === this.name) {
          // Create a new shape object with a new config containing the old coordinates
          return {
            ...shape,
            config: {
              ...shape.config,
              x: this.newX,
              y: this.newY
            }
          };
        }
        // Return the original shape if it's not the one we're moving
        return shape;
      })
    );
  }

  undo(shapesSignal: WritableSignal<{     type: string  ,  config: ShapeConfig }[]>): void {
    shapesSignal.update(currentShapes =>
      currentShapes.map(shape => {
        if (shape.config.name === this.name) {
          return {
            ...shape,
            config: {
              ...shape.config,
              x: this.oldX,
              y: this.oldY
            }
          };
        }
        return shape;
      })
    );
  }

}

export class Transform extends Action {
  name: string;
  newX: number
  newY: number
  oldX: number
  oldY: number
  newRotation: number
  oldRotation: number
  newScaleX: number
  newScaleY: number
  oldScaleX: number
  oldScaleY: number

  constructor(name: string, oldX: number, oldY: number, newY: number, newX: number, newRotation: number
    , oldRotation: number, oldScaleX: number, oldScaleY: number, newScaleX: number, newScaleY: number) {
    super("transform");
    this.name = name
    // NEW PARAMS
    this.newX = newX
    this.newY = newY
    this.newRotation = newRotation
    this.newScaleX = newScaleX
    this.newScaleY = newScaleY
    // OLD PARAMS
    this.oldX = oldX
    this.oldY = oldY
    this.oldRotation = oldRotation
    this.oldScaleX = oldScaleX
    this.oldScaleY = oldScaleY
  }

  apply(shapesSignal: WritableSignal<{ type: string, config: ShapeConfig }[]>): void {
    shapesSignal.update(currentShapes =>
      currentShapes.map(shape => {
        if (shape.config.name === this.name) {
          // Create a new shape object with a new config containing the old coordinates
          return {
            ...shape,
            config: {
              ...shape.config,
              x: this.newX,
              y: this.newY,
              rotation: this.newRotation,
              scaleX: this.newScaleX,
              scaleY: this.newScaleY
            }
          };
        }
        // Return the original shape if it's not the one we're moving
        return shape;
      })
    );
  }

  undo(shapesSignal: WritableSignal<{ type: string, config: ShapeConfig }[]>): void {
    shapesSignal.update(currentShapes =>
      currentShapes.map(shape => {
        if (shape.config.name === this.name) {
          return {
            ...shape,
            config: {
              ...shape.config,
              x: this.oldX,
              y: this.oldY,
              rotation: this.oldRotation,
              scaleX: this.oldScaleX,
              scaleY: this.oldScaleY
            }
          };
        }
        return shape;
      })
    );
  }
}
export class AddShape extends Action{
  className: string
  name: string
  fill: string
  constructor(className: string, name: string, fill: string) {
    super("add");
    this.className = className
    this.name = name
    this.fill = fill
  }
  apply(shapesSignal: WritableSignal<{ type: string; config: ShapeConfig }[]>): void {
    const shapesArr: {type: string, config: ShapeConfig}[] = shapesSignal();
    const cfg: any = { name: this.name, fill: this.fill, x: 100, y: 100 }
    const newShapes = [...shapesArr, {type: this.className, config: cfg}]
    shapesSignal.set(newShapes)
  }

  undo(shapesSignal: WritableSignal<{ type: string; config: ShapeConfig }[]>): void {
    shapesSignal.update((shapes) => {
      return shapes.filter((s) => s.config.name != this.name)
    })
  }

}

export class DeleteShape extends Action{
  name: string
  deletedShape: { type: string; config: ShapeConfig } | undefined
  constructor(name: string) {
    super("delete");
    this.name = name

  }
  apply(shapesSignal: WritableSignal<{ type: string; config: ShapeConfig }[]>): void {
    shapesSignal.update(currentShapes =>
      currentShapes.filter(shape => {
        if (shape.config.name == this.name) {
          this.deletedShape = shape
          return false
        }
        return true;
      })
    );
  }

  undo(shapesSignal: WritableSignal<{ type: string; config: ShapeConfig }[]>): void {
    console.log(this.deletedShape)
    if(this.deletedShape) shapesSignal.update((current) => {
      return [...current, this.deletedShape as any]
    })
    else console.log("not log of the deleted shape")
  }

}
