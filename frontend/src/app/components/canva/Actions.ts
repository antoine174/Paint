import {ShapeConfig} from 'konva/lib/Shape';
import {WritableSignal} from '@angular/core';
import {RegularPolygonConfig} from 'konva/lib/shapes/RegularPolygon';

export abstract class Action {
  protected constructor(type: string) {
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

  constructor(name: string, oldX: number, oldY: number, newX: number, newY: number, oldRotation: number
    , newRotation: number, oldScaleX: number, oldScaleY: number, newScaleX: number, newScaleY: number) {
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
    switch (this.className) {
      case 'rect': {
        createRect(shapesSignal, this.name, this.fill)
        break
      }
      case 'square': {
        createSquare(shapesSignal, this.name, this.fill)
        break
      }
      case 'circle': {
        createCircle(shapesSignal, this.name, this.fill)
        break
      }
      case 'triangle': {
        createTriangle(shapesSignal, this.name, this.fill)
        break
      }
      case 'ellipse': {
        createEllipse(shapesSignal, this.name, this.fill)
        break
      }
      case 'line': {
        createLine(shapesSignal, this.name, this.fill)
        break
      }
    }
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

function createRect(shapes: WritableSignal<{ type: string; config: ShapeConfig }[]>, name: string, fill: string) {
  const cfg: any = {
    x: 100,
    y: 100,
    height: 100,
    width: 200,
    fill: fill,
    stroke: "black",
    strokeWidth: 2,
    draggable: true,
    scaleX: 1,
    scaleY: 1,
    name: name
  }
  console.log("rect")
  shapes.update((shapesArr) => [
    ...shapesArr,
    { type: "rect", config: cfg }
  ])
}
function createCircle(shapes: WritableSignal<{ type: string; config: ShapeConfig }[]>, name: string, fill: string) {
  const shapesArr = shapes();
  const cfg: any = {
    x: 100,
    y: 100,
    radius: 100,
    fill: fill,
    strokeWidth: 2,
    stroke: "black",
    draggable: true,
    scaleX: 1,
    scaleY: 1,
    name: name
  }
  shapes.set([...shapesArr, { type: "circle", config: cfg }])
}
function createSquare(shapes: WritableSignal<{ type: string; config: ShapeConfig }[]>, name: string, fill: string) {
  const shapesArr = shapes();
  const cfg: any = {
    x: 100,
    y: 100,
    height: 200,
    width: 200,
    fill: fill,
    strokeWidth: 2,
    stroke: "black",
    draggable: true,
    scaleX: 1,
    scaleY: 1,
    name: name
  }
  shapes.set([...shapesArr, { type: "square", config: cfg }])
}
function createEllipse(shapes: WritableSignal<{ type: string; config: ShapeConfig }[]>, name: string, fill: string) {
  const shapesArr = shapes();
  const cfg: any = {
    x: 100,
    y: 100,
    fill: fill,
    strokeWidth: 2,
    stroke: "black",
    draggable: true,
    scaleX: 2,
    scaleY: 1,
    name: name,
    className: 'asd',
    radiusX: 50,
    radiusY: 50
  }
  shapes.set([...shapesArr, { type: "ellipse", config: cfg }])
}
function createLine(shapes: WritableSignal<{ type: string; config: ShapeConfig }[]>, name: string, fill: string) {
  const shapesArr =shapes();
  const cfg: any = {
    points: [50, 50, 250, 50],
    fill: fill ,
    strokeWidth: 4,
    stroke: fill ,
    draggable: true,
    scaleX: 1,
    scaleY: 1,
    name: name,
    x: 0,
    y: 0
  }
  shapes.set([...shapesArr, { type: "line", config: cfg }])
}
function createTriangle(shapes: WritableSignal<{ type: string; config: ShapeConfig }[]>, name: string, fill: string) {
  const config: RegularPolygonConfig = {
    x: 100,
    y: 100,
    sides: 3,
    radius: 60,
    fill: fill,
    strokeWidth: 2,
    stroke: "black",
    draggable: true,
    scaleX: 1,
    scaleY: 1,
    name: name
  }
  const shapesArr =shapes();
  shapes.set([...shapesArr, { type: "triangle", config: config as ShapeConfig }])
}
